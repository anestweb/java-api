package br.projetointegrador.anestwebm.model;

import java.io.Serializable;

/**
 * Esta classe tem a única função de encapsular os modelos utilizados na
 * aplicação e forçá-los a implementar os métodos declarados aqui.
 *
 * Futuramente, esse encapsulamento pode ser proveitoso por permitir incluir
 * novas funcionalidades comuns aos modelos da aplicação sem correr o risco de
 * quebrar partes da aplicação.
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public abstract class AbstractModel implements Serializable {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

}
