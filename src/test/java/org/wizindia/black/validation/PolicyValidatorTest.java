package org.wizindia.black.validation;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.domain.Roles;
import org.wizindia.black.domain.User;
import org.wizindia.black.validation.validator.PolicyValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nischal.k on 26/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml"})
public class PolicyValidatorTest extends TestCase {

    @Test
    public void testValidate() throws Exception {
        PolicyValidator policyValidator = new PolicyValidator();
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(new Roles(0, Role.STUDENT));
        User user = new User("abcd", "efgh", roleSet);
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user, Arrays.asList(Role.STUDENT));
        List<? extends ValidationError> validationErrorList = policyValidator.validate(policyValidatorContext);
    }
}