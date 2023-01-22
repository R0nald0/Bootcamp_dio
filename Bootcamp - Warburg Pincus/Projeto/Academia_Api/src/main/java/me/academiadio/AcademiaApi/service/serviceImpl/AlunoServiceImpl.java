package me.academiadio.AcademiaApi.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.academiadio.AcademiaApi.entidades.Aluno;
import me.academiadio.AcademiaApi.repository.AlunoRepository;
import me.academiadio.AcademiaApi.service.AlunoService;

@Service

public class AlunoServiceImpl implements AlunoService  {
    @Autowired
     AlunoRepository alunoRepository ;

    @Override
    public Aluno criarAluno(Aluno aluno) {
      return  alunoRepository.save(aluno);    
    }

    @Override
    public List<Aluno> buscasTodos() {
           return alunoRepository.findAll();
    }

    
}
