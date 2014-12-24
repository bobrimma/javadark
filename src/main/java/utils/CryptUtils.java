package utils;

/**
 *
 *@author Kapitoha
 *
 */
public class CryptUtils {
    
    /**
     * Encrypt your string using pKey
     * @param pText
     * @param pKey
     * @return
     */
    public static byte[] encode(String pText, String pKey) 
    {
        byte[] txt = pText.getBytes();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]);
        }

        return res;
    }
    
    /**
     * Decrypt your string using pKey
     * @param pText
     * @param pKey
     * @return
     */
    public static String decode(byte[] pText, String pKey)
    {
        byte[] res = new byte[pText.length];
        byte[] key = pKey.getBytes();

        for (int i = 0; i < pText.length; i++) {
            res[i] = (byte) (pText[i] ^ key[i % key.length]);
        }

        return new String(res);
    }
}
