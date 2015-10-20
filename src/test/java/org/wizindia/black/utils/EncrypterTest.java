package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hari_om on 6/25/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml"})
public class EncrypterTest {

    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;

    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;

    final static Logger logger = LoggerFactory.getLogger(EncrypterTest.class);

    @org.junit.Test
    public void testId() throws Exception {
        String sensitiveData="1";
        String  cipherText= encrypter.encrypt(sensitiveData);
//        logger.info("plainText: "+sensitiveData+" is encryoted into cipherText: "+cipherText);
        String plainText = decrypter.encrypt(cipherText);
        Assert.assertEquals(plainText,sensitiveData);
    }

    @org.junit.Test
    public void testRandomLongString() throws Exception {
        String sensitiveData="fbahfa fahfahjfhaga kfgajhgfeuaygfakh afgahjgafgahfgayueag kfabhasgfakgfeau";
        String  cipherText= encrypter.encrypt(sensitiveData);
        logger.info("plainText: "+sensitiveData+" is encryoted into cipherText: "+cipherText);
        String plainText = decrypter.encrypt(cipherText);
        Assert.assertEquals(plainText,sensitiveData);
    }

    @org.junit.Test
    public void testRandomSmallString() throws Exception {
        String sensitiveData="fbahf";
        String  cipherText= encrypter.encrypt(sensitiveData);
        logger.info("plainText: "+sensitiveData+" is encryoted into cipherText: "+cipherText);
        String plainText = decrypter.encrypt(cipherText);
        Assert.assertEquals(plainText,sensitiveData);
    }
}