import java.util.Arrays;

import service.email.CreateEmailProps;
import utils.CryptUtils;
import utils.HibernateUtils;


/**
 *
 *@author Kapitoha
 *
 */
public class TestMail {

    public static void main(String[] args)
    {
	new CreateEmailProps().create(null, null, null, null, 0, null);
	//send email
//	JDInstanceService.getInstance().sendEmail("kapitohaua@gmail.com", "Java D'Ark", "This is test from Java D'Ark", (JDEmailConfigInstance) mail);
	printLine();
	String string = "Java D'Ark";
	byte[] res;
	System.out.println("test encrypt String "+ string + ". Result: " +Arrays.toString((res = CryptUtils.encode(string, "qwerty"))));
	for (byte b : res)
	{
	    System.out.print((char)b);
	}
	System.out.println();
	System.out.println("test decrypt String. Result: " + CryptUtils.decode(res, "qwerty"));
	HibernateUtils.getSessionFactory().close();

    }

    private static void printLine()
    {
	System.out.println("=======================================");
    }

}
