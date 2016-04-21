package br.projetointegrador.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 * @param <T>
 */
public class GenericDao<T> implements Dao<T> {

    private final Session session;
    private final Class<T> persistentClass;

    public GenericDao(Session session, Class<T> persistentClass) {
        this.session = session;
        this.persistentClass = persistentClass;
    }

    @Override
    public T buscaPorId(Serializable id) {
        return this.session.load(this.persistentClass, id);
    }

    @Override
    public List<T> lista() {
        return this.session.createCriteria(this.persistentClass).list();
    }


    @Override
    public void salva(T t) {
        Transaction transaction = this.session.beginTransaction();
        this.session.saveOrUpdate(t);
        transaction.commit();
    }

    @Override
    public void remove(T t) {
        Transaction transaction = this.session.beginTransaction();
        this.session.delete(t);
        transaction.commit();
    }

}
