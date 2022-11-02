import model.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Curso curso = new Curso();
        curso.setTitulo("Angular Iniciante");
        curso.setDescricao("Iniciando Angular");
        curso.setCargaHoraria(32);

        Mentoria mentoria = new Mentoria();
          mentoria.setTitulo("JavaScript intermediario");
          mentoria.setDescricao("Principais técnicais");
          mentoria.setData(LocalDate.now());

        Bootcamp bootcamp = new Bootcamp();
            bootcamp.setNome("Java e JS");
            bootcamp.getConteudos().add(curso);
            bootcamp.getConteudos().add(mentoria);

        Dev dev1 = new Dev();
            dev1.setNome("dev1");
            dev1.setConteudosinscritos(bootcamp.getConteudos());
            System.out.println("Conteudos inscritos "+ dev1.getConteudosinscritos() +" xp =" + dev1.calcaularXp());
            dev1.progredir();
            dev1.progredir();
            System.out.println("Conteudos inscritos "+ dev1.getConteudosinscritos()  );


    }
}
