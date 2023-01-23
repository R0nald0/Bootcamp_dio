package me.academiadio.AcademiaApi.controller.model.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import me.academiadio.AcademiaApi.entidades.Aluno;
import me.academiadio.AcademiaApi.entidades.dto.AlunoDto;

@Component
public class AlunoMapper {
    
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

     public Aluno convertAlunoDtoToAluno(AlunoDto alunoDto){
         return MODEL_MAPPER.map(alunoDto, Aluno.class);
     }

     public AlunoDto convertAlunoToAlunoDto (Aluno aluno){
         return MODEL_MAPPER.map(aluno, AlunoDto.class);
        }

        public List<AlunoDto> convertToListAlunoDto(List<Aluno> alunos){
            return alunos.stream()
              .map(this::convertAlunoToAlunoDto)
              .collect(Collectors.toList());
        }

}
