package EFT;

import java.security.Key;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class EFTUtils {
	
	public static byte[] decrypt(byte[] plainData, RSAPublicKey publicKey, String password) throws Exception{
		byte[] encryptData; 		//The result of encrypt, end with 64bites password information
		//encrypt password with RSA publicKey
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
		byte[] encryptPassword = cipher.doFinal(password.getBytes());
		
		//Encrypt date with AES
		KeyGenerator keyGen=KeyGenerator.getInstance("AES");  
		keyGen.init(128,new SecureRandom(password.getBytes()));		//TODO 128 should be configurable
	    Key key=keyGen.generateKey();
		Cipher cipherAES=Cipher.getInstance("AES/ECB/PKCS5Padding"); 
	    cipherAES.init(Cipher.ENCRYPT_MODE,key);  
		encryptData=cipherAES.doFinal(plainData);
		return encryptData;
	}
	
	public static byte[] encrypt(byte[] encryptData, RSAPrivateKey privateKey) throws Exception{
		Cipher cipherRSA = Cipher.getInstance("RSA");  
        cipherRSA.init(Cipher.DECRYPT_MODE, privateKey);  
        byte[] plainData = cipherRSA.doFinal(encryptData); 
		return plainData;
	}
}
