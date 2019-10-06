/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes.dao;

import br.edu.ifrs.restinga.requisicoes.modelo.RequisicaoCertificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jader
 */
@Repository
public interface RequisicaoCertificacaoDAO extends CrudRepository<RequisicaoCertificacao, Integer>{

    public Iterable<RequisicaoCertificacao> findById(long id);

}
