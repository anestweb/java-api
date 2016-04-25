package br.projetointegrador.anestwebm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@Entity(name = "profissionais")
public class Profissional extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[0-9]{4,6}/[A-Z]{2}$", message = "Informe o CRM no formato 9999/UF.")
    @Column(length = 9, unique = true, nullable = false, columnDefinition = "CHAR(9)")
    private String crm;

    @NotBlank(message = "O nome do profissional não pode ficar em branco.")
    @Column(length = 80, nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail do profissional deve ser informado.")
    @Email(message = "Um e-mail válido deve ser informado.")
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Length(min = 4, max = 24, message = "A senha deve ter entre {min} e {max} caracteres.")
    @Column(length = 64, nullable = false)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Local> locais = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.crm);
        hash = 61 * hash + Objects.hashCode(this.email);
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
        final Profissional other = (Profissional) obj;
        return (Objects.equals(this.crm, other.crm) && Objects.equals(this.email, other.email));
    }

    @Override
    public String toString() {
        return "Profissional{" + "id=" + id + ", crm=" + crm + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", locais=" + locais + '}';
    }

}
