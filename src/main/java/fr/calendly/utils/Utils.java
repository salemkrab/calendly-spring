package fr.calendly.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
	private static final String SALT_LEFT= "jfiHhR";
	private static final String SALT_RIGHT= "lBorSPg";
	
    public static String generateSha1SaltedPassword(String password) {
        	
    	String saltedPassword = SALT_LEFT + password + SALT_RIGHT;
        return DigestUtils.sha1Hex(saltedPassword);	
    }
        
}
