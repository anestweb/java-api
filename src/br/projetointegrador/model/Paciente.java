package br.projetointegrador.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@Entity(name = "pacientes")
public class Paciente extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome_completo;

    @Column(length = 11, nullable = false, unique = true, columnDefinition = "CHAR(11)")
    private String cpf;

    @Column(length = 30)
    private String rg;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genero genero = Genero.NAO_INFORMADO;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    public static enum Genero {
        MASCULINO,
        FEMININO,
        NAO_INFORMADO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nome_completo;
    }

    public void setNomeCompleto(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setGenero(char genero) {
        switch (genero) {
            case 'M':
            case 'm':
                this.genero = Genero.MASCULINO;
                break;
            case 'F':
            case 'f':
                this.genero = Genero.FEMININO;
                break;
            case 'O':
            case 'o':
                this.genero = Genero.NAO_INFORMADO;
                break;
            default:
                throw new EnumConstantNotPresentException(Genero.class, String.valueOf(genero));
        }
    }

    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getIdade() {
        if (this.data_nascimento != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.data_nascimento);
            LocalDate born = LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));
            LocalDate today = LocalDate.now();
            return (int) ChronoUnit.YEARS.between(born, today);
        }
        return 0;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.cpf);
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
        final Paciente other = (Paciente) obj;
        return (Objects.equals(this.cpf, other.cpf) && Objects.equals(this.id, other.id));
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nome_completo=" + nome_completo + ", cpf=" + cpf + ", rg=" + rg + ", genero=" + genero + ", data_nascimento=" + data_nascimento + ", profissional=" + profissional + '}';
    }

}
