/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes.controle;

import br.edu.ifrs.restinga.requisicoes.dao.RequisicaoAproveitamentoDAO;
import br.edu.ifrs.restinga.requisicoes.dao.RequisicaoCertificacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.requisicoes.dao.RequisicaoDAO;
import br.edu.ifrs.restinga.requisicoes.erros.RequisicaoInvalida;
import br.edu.ifrs.restinga.requisicoes.modelo.Requisicao;
import br.edu.ifrs.restinga.requisicoes.modelo.RequisicaoAproveitamento;
import br.edu.ifrs.restinga.requisicoes.modelo.RequisicaoCertificacao;
import java.util.ArrayList;
import java.util.Comparator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author daione
 */
@RestController
@RequestMapping(path = "/api")
public class RequisicoesControle {

    @Autowired
    RequisicaoDAO rDao;
    
    @Autowired
    RequisicaoCertificacaoDAO requisicaoCertificacaoDAO;
    
    @Autowired
    RequisicaoAproveitamentoDAO requisicaoAproveitamentoDAO;
    

    @GetMapping(path = "/requisicoes/")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Requisicao> listarRequisicao() {
        ArrayList<Requisicao> requisicoes = new ArrayList<>();
        Iterable<RequisicaoAproveitamento> apro = requisicaoAproveitamentoDAO.findAll();
        Iterable<RequisicaoCertificacao> cert = requisicaoCertificacaoDAO.findAll();
        apro.forEach((aproveitamento) -> {
            requisicoes.add(aproveitamento);
        });
        cert.forEach((certificacao) -> {
            requisicoes.add(certificacao);
        });
        requisicoes.sort(Comparator.comparing(Requisicao::getId));
        return requisicoes;
    }

    @PostMapping(path = "/requisicoes/")
    @ResponseStatus(HttpStatus.CREATED)
    public Requisicao insere(@RequestBody Requisicao c) {
        return rDao.save(c);

    }


    @GetMapping("/requisicoes/certificacao/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RequisicaoCertificacao> listarCertificacao() {
        return requisicaoCertificacaoDAO.findAll();
    }
    
    @GetMapping("/requisicoes/aproveitamento/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RequisicaoAproveitamento> listarAproveitamento() {
        return requisicaoAproveitamentoDAO.findAll();
    }
    
    
}
