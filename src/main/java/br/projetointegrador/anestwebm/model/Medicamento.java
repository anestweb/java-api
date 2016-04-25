package br.projetointegrador.anestwebm.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@Entity(name = "medicamentos")
public class Medicamento extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "A descrição do medicamento não pode ficar vazia.")
    @Column(length = 255, nullable = false)
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Medicamento other = (Medicamento) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Medicamento{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
