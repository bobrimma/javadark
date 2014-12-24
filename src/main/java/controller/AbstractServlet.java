package controller;

import javax.servlet.http.HttpServlet;

/**
 * This class is necessary for initialization SessionFactory to allow hibernate
 * operations. It seems it is a temporary solution.
 * @deprecated
 * @author Kapitoha
 * 
 */
public abstract class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AbstractServlet() {
		super();
		 //HibernateUtils.init();
	}

}
