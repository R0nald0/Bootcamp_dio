package me.academiadio.AcademiaApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.academiadio.AcademiaApi.entidades.Aluno;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno,Long>{
    
}
