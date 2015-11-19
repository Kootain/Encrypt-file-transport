# Encrypt-file-transport
a solution of data encryption and transportation by using RSA &amp; AES
***

#使用背景
在公共网络下，通过RAS公钥对数据经行加密。考虑到RSA加密数据大小限制以及效率限制，仅使用RSA加密基础秘钥。再通过基础秘钥对待加密数据经行AES加密。最终将加密后的数据及基础秘钥一同输出。
该加密方法可在完全开放的网络环境下进行数据通信，且双方不需要提前配置好秘钥。

#Usage

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> map = RsaseUtils.getKeys();     //Generate RSA public/private key
        byte[] plainText = "Test".getBytes();                   //Plain text "Text" -> [84, 101, 115, 116]
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
        byte[] encrypt = RsaseUtils.encrypt(plainText, publicKey);  //Encrypt `plainText`
        byte[] decrypt = RsaseUtils.decrypt(encrypt, privateKey);   //Decrypt `encrypt`
        System.out.println(Arrays.toString(plainText));     //[84, 101, 115, 116]
        System.out.println(Arrays.toString(decrypt));       //[84, 101, 115, 116]
    }

