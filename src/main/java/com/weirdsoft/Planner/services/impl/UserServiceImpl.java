package com.weirdsoft.Planner.services.impl;

import com.weirdsoft.Planner.dao.UserDao;
import com.weirdsoft.Planner.models.User;
import com.weirdsoft.Planner.services.UserService;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User Login(String login, String password) {
        User user = userDao.getUserByLogin(login);
        
        if (user == null) {
            return null; // Incorrect login
        }
        
        byte[] salt = user.getSalt().getBytes();
        
        try {
            byte[] hashedPassword = hashPassword(password, salt);
            
            if (!new String(hashedPassword).equals(user.getPasswordHash())) {
                return null;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

    @Override
    public User Register(String name, String login, String password) {
        User userExists = userDao.getUserByLogin(login);
        if (userExists != null) {
            return null;
        }
        
        User user = new User();
        user.setLogin(login);
        user.setName(name);

        byte[] salt = generateSalt();
        
        byte[] passwordHash = new byte[0];
        try {
            passwordHash = hashPassword(password, salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setSalt(new String(salt));
        user.setPasswordHash(new String(passwordHash));
        
        User registered = userDao.create(user);
        return registered;
    }
    
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        return salt;
    }
    
    private byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        return  factory.generateSecret(spec).getEncoded();
    }
}
