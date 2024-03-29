package me.academiadio.AcademiaApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.academiadio.AcademiaApi.service.serviceImpl.AlunoServiceImpl;
import me.academiadio.AcademiaApi.entidades.Aluno;
import me.academiadio.AcademiaApi.entidades.dto.AlunoDto;


@RestController
@RequestMapping("/academiadio")
public class AlunoController {

  @Autowired
  AlunoServiceImpl alunoService;

  @GetMapping
  public ResponseEntity<List<AlunoDto>> buscasTodos() {
    List<AlunoDto> alunos = alunoService.buscasTodos();
    return ResponseEntity.ok(alunos);
  }

  @PostMapping
  public ResponseEntity<Aluno> salvarAluno(@RequestBody AlunoDto alunoDto) {
    Aluno alunoCreado = alunoService.criarAluno(alunoDto);
    return ResponseEntity.ok(alunoCreado);
  }
}
