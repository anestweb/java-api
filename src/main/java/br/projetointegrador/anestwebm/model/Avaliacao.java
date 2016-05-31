package br.projetointegrador.anestwebm.model;

import br.projetointegrador.anestwebm.util.StringUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
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
@Entity(name = "avaliacao")
public class Avaliacao extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Temporal(TemporalType.DATE)
    private Date data_avaliacao;

    @Basic
    private String cirurgia;

    @Basic
    private String procedimento;

    @Basic
    private String peso;

    @Basic
    private String altura;

    @Basic
    private String pa;

    @Basic
    private String palidez;

    @Basic
    private String dentadura;

    @Basic
    private String dentes;

    @Basic
    private String boca;

    @Basic
    private String pescoco;

    @Basic
    private String pescoco_flexao;

    @Basic
    private String mallampati;

    @Basic
    private String anotacao_fisico;

    @Basic
    private String anotacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Retorna a data da avaliação como string no padrão brasileiro.
     * @return 
     */
    public String getDataAvaliacao() {
        return data_avaliacao != null
                ? StringUtil.dateToString(data_avaliacao)
                : null;
    }

    public void setDataAvaliacao(String data_avaliacao) {
        try {
            this.data_avaliacao = StringUtil.stringToDate(data_avaliacao);
        } catch (ParseException ex) {
            this.data_avaliacao = null;
        }
    }

    public void setDataAvaliacao(Date data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }

    public String getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(String cirurgia) {
        this.cirurgia = cirurgia;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getPalidez() {
        return palidez;
    }

    public void setPalidez(String palidez) {
        this.palidez = palidez;
    }

    public String getDentadura() {
        return dentadura;
    }

    public void setDentadura(String dentadura) {
        this.dentadura = dentadura;
    }

    public String getDentes() {
        return dentes;
    }

    public void setDentes(String dentes) {
        this.dentes = dentes;
    }

    public String getBoca() {
        return boca;
    }

    public void setBoca(String boca) {
        this.boca = boca;
    }

    public String getPescoco() {
        return pescoco;
    }

    public void setPescoco(String pescoco) {
        this.pescoco = pescoco;
    }

    public String getPescocoFlexao() {
        return pescoco_flexao;
    }

    public void setPescocoFlexao(String pescoco_flexao) {
        this.pescoco_flexao = pescoco_flexao;
    }

    public String getMallampati() {
        return mallampati;
    }

    public void setMallampati(String mallampati) {
        this.mallampati = mallampati;
    }

    public String getAnotacaoFisico() {
        return anotacao_fisico;
    }

    public void setAnotacaoFisico(String anotacao_fisico) {
        this.anotacao_fisico = anotacao_fisico;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Avaliacao other = (Avaliacao) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + id + ", profissional=" + profissional + ", paciente=" + paciente + ", data_avaliacao=" + data_avaliacao + ", cirurgia=" + cirurgia + ", procedimento=" + procedimento + ", peso=" + peso + ", altura=" + altura + ", pa=" + pa + ", palidez=" + palidez + ", dentadura=" + dentadura + ", dentes=" + dentes + ", boca=" + boca + ", pescoco=" + pescoco + ", pescoco_flexao=" + pescoco_flexao + ", mallampati=" + mallampati + ", anotacao_fisico=" + anotacao_fisico + ", anotacoes=" + anotacoes + '}';
    }

}
