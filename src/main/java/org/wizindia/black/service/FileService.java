package org.wizindia.black.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.domain.User;
import org.wizindia.black.jpa.FileDao;
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
    FileDao fileDao;

    final static Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileUploadResponse saveFile(User user, String fileName, MultipartFile file, String context) throws Exception{
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .addValidator(ValidatorEnum.FileNameValidator, fileName)
                .addValidator(ValidatorEnum.FileExtensionValidator, file.getOriginalFilename())
                .addValidator(ValidatorEnum.FileSizeValidator, file.getSize())
                .build();
        validatorService.validate(validatorContextMap);
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File(getFileSavePath(context))));
        stream.write(bytes);
        stream.close();
        long id = fileDao.save(fileName);
        return new FileUploadResponse(Long.toString(id) , getDownloadLink(id));
    }

    private String getFileSavePath(String context) {
        return null;
    }

    public String getDownloadLink(long id) {
        return null;
    }
}
