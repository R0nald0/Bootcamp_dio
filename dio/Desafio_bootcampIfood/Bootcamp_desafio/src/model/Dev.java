package model;


import java.util.*;


public class Dev {
    
   private  String nome ;
   private Set<Conteudo> conteudosinscritos = new LinkedHashSet<>();
   private Set<Conteudo> conteududosEncerrados = new LinkedHashSet<>();

   void inscreverBootcamp(Bootcamp bootCamp){
      this.conteudosinscritos.addAll(bootCamp.getConteudos());
      bootCamp.getDevsIncritos().add(this);
   }

   public void progredir(){
        Optional<Conteudo> conteudo = this.conteudosinscritos.stream().findFirst();
         if (conteudo.isPresent()){
            this.conteududosEncerrados.add(conteudo.get());
            this.conteudosinscritos.remove(conteudo.get());
         }else {
            System.out.println("sem Conteudos inscritos");
         }

   }
  public double  calcaularXp(){
     return this.conteudosinscritos.stream()
              .mapToDouble(Conteudo::calcularXp).sum();
    }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Dev dev = (Dev) o;
      return Objects.equals(nome, dev.nome) && Objects.equals(conteudosinscritos, dev.conteudosinscritos) && Objects.equals(conteududosEncerrados, dev.conteududosEncerrados);
   }

   @Override
   public int hashCode() {
      return Objects.hash(nome, conteudosinscritos, conteududosEncerrados);
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public Set<Conteudo> getConteudosinscritos() {
      return conteudosinscritos;
   }

   public void setConteudosinscritos(Set<Conteudo> conteudosinscritos) {
      this.conteudosinscritos = conteudosinscritos;
   }

   public Set<Conteudo> getConteududosEncerrados() {
      return conteududosEncerrados;
   }

   public void setConteududosEncerrados(Set<Conteudo> conteududosEncerrados) {
      this.conteududosEncerrados = conteududosEncerrados;
   }
}
