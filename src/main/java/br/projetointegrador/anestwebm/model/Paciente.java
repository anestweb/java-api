package br.projetointegrador.anestwebm.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@Entity(name = "pacientes")
public class Paciente extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do paciente está em branco.")
    @Column(length = 80, nullable = false)
    private String nome_completo;

//    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF não é válido.")
    @Column(length = 11, unique = true, columnDefinition = "CHAR(11)")
    private String cpf;

    @Column(length = 45)
    private String rg;

    @NotNull(message = "A data de nascimento não foi informada.")
    @Past(message = "A data de nascimento não pode ser uma data futura.")
    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

    @NotNull(message = "Escolha uma opção de gênero.")
    @Enumerated(EnumType.ORDINAL)
    private Genero genero = Genero.NAO_INFORMADO;

    @Email(message = "O e-mail informado não é válido.")
    @Column(length = 100)
    private String email;

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

    public void setGenero(String genero) {
        if (genero != null && genero.length() > 0) {
            setGenero(genero.charAt(0));
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setDataNascimento(String data_nascimento) {
        try {
            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.data_nascimento = dateFormat.parse(data_nascimento);
        } catch (ParseException ex) {
            this.data_nascimento = null;
        }
    }

    public int getIdade() {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(this.data_nascimento);
        final LocalDate born = LocalDate.of(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        final LocalDate today = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(born, today);
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
        return "Paciente{" + "id=" + id + ", nome_completo=" + nome_completo + ", cpf=" + cpf + ", rg=" + rg + ", data_nascimento=" + data_nascimento + ", genero=" + genero + ", email=" + email + ", profissional=" + profissional + '}';
    }

}
