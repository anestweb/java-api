package br.projetointegrador.anestwebm.model;

import br.projetointegrador.anestwebm.util.DateUtil;
import br.projetointegrador.anestwebm.util.StringUtil;
import java.text.ParseException;
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
    private Genero genero = Genero.NAO_DECLARADO;

    @Email(message = "O e-mail informado não é válido.")
    @Column(length = 100)
    private String email;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    public static enum Genero {
        MASCULINO('M'),
        FEMININO('F'),
        NAO_DECLARADO('O');

        private final char opcao;

        Genero(char opcao) {
            this.opcao = opcao;
        }

        public char asChar() {
            return this.opcao;
        }

        public String asHomemMulher() {
            switch (opcao) {
                case 'M':
                    return "Homem";
                case 'F':
                    return "Mulher";
                default:
                    return "";
            }
        }

        public String asString() {
            switch (opcao) {
                case 'M':
                    return "MASCULINO";
                case 'F':
                    return "FEMININO";
                default:
                    return "NÃO DECLARADO";
            }
        }
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
        this.nome_completo = nome_completo.isEmpty() ? null : nome_completo;
    }

    /**
     * Retorna o CPF formatado.
     * @return o cpf formatado.
     */
    public String getCpf() {
        return cpf != null ? StringUtil.formatCpf(cpf) : cpf;
    }

    /**
     * Armazena apenas os números do cpf informado.
     * @param cpf 
     */
    public void setCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        this.cpf = cpf.isEmpty() ? null : cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg.isEmpty() ? null : rg;
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
        } else {
            setGenero((Genero) null);
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
                this.genero = Genero.NAO_DECLARADO;
                break;
            default:
                throw new EnumConstantNotPresentException(Genero.class, String.valueOf(genero));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.isEmpty() ? null : email;
    }

    /**
     * Retorna a data de nascimento como string no padrão brasileiro.
     * @return 
     */
    public String getDataNascimento() {
        return data_nascimento != null
                ? StringUtil.dateToString(data_nascimento)
                : null;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setDataNascimento(String data_nascimento) {
        try {
            this.data_nascimento = StringUtil.stringToDate(data_nascimento);
        } catch (ParseException ex) {
            this.data_nascimento = null;
        }
    }

    public int getIdade() {
        return DateUtil.ageFromDate(data_nascimento);
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
