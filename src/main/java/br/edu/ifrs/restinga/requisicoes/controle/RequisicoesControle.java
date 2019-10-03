/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.requisicoes.dao.RequisicaoDAO;
import br.edu.ifrs.restinga.requisicoes.modelo.Requisicao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "/requisicoes/")
    public ResponseEntity<?> listarRequisicao() {
        Iterable<Requisicao> r = rDao.findAll();
        return new ResponseEntity<Iterable<Requisicao>>(r, HttpStatus.OK);

    }

    @PostMapping(path = "/requisicoes/")
    public Requisicao insere(@RequestBody Requisicao c) {

        return rDao.save(c);

    }
//
//        @RequestMapping(path = "/nome/{nome}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<Funcionario> buscarPeloNome(@PathVariable("nome") String nome) {
//        return funcionarioDAO.findByNome(nome);
//    }
//        @RequestMapping(path = "/pesquisar/andar/", method = RequestMethod.GET)
//    public Iterable<Vaga> pesquisaPorAndar(@RequestParam int inicia) {
//        return vagaDAO.findByAndarStartingWith(inicia);
//
//    }

    @RequestMapping(path = "parecer/", method = RequestMethod.GET)
    public Iterable<Requisicao> listarTipo(@RequestParam String parecer) {
        return rDao.findByParecer(parecer);
    }

}
