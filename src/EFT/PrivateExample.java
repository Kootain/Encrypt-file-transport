package EFT;
/** 
*PrivateExmaple.java 
*Copyright 2005-2-16 
*/  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  

import java.security.Key;  
import java.security.SecureRandom;
  
/** 
*&Euml;&frac12;?&frac14;&Oacute;&Atilde;&Uuml;&amp;&amp;¡À&amp;&Ouml;¡è&Iuml;&amp;&Iuml;&amp;&amp;u&Atilde;&Uuml;&ETH;&Ocirc; 
*/  
public class PrivateExample{  
    public static void main(String[] args) throws Exception{  
      
    byte[] plainText="123456783213fdfdadfs321d123456783213fdfdadfs321d123456783213fdfdadfs321d123456783213fdfdadfs321d321321312123456783213fdfdadfs321d".getBytes();  
  
    //&Iacute;¨E&sup1;&amp;KeyGenerator&ETH;&Icirc;&sup3;&Eacute;&Ograve;&amp;&amp;&amp;key  
    System.out.println("\nStart generate AES key");  
    KeyGenerator keyGen=KeyGenerator.getInstance("AES");  
    keyGen.init(128,new SecureRandom("123456783213fdfdadfs321d123456783213fdfdadfs321d123456783213fdfdadfs321d1234567".getBytes()));  
    Key key=keyGen.generateKey();  
    System.out.println("Finish generating AES key");  
  
    //&amp;&amp;&amp;&Atilde;&Ograve;&amp;&amp;&amp;&Euml;&frac12;?&frac14;&Oacute;&Atilde;&Uuml;&Agrave;aCipher&amp;&amp;ECB&Ecirc;&Ccedil;&frac14;&Oacute;&Atilde;&Uuml;¡¤&frac12;&Ecirc;&frac12;&amp;&amp;PKCS5Padding&Ecirc;&Ccedil;&Igrave;&amp;&sup3;&amp;¡¤&frac12;¡¤¨E  
    Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");  
    System.out.println("\n"+cipher.getProvider().getInfo());  
  
    //&Ecirc;&sup1;&Oacute;&Atilde;&Euml;&frac12;?&frac14;&Oacute;&Atilde;&Uuml;  
    System.out.println("\nStart encryption:");  
    cipher.init(Cipher.ENCRYPT_MODE,key);  
    byte[] cipherText=cipher.doFinal(plainText);  
    System.out.println("Finish encryption:"+cipherText.length);  
    System.out.println(RSAUtils.bcd2Str(cipherText));  
  
    System.out.println("\nStart decryption:"+plainText.length);  
    cipher.init(Cipher.DECRYPT_MODE,key);  
    byte[] newPlainText=cipher.doFinal(cipherText);  
    System.out.println("Finish decryption:");  
  
    System.out.println(new String(newPlainText,"UTF8"));  
    }  
}