/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes.modelo;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jader
 */
@Entity
public class RequisicaoCertificacao extends Requisicao{
    
    private String formacaoAtividadeAnterior;
    private String criterioAvaliacao;

    @JsonProperty("tipo")
    @Transient
    private final String tipo = "certificacao";

    public String getFormacaoAtividadeAnterior() {
        return formacaoAtividadeAnterior;
    }

    public void setFormacaoAtividadeAnterior(String formacaoAtividadeAnterior) {
        this.formacaoAtividadeAnterior = formacaoAtividadeAnterior;
    }

    public String getCriterioAvaliacao() {
        return criterioAvaliacao;
    }

    public void setCriterioAvaliacao(String criterioAvaliacao) {
        this.criterioAvaliacao = criterioAvaliacao;
    }
    
    
}
