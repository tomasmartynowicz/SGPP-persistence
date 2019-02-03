package sgpp.marty.persistence.postgres;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;

public abstract class PostgreSQLClient<T> {

	private static final StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
	private static final Metadata metadata = new MetadataSources( standardRegistry ).getMetadataBuilder().build();
	private static final SessionFactory sessionFactory = metadata.buildSessionFactory();
	
	
	public PostgreSQLClient() {
	}
	
	
	public int crear(T objeto) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int id = 0;
		try {
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void actualizar(T objeto) throws HibernateException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminar(T objeto) throws HibernateException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected T traerObjeto(String consulta) {
		Session session = sessionFactory.openSession();
		T objeto = null;
		try {
			objeto = (T) session.createQuery(consulta).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	protected T traerObjeto(String consulta, Object ...params ) {
		Session session = sessionFactory.openSession();
		T objeto = null;
		try {
			Query<T> query = session.createQuery(consulta);
			int position = 0;
			for(Object p : params) {
				if(p instanceof Date) {
					query.setParameter(position, p, TemporalType.DATE);
				} else {
					query.setParameter(position, p);
				}
				position++;
			}
			objeto = query.uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> traerListaObjeto(String consulta) {
		Session session = sessionFactory.openSession();
		List<T> objeto = null;
		try {
			objeto = (List<T>) session.createQuery(consulta).list();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> traerListaObjeto(String consulta, Object ...params ) {
		Session session = sessionFactory.openSession();
		List<T> objeto = null;
		try {
			Query<T> query = session.createQuery(consulta);
			int position = 0;
			for(Object p : params) {
				query.setParameter(position, p);
				position++;
			}
			objeto = query.list();
		} finally {
			session.close();
		}
		return objeto;
	}
	

	
	
}
