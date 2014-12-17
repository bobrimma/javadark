package main.java.controller;

import javax.servlet.http.HttpServlet;

import main.java.utils.HibernateUtils;

/**
 * This class is necessary for initialization SessionFactory to allow hibernate operations.
 * It seems it is a temporary solution.
 *@author Kapitoha
 *
 */
public abstract class AbstractServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AbstractServlet()
    {
	super();
	HibernateUtils.init();
    }

}
