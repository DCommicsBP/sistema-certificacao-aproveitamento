
package br.edu.ifrs.restinga.requisicoes.modelo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//Configurando herança
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipo")
//define o tipo raiz
@JsonTypeName("requisicao")
//tem que definir as subclasses conhecidas
@JsonSubTypes({
    @JsonSubTypes.Type(name = "aproveitamento", value = RequisicaoAproveitamento.class),
        @JsonSubTypes.Type(name = "certificacao", value = RequisicaoCertificacao.class)})
public abstract class Requisicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    private String parecer;
    private boolean deferido;
    private byte[] anexos;
    @OneToMany
    private List<Disciplina> disciplinaSolicitada;

       public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public boolean isDeferido() {
        return deferido;
    }

    public void setDeferido(boolean deferido) {
        this.deferido = deferido;
    }

    public byte[] getAnexos() {
        return anexos;
    }

    public void setAnexos(byte[] anexos) {
        this.anexos = anexos;
    }



    public List<Disciplina> getDisciplinaSolicitada() {
        return disciplinaSolicitada;
    }

    public void setDisciplinaSolicitada(List<Disciplina> disciplinaSolicitada) {
        this.disciplinaSolicitada = disciplinaSolicitada;
    }



}
