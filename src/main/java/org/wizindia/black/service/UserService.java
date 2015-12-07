package org.wizindia.black.service;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wizindia.black.utils.UserUtils;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.response.UserResponse;
import org.wizindia.black.domain.User;
import org.wizindia.black.validation.PolicyValidator;
import org.wizindia.black.validation.PolicyValidatorContext;
import org.wizindia.black.validation.ValidatorContextMapBuilder;
import org.wizindia.black.validation.ValidatorEnum;
import org.wizindia.black.worker.UserWorker;

import java.util.Map;

/**
 * Created by hari_om on 6/18/15.
 */
@Service
public class UserService {

    @Autowired
    UserWorker userWorker;
    @Autowired
    UserUtils userUtils;
    @Autowired
    ValidatorService validatorService;
    @Autowired
    PolicyValidator policyValidator;

    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;

    public UserService() {}

    public UserResponse getUser(String login, User user) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();
        validatorService.validate(validatorContextMap);
        return userUtils.getEncryptedUser(userWorker.getUser(login));
    }

    public UserResponse getUser(User user) {
        return userUtils.getEncryptedUser(user);
    }

    public UserResponse insertUser(User user, User autherizer) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(autherizer);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();
        validatorService.validate(validatorContextMap);
        user.setId(0);
        return userUtils.getEncryptedUser(userWorker.insertUser(user, user.getRoles()));
    }
}
