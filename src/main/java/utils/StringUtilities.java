package utils;

import java.util.Random;

/**
 *
 *@author Kapitoha
 *
 */
public class StringUtilities {
    
    /**
     * Create random string with required length
     * @param length
     * @return
     */
    public static String randomString(int length)
    {
	Random rand = new Random();
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < length; i++)
	{
	    int res = 0;
	    while (true)
	    {
		res = rand.nextInt(122);
		if (!(res < 48 || (res > 57 && res < 65) || (res > 90 && res < 97) || res > 122))
		{
		    sb.append((char)res);
		    break;
		}
	    }
	}
	return sb.toString();
    }
}
