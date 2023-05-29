/*
Desafio
        Astrônomos e astrofísicos utilizam diferentes unidades
        de tempo para medir o tempo em outros
         planetas. Neste desafio de código, você deverá criar um
         programa que solicite a idade de uma pessoa em anos terrestres e calcule a
         idade equivalente em outros planetas. Para isso, considere as seguintes
         informações:

        Marte: 1 ano marciano dura aproximadamente 1,88 anos terrestres;
        Venus: 1 ano venusiano dura aproximadamente 0,62 anos terrestres;
        Jupiter: 1 ano jupiteriano dura aproximadamente 11,86 anos terrestres.
        Entrada
        Solicitação da idade em anos terrestres através da entrada
        de dados do usuário;
        Solicitação do planeta desejado (Marte, Venus ou Jupiter)
        através da entrada de dados do usuário.
        O usuário deve fornecer sua idade em anos terrestres e o
        planeta para o qual deseja converter a idade.

        Saída
        Exibição da idade equivalente em anos planetários de acordo
        com o planeta escolhido pelo usuário.
        A saída deve ser apresentada em uma mensagem na tela, informando
        a idade equivalente em anos planetários para o planeta
        escolhido, com duas casas decimais após a vírgula.
        Se o planeta informado pelo usuário não for um dos
        três planetas mencionados acima, a mensagem "Planeta
        invalido." deve ser exibida.
           */

import java.util.HashMap;
import java.util.Map;

public class DesafioJavaIdadePlanetária {
    public static void main(String[] args) {
       //Scanner scanner = new Scanner(System.in);
        double idadeTerrestre =  30 ;//scanner.nextDouble();
        String planeta =  "Jupiter" ; // scanner.next();
        Double idadePlaneta  = 0.0;
        /*Map<String,Double> planetas = new HashMap<String,Double>();
         planetas.put("Venus",0.62);
         planetas.put("Marte",1.88);
         planetas.put("Jupiter",11.86);*/

         /*switch (planeta){
             case "Venus" : idadePlaneta = planetas.get("Venus") ;
                       break;
             case "Marte" : idadePlaneta = planetas.get("Marte") ;
                 break;
             case "Jupiter" : idadePlaneta = planetas.get("Jupiter") ;
                 break;
             default : { idadePlaneta = 0.0;}
         }*/
         switch (planeta){
             case "Venus": idadePlaneta =0.62;
                 break;
             case "Marte": idadePlaneta =1.88;
                 break;
             case "Jupiter": idadePlaneta =11.86;
              break;
             default : System.out.println("Planeta invalido.");
         }
        //TODO: Crie as condições para calcular a idade equivalente de cada planeta.
          if (idadePlaneta != 0.0){
              Double idadeCalculada  = calcularIdade(idadeTerrestre,idadePlaneta);
              exibirIdade(idadeCalculada , planeta);
          }

        //TODO: Imprima a saída conforme descrito neste desafio.

        //scanner.close();
    }

    public static void exibirIdade(double idade , String planetaName){
        System.out.printf("Idade equivalente  em "+ planetaName + ": %.2f  anos",idade);
    }
    public static Double calcularIdade(double idadePessoa ,double idadePlaneta){
         return  idadePessoa / idadePlaneta;
    }

}
