package main.java.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Kapitoha
 *
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory = createSessionFactory();
    
    public static SessionFactory createSessionFactory()
    {
	Configuration config = new Configuration().configure();
	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(config.getProperties());
	return config.buildSessionFactory(builder.build());
    }
    /**
     * Get SessionFactory © Your CO
     * @return
     */
    public static SessionFactory getSessionFactory()
    {
	return sessionFactory;
    }
}
