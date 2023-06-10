package me.otmane.demo.repositories;

import me.otmane.demo.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseRepository<Model, Pk> {
    protected static Session session = null;

    protected final Class<Model> klass;

    protected BaseRepository() {
        if (session == null) {
            session = HibernateUtils.getSessionFactory().openSession();
        }

        klass = (Class<Model>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void create(Model o) {
        try {
            Transaction t = session.beginTransaction();
            session.persist(o);
            session.flush();
            t.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Model> list() {
        return session.createNamedQuery("models.User.all", klass).list();
    }

    public Model byId(Pk pk) {
        return session.get(klass, pk);
    }

    public void update(Model o) {
        try {
            Transaction t = session.beginTransaction();
            session.merge(o);
            t.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Model o) {
        try {
            Transaction t = session.beginTransaction();
            session.remove(o);
            t.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
