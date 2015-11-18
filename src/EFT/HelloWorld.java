package EFT;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;




public class HelloWorld

{
	public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
        HashMap<String, Object> map = RSAUtils.getKeys();  
        //生成公钥和私钥  
        byte[] plainText = getBytes("d:\\KOF97.zip");
        
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
        
        String randomStr = RSAUtils.getRandomString(53);
        
        encrypt(plainText, randomStr, publicKey, privateKey);
        
        
    }
	
	public static void encrypt(byte[] plain, String password, RSAPublicKey publicKey,RSAPrivateKey privateKey) throws Exception{
		
		//对password加密
		Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] passwordT = cipher.doFinal(password.getBytes());
        
        //用password对文件加密
        KeyGenerator keyGen=KeyGenerator.getInstance("AES");  
        keyGen.init(128,new SecureRandom(password.getBytes()));
        Key key=keyGen.generateKey();
        Cipher cipherAES=Cipher.getInstance("AES/ECB/PKCS5Padding"); 
        cipherAES.init(Cipher.ENCRYPT_MODE,key);  
        byte[] cipherText=cipherAES.doFinal(plain); 
        byte[] result = decrypt(cipherText, passwordT, privateKey);
        getFile(cipherText,"D:\\","enKOF97.zip");
        getFile(result,"D:\\","deKOF97.zip");
	}
	
	public static byte[] decrypt(byte[] cipherText,byte[] passwordT, RSAPrivateKey privateKey) throws Exception{
		
		String password = RSAUtils.decryptByPrivateKey(RSAUtils.bcd2Str(passwordT), privateKey);
		
		KeyGenerator keyGen=KeyGenerator.getInstance("AES");  
        keyGen.init(128,new SecureRandom(password.getBytes()));
        Key key=keyGen.generateKey();
		Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding"); 
	    cipher.init(Cipher.DECRYPT_MODE,key);  
	    byte[] plain=cipher.doFinal(cipherText);
	    return plain;
	}
	
    public static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                   e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    } 
} 