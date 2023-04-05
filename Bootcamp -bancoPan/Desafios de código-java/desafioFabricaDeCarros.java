import java.util.Scanner;

public class desafioFabricaDeCarros{

	public static void main(String[] args) {
        /* 
         * O custo de um carro novo ao consumidor é a soma do custo de 
         * fábrica com a porcentagem do distribuidor e dos impostos 
         * (aplicados ao custo de fábrica). O gerente de uma loja de 
         * carros gostaria de um programa para calcular o preço de um 
         * carro novo para os clientes. Você receberá o custo de fábrica 
         * e as porcentagens referentes ao distribuidor e os impostos e 
         * deverá escrever programa para ler esses valores e imprimir o 
         * valor final do carro.
         */

	  //  Scanner scan = new Scanner(System.in);
        int custoFabrica = 30000;
	      int porcentagemDistribuidor = 10;
	      int PercentualImpostos = 10;

            int custoConsumidor;
            int Distribuidor;
            int ValorImpostos;
           
        // TODO: Implemente uma condição que calcule a porcentagem do distribuidor e dos impostos:
           
        //calculo custo do carro 
    
          
 
        Distribuidor = (custoFabrica * porcentagemDistribuidor) / 100;
        ValorImpostos = (custoFabrica * PercentualImpostos) / 100;
         custoConsumidor = custoFabrica +Distribuidor + ValorImpostos;
      
        System.out.println(custoConsumidor);
	}
}