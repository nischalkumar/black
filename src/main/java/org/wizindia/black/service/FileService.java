package org.wizindia.black.service;

import com.google.common.io.Files;
import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.domain.User;
import org.wizindia.black.jpa.FileDao;
import org.wizindia.black.jpa.FileSystem;
import org.wizindia.black.utils.FileSystemUtils;
import org.wizindia.black.validation.PolicyValidatorContext;
import org.wizindia.black.validation.ValidatorContextMapBuilder;
import org.wizindia.black.validation.ValidatorEnum;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    final static Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileUploadResponse saveFile(User user, String fileName, MultipartFile file, String context) throws Exception{
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .addValidator(ValidatorEnum.FileNameValidator, fileName)
                .addValidator(ValidatorEnum.FileExtensionValidator, file.getName())
                .addValidator(ValidatorEnum.FileSizeValidator, file.getSize())
                .build();
        validatorService.validate(validatorContextMap);
        FileSystem fileSystem = fileSystemFactory.getFileSystem();
        String finalContext = fileSystem.save(fileSystem.getFileSavePath(context, fileName), file);
        return new FileUploadResponse(fileName , fileSystemUtils.getDownloadLink(finalContext));
    }
}
