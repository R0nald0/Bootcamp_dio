package model;

public class Curso extends Conteudo {
    
    private int cargaHoraria ;
    
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }


    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }


    public Curso(){
         
    }

    
    @Override
    public double calcularXp() {
        return  XP_PADRAO * cargaHoraria;
    }

   
    @Override
    public String toString() {
        return "Curso [cargaHoraria=" + cargaHoraria + ", titulo=" + getTitulo() + ", descricao=" + getDescricao()+ "]";
    }

   
    
    
}
