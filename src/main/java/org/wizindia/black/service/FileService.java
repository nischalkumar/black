package org.wizindia.black.service;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.FinalFilePathContext;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.common.response.FileDownloadResponse;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.domain.Context;
import org.wizindia.black.domain.Feed;
import org.wizindia.black.domain.User;
import org.wizindia.black.exception.ValidationException;
import org.wizindia.black.jpa.FileSystem;
import org.wizindia.black.utils.FeedUtils;
import org.wizindia.black.utils.FileSystemUtils;
import org.wizindia.black.validation.PolicyValidatorContext;
import org.wizindia.black.validation.ValidationError;
import org.wizindia.black.validation.ValidatorContextMapBuilder;
import org.wizindia.black.validation.ValidatorEnum;
import org.wizindia.black.worker.FeedWorker;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by nischal.k on 07/12/15.
 */
@Service
public class FileService {
    @Autowired
    ValidatorService validatorService;
    @Autowired
    FileSystemFactory fileSystemFactory;
    @Autowired
    FileSystemUtils fileSystemUtils;
    @Autowired
    FeedWorker feedWorker;
    @Autowired
    FeedUtils feedUtils;


    final static Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileUploadResponse saveFile(final String fileName, final MultipartFile file, final String contextCode) throws Exception{
        Feed feed = null;
        try {
            Context context = feedWorker.getContext(contextCode);
            feed = feedWorker.save(context, fileName, (long) 99999999);
            FileSystem fileSystem = fileSystemFactory.getFileSystem();
            fileSystem.save(context, feed, file);
            return new FileUploadResponse(fileSystemUtils.getDownloadLink(feed.getFeedId(), context.getContextId(), context.isAuthRequired()));
        } catch (Exception ex) {
            if(feed!=null) {
                feedWorker.markDeleted(feed.getFeedId());
            }
            throw ex;
        }
    }

    public FileUploadResponse saveFile(final User user, final String fileName, final MultipartFile file, final String contextCode) throws Exception{
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .addValidator(ValidatorEnum.FileNameValidator, fileName)
                .addValidator(ValidatorEnum.FileExtensionValidator, file.getName())
                .addValidator(ValidatorEnum.FileSizeValidator, file.getSize())
                .build();
        //TODO: check the return value of validtor. take proper steps to throw checked exceptions
        validatorService.validate(validatorContextMap);

        Feed feed = null;
        try {
            Context context = feedWorker.getContext(contextCode);
            feed = feedWorker.save(context, fileName, user.getId().longValue());
            FileSystem fileSystem = fileSystemFactory.getFileSystem();
            fileSystem.save(context, feed, file);
            return new FileUploadResponse(fileSystemUtils.getDownloadLink(feed.getFeedId(), context.getContextId(), context.isAuthRequired()));
        } catch (Exception ex) {
            if(feed!=null) {
                feedWorker.markDeleted(feed.getFeedId());
            }
            throw ex;
        }
    }

    public File getFile(final String encryptedFinalContext, User user) throws IOException {

        FileSystem fileSystem = fileSystemFactory.getFileSystem();
        FinalFilePathContext finalFilePathContext = fileSystemUtils.getOriginalContextFromEncryptedOriginalContext(encryptedFinalContext);
        Context context = feedWorker.getContext(finalFilePathContext.getContextId());

        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .addValidator(ValidatorEnum.AuthContextDownLoadRequiredValidator, context)
                .build();

        validatorService.validate(validatorContextMap);

        Feed feed = feedWorker.getFeed(finalFilePathContext.getFeedId());
        File file = (File)fileSystem.get(context, feed, false).get(0);
        enrichFileDetails(finalFilePathContext, file);
        return file;
    }



    public FileDownloadResponse getFile(final String encryptedFinalContext) throws IOException {
        FileSystem fileSystem = fileSystemFactory.getFileSystem();
        FinalFilePathContext finalFilePathContext = fileSystemUtils.getOriginalContextFromEncryptedOriginalContext(encryptedFinalContext);
        Context context = feedWorker.getContext(finalFilePathContext.getContextId());

        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.UnAuthContextDownLoadRequiredValidator, context)
                .build();

        validatorService.validate(validatorContextMap);

        Feed feed = feedWorker.getFeed(finalFilePathContext.getFeedId());
        File file = (File)fileSystem.get(context, feed, false).get(0);
        enrichFileDetails(finalFilePathContext, file);
        return new FileDownloadResponse(feed, file);
    }

    private void enrichFileDetails(FinalFilePathContext finalFilePathContext, File file) {
        Feed feed = feedWorker.getFeed(finalFilePathContext.getFeedId());
    }

    public ContextRequest saveContext(User user, ContextRequest contextRequest) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();

        validatorService.validate(validatorContextMap);
        return feedWorker.save(contextRequest);
    }
}
