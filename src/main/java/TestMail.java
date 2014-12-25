import java.util.Arrays;

import dao.JDInstanceDAO;
import domain.JDEmailConfigInstance;
import domain.JDInstance;
import service.JDInstanceService;
import service.email.Protocol;
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
	String email = "javadark.mail@gmail.com";
	String username ="javadark.mail";
	String password = "javadarkpassword";
	String host = "smtp.gmail.com";
	
	JDEmailConfigInstance ec = new JDEmailConfigInstance(email, username, password, host, 465, Protocol.SMTPS, "javadark");
	JDInstanceService.getInstance().saveInstance(ec);
	JDInstance mail = JDInstanceDAO.retrieveFromDB(JDEmailConfigInstance.class, 0);
	System.out.println(mail.toString());
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
