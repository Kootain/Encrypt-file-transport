# Encrypt-file-transport
a solution of data encryption and transportation by using RSA &amp; AES
***

#使用背景
在公共网络下，通过RAS公钥对数据经行加密。考虑到RSA加密数据大小限制以及效率限制，仅使用RSA加密基础秘钥。再通过基础秘钥对待加密数据经行AES加密。最终将加密后的数据及基础秘钥一同输出。

#Usage

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> map = RsaseUtils.getKeys();  
        byte[] plainText = "Test".getBytes();
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
        byte[] encrypt = RsaseUtils.encrypt(plainText, publicKey);
        byte[] decrypt = RsaseUtils.decrypt(encrypt, privateKey);
        System.out.println(Arrays.toString(plainText));
        System.out.println(Arrays.toString(decrypt));
    }

