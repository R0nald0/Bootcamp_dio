import java.io.IOException;
import java.util.Scanner;


/* 
 * Desafio
Leia 3 valores reais (A, B e C) e verifique se eles formam ou não um triângulo. 
Em caso positivo, calcule o perímetro do triângulo (soma de todos os lados) e apresente a mensagem:

Perimetro = XX.X

Em caso negativo, calcule a área do trapézio que tem A e B como base e C 
como altura, mostrando a mensagem:

Area = XX.X

Fórmula da área de um trapézio: AREA = ((A + B) x C) / 2

Entrada
A entrada contém três valores reais.

Saída
O resultado deve ser apresentado com uma casa decimal.
 * 
 * 
 */
public class desafioTriangulo {

public static void main(String[] args) throws IOException {
		Scanner leitor = new Scanner(System.in);
		double A = 6.0;
		double B = 4.0;
		double C = 2.0;
		boolean triangulo;
		
//TODO: Implemente a condição necessária para o cálculo do triângulo:
//Dica: Você pode utilizar o String.format() na formatação do texto.
		  
            triangulo = verificaMaiorLado(A, B, C);   
            

           if (triangulo) {
                Double perimetro = A+ B + C;
                System.out.println( "Perimetro = " + perimetro);
           }else{
            Double area = ((A+B) * C) /2;
            System.out.println( "Area = " +area );
           } 
    
    }
	

    private static boolean verificaTriangulo(double somaValores, double maiorLado){
        if (  maiorLado < somaValores) {
            return true;
        }else{
          return  false;
        }	
    }
    public static Boolean verificaMaiorLado(Double valorA,Double valorB,Double valorC){
        if(valorA > valorB && valorA >valorC){
                 Double soma = valorB + valorC;
                return verificaTriangulo(soma, valorA);
              
        }else if(valorB > valorC && valorB >valorA){
            Double soma = valorC + valorA;
               return  verificaTriangulo(soma, valorB);
        
        
        }else{
            Double soma = valorB + valorA;
            return verificaTriangulo(soma, valorC);
         
        }
    }
    
}
