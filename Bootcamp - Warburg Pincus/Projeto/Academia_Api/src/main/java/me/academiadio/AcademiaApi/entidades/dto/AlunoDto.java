package me.academiadio.AcademiaApi.entidades.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    
    private  String nome;
    private String cpf;

    private String bairro; 
   
    private LocalDate dataDeNascimento; 
    
}
