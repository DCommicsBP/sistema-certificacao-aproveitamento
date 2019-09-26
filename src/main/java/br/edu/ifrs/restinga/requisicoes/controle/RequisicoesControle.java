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
import br.edu.ifrs.restinga.requisicoes.modelo.RequisicaoCertificacao;

/**
 *
 * @author daione
 */

@RestController
@RequestMapping(path = "/api/requisisoes")
public class RequisicoesControle {
	
	@Autowired
	RequisicaoDAO rDao; 
		
	@GetMapping
	public ResponseEntity<?> listarRequisicao(){
		Iterable<Requisicao> r = rDao.findAll(); 
		return new ResponseEntity<Iterable<Requisicao>>(r, HttpStatus.OK);
			
	}
			
	public RequisicaoCertificacao insereCertificacao(RequisicaoCertificacao c) {
		return null; 
		
	}
	
    public void atualizaRequisicao () {
    	
    }
    
//     @RequestMapping(path = "/pesquisar/tipo/", method = RequestMethod.GET)
//    public Iterable<Requisicao> pesquisaPorTipo(
//            @RequestParam String contem) {
//        return rDao.findByTipoContaining(contem);
//    }

    
}
