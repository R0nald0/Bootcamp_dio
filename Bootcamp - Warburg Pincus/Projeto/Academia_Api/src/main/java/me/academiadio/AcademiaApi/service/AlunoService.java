package me.academiadio.AcademiaApi.service;

import java.util.List;

import me.academiadio.AcademiaApi.entidades.Aluno;
import me.academiadio.AcademiaApi.entidades.dto.AlunoDto;

public interface AlunoService {

   
    public Aluno criarAluno(AlunoDto alunodDto);
    public List<AlunoDto> buscasTodos();
}
