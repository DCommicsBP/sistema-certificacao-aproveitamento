/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes.controle;

import br.edu.ifrs.restinga.requisicoes.dao.CursoDAO;
import br.edu.ifrs.restinga.requisicoes.dao.DisciplinaDAO;
import br.edu.ifrs.restinga.requisicoes.erros.ErroServidor;
import br.edu.ifrs.restinga.requisicoes.erros.NaoEncontrado;
import br.edu.ifrs.restinga.requisicoes.erros.RequisicaoInvalida;
import br.edu.ifrs.restinga.requisicoes.modelo.Curso;
import br.edu.ifrs.restinga.requisicoes.modelo.Disciplina;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author jader
 */
@RestController
@RequestMapping(path = "/api/cursos")
public class CursosController {

    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    DisciplinaDAO disciplinaDAO;
    
    
    // CURSOS  
    @GetMapping(path = "")
    public ResponseEntity<?> listar() {
    		Iterable<Curso> cursos = cursoDAO.findAll(); 
    	if(cursos == null) {
    		throw new NaoEncontrado("Não foi possível encontrar a lista de curso. "); 
    	}
        return new ResponseEntity<Iterable<Curso>>(cursoDAO.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(path="")
    public ResponseEntity<?> inserirCurso(@RequestBody Curso curso){
    	if(curso.getNome().isEmpty()) {
    		throw new RequisicaoInvalida("Você não pode inserir um curso sem número. "); 
    	}
    	
    	Curso novoCurso = cursoDAO.save(curso); 
    	if(novoCurso != null) {
    		return new ResponseEntity<Curso>(novoCurso, HttpStatus.CREATED); 
    	}
    	throw new ErroServidor("Não foi possível salvar o curso especificado. "); 
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Curso> carregarCursoId(@PathVariable long id) {
        
    	Optional<Curso> cursoId = cursoDAO.findAllById(id);
        if (cursoId.isPresent()) {
            return new ResponseEntity<Curso>(cursoId.get(), HttpStatus.OK);
        } else {
            throw new NaoEncontrado("Curso não encontrado");
        }
    }
    
    public ResponseEntity<Curso> editarCurso(@RequestBody Curso novoCurso, @PathVariable long id){
    	Curso curso = this.carregarCursoId(id).getBody(); 
    	if(novoCurso.getNome() != null) {
    		curso.setNome(novoCurso.getNome());
    	}
    	
    	return new ResponseEntity<Curso>(curso, HttpStatus.NO_CONTENT); 
    }
    
    // DISCIPLINAS
    
    @GetMapping(path="/{id}/disciplinas")
    public ResponseEntity<List<Disciplina>> listarDisciplinaPorCurso(@PathVariable long id){
    	Curso curso = this.carregarCursoId(id).getBody(); 
    	if(curso.getDisciplinas() == null) {
    		throw new NaoEncontrado("Não foi possível listar. "); 
    	}
    	return new ResponseEntity<List<Disciplina>>(curso.getDisciplinas(), HttpStatus.OK); 
    }
    
    @PostMapping(path="/{id}/disciplinas")
    public ResponseEntity<Disciplina> novaDisciplina(@RequestBody Disciplina disciplina, @PathVariable long id){
    	Curso curso = this.carregarCursoId(id).getBody();
    	if(disciplina.getCargaHoraria() == 0) {
    		throw new RequisicaoInvalida("Você não pode inserir uma disciplina com carga horária igual ou menor que zero. "); 
    	}
    	if(disciplina.getNome().isEmpty()) {
    		throw new RequisicaoInvalida("Você não pode inserir uma disciplina sem nome. "); 
    	
    	}

    	curso.getDisciplinas().add(disciplina);
    	Curso novoCurso = cursoDAO.save(curso); 
    	return new ResponseEntity<Disciplina>(novoCurso.getDisciplinas().get(novoCurso.getDisciplinas().size()-1), HttpStatus.CREATED); 
    }
    
    @GetMapping(path="/{id}/disciplinas/{idDisciplina}")
    public ResponseEntity<Disciplina> carregarDisciplina(@PathVariable long id, @PathVariable long idDisciplina){
    	Optional<Disciplina> d= disciplinaDAO.findById(idDisciplina);
    	if(d.isPresent()) {
    		return new ResponseEntity<Disciplina>(d.get(), HttpStatus.OK); 
    	}
    	throw new RequisicaoInvalida("Não foi possível encontrar a disciplina requisitada."); 
    }
    
    @PatchMapping(path="/{id}/disciplinas/{idDisciplina}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable long id, @PathVariable long idDisciplina, @RequestBody Disciplina novaDisciplina){
    	Curso curso = this.carregarCursoId(id).getBody(); 
    	Disciplina d = this.carregarDisciplina(id, idDisciplina).getBody();
    	
    	if(novaDisciplina.getCargaHoraria() > 0) {
    		d.setCargaHoraria(0);
    	}
    	if(!novaDisciplina.getNome().isEmpty()) {
    		d.setNome(novaDisciplina.getNome());
    	}
    	disciplinaDAO.save(d); 
    	return null; 
    }
    
    
}
