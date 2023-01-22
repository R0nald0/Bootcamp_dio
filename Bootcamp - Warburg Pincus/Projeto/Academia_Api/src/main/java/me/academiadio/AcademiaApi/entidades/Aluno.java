package me.academiadio.AcademiaApi.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Aluno {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private Long id;

   private  String nome;
   @Column(unique = true,nullable = false)
   private String cpf;

   private String bairro;


   private LocalDate dataDeNascimento;      
}
