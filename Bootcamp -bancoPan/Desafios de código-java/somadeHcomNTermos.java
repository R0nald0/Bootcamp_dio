import java.util.Scanner;

public class somadeHcomNTermos {
    public static void main(String[] Args) {
/* 
Desafio
Neste desafio, faça um programa que calcule o valor de H com N termos. 

Sendo, H = 1 + 1/2 + 1/3 + 1/4 + ... + 1/N. 

Entrada 
A entrada consiste em um número inteiro positivo. 

Saída 
Na saída será impresso o valor que representa a soma dos termos. */

        Double h = 0.0;
        //Scanner sc = new Scanner(System.in);
        double n = 8;
   
        for (int i = 1; i <= n; i++) {
        //TODO: Calcule o valor de H de forma que resulte na soma dos termos:
               h += 1d/i;
         }
        //TODO: Imprima a soma dos termos, considerando um resultado com duas casas decimais:
        System.out.println(Math.round(h));
          
      }
}
