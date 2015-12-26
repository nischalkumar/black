package org.wizindia.black.service;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.domain.User;
import org.wizindia.black.exception.ValidationException;
import org.wizindia.black.jpa.FileSystem;
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

    final static Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileUploadResponse saveFile(final User user, final String fileName, final MultipartFile file, final String context) throws Exception{
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .addValidator(ValidatorEnum.FileNameValidator, fileName)
                .addValidator(ValidatorEnum.FileExtensionValidator, file.getName())
                .addValidator(ValidatorEnum.FileSizeValidator, file.getSize())
                .build();
        //TODO: check the return value of validtor. take proper steps to throw checked exceptions
        List<ValidationError> validationErrorList = validatorService.validate(validatorContextMap);
        if(CollectionUtils.isNotEmpty(validationErrorList)) {
            throw new ValidationException(validationErrorList);
        }
        FileSystem fileSystem = fileSystemFactory.getFileSystem();
        String originalContext = fileSystem.save(fileSystem.getFileSavePath(context, fileName), file);
        feedWorker.save(context, fileName, user.getId().longValue());
        return new FileUploadResponse(fileSystemUtils.getDownloadLink(originalContext));

    }

    public File getFile(final String encryptedFinalContext) throws IOException {
        FileSystem fileSystem = fileSystemFactory.getFileSystem();
        //FinalFilePathContext finalFilePathContext = feedWorker.getFinalPath(finalContext);
        String originalContext = fileSystemUtils.getOriginalContextFromEncryptedOriginalContext(encryptedFinalContext);
        File file = (File)fileSystem.get(originalContext, false).get(0);
        enrichFileDetails(file);
        return file;
    }

    private void enrichFileDetails(File file) {
        Feed feed = feedWorker.
    }
}
