package me.academiadio.AcademiaApi.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.academiadio.AcademiaApi.controller.model.mappers.AlunoMapper;
import me.academiadio.AcademiaApi.entidades.Aluno;
import me.academiadio.AcademiaApi.entidades.dto.AlunoDto;
import me.academiadio.AcademiaApi.repository.AlunoRepository;
import me.academiadio.AcademiaApi.service.AlunoService;

@Service

public class AlunoServiceImpl implements AlunoService  {
    @Autowired
     AlunoRepository alunoRepository ;
     @Autowired
     AlunoMapper alunoMapper ;

    @Override
    public Aluno criarAluno(AlunoDto alunoDto) {
              Aluno aluno = alunoMapper.convertAlunoDtoToAluno(alunoDto);
              
      return  alunoRepository.save(aluno);   
    }

    @Override
    public List<AlunoDto> buscasTodos() {
        return  alunoMapper.convertToListAlunoDto( alunoRepository.findAll());
    }

    
}
