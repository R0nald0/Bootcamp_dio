package me.academiadio.AcademiaApi.service;

import java.util.List;

import me.academiadio.AcademiaApi.entidades.Aluno;

public interface AlunoService {

   
    public Aluno criarAluno(Aluno aluno);
    public List<Aluno> buscasTodos();
}
