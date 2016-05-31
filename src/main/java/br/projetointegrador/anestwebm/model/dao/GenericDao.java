package br.projetointegrador.anestwebm.model.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
        return (T) this.session.load(this.persistentClass, id);
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
        transaction.setTimeout(2);
        this.session.delete(t);
        transaction.commit();
    }

    @Override
    public List<T> buscaPorAtributo(String campo, Object valor, int limite) {
        Criteria crit = this.session.createCriteria(persistentClass)
                .add(Restrictions.eqOrIsNull(campo, valor));
        if (limite > 0) {
            crit.setMaxResults(limite);
        }
        return crit.list();
    }

    @Override
    public List<T> buscaPorAtributos(Map<String, Object> criterios, int limite) {
        Criteria crit = this.session.createCriteria(persistentClass);
        criterios.entrySet().stream().forEach((entry) -> {
            String campo = entry.getKey();
            Object valor = entry.getValue();
            crit.add(Restrictions.eqOrIsNull(campo, valor));
        });
        if (limite > 0) {
            crit.setMaxResults(limite);
        }
        return crit.list();
    }

}
