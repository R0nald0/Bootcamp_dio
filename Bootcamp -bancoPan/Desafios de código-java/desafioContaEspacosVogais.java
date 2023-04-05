
/* 
 * Desafio
Jorginho é professor do primário de uma determinada escola. 
Ele tem 100000 alunos e precisa criar um programa que verifica 
quantos espaços em branco e quantas vogais existem em uma 
determinada string de entrada que os alunos entregaram na avaliação 
final. Ajude-o a realizar essa tarefa antes que o tempo para 
entrega-la no fim do semestre acabe!

Entrada
A entrada será uma frase no formato de string. 

Saída
A saída deverá retornar quantos espaços em branco e quantas 
vogais existem na determinada string, conforme exemplo abaixo:
 */


public class desafioContaEspacosVogais {
    public static void main(String[] args) {
       // Scanner sc = new Scanner(System.in);
        String str ="Amo a DIO";
        String[] strSplit = str.split(" ");
        char[] arrVogais = {'a', 'e', 'i', 'o', 'u'};
        int espacosEmBranco = strSplit.length - 1, 
        quantVogais = 0;
       
       
       for(int i = 0; i <str.length(); i++){
          
          char item = str.toLowerCase().charAt(i);
           for (char vogal : arrVogais) {
                if(item == vogal){quantVogais += 1;}
           }
           
       }
    
      //TODO: Implemente condições (laços) adequados para que possamos contar os espaços em brancos e as vogais que existem na string.
     //Dica: Você pode utilizar o Character.toLowerCase em sua condição:      
       
       System.out.println("Espacos em branco: " + espacosEmBranco + " Vogais: " + quantVogais);
   
    }
}
