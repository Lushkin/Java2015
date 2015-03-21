package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    static
    {
        try
        {
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (HibernateException ex)
        {
            System.err.println("Error creating Session: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static Session currentSession()
	{
		Session s = (Session)session.get();
		if(s == null)
		{
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession()
	{
		Session s = (Session)session.get();
		session.set(null);
		if(s != null)
		{
			s.close();
		}
	}
}
