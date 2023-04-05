import java.util.Scanner;

public class imprimindoPositivosEMedia{

/* 
Desafio
Leia 6 valores. Em seguida, mostre quantos destes 
valores digitados foram positivos. Na próxima linha, 
deve-se mostrar a média de todos os valores positivos 
digitados, com um dígito após o ponto decimal.

Entrada
A entrada contém 6 números que podem ser valores 
inteiros ( int ) ou de ponto flutuante ( float ou double ). 
Pelo menos um destes números será positivo.

Saída
O primeiro valor de saída é a quantidade de valores positivos.
 A próxima linha deve mostrar a média dos valores positivos 
 digitados.
 */
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        // Double varia =  leitor.nextDouble();
         // System.out.println( varia );
       /* int val1 = 7;
        int val2 = -5;
       int  val3 = 6;
       Double val4 = -3.4;
        Double val5 =   4.6;
       int val6 =   12;   */    
        Double[] val ={7.0,-5.0,6.0,-3.4,4.6,12.0}; 
      
        int cont = 0;
        double media = 0;
        double x = 0.0;
   
        //TODO: Implemente as condições adequadas para obter a quantidade 
       //de valores positivos e a média dos valores positivos:
           for (int i = 0 ; i < val.length ; i++) {
                        if (val[i] > 0) {
                              cont++;
                              x += val[i];
                        }
                             
               }
               System.out.println("total = " + x);
           
           
           media =  x / cont ;
           System.out.println(cont + " valores positivos");
           System.out.println(String.format("%.1f", media)); 
    }
}