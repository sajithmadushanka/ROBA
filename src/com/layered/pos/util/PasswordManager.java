package com.layered.pos.util;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    public static String encryptedPassword(String text, int logRounds) {
        return BCrypt.hashpw(text, BCrypt.gensalt(logRounds));
    }
    public  static  boolean checkPassword(String inputPassword,String hashPassword){
        return BCrypt.checkpw(inputPassword, hashPassword);
    }
}
