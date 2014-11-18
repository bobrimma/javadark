import java.util.*;
/**
 * @Author Kapitoha
 */
public class GenerateRandomPassword {
	/**
	 * returns a string with random letters [A-Z] and numbers [1-9] except '0'.
	 * @return String
	 */
	public static String generate(int length)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
		{
			int num = -1;
			while (num < 49 || (num > 57 && num < 65) || (num > 90 && num < 97) || num > 122)
				num = new Random().nextInt(122);
			sb.append((char)num);
		}
		return sb.toString();
	}
}
