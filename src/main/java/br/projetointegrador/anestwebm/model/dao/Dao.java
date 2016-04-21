package br.projetointegrador.anestwebm.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 * @param <T>
 */
public interface Dao<T> {

    public T buscaPorId(Serializable id);

    public List<T> lista();

    public void salva(T t);

    public void remove(T t);

}
