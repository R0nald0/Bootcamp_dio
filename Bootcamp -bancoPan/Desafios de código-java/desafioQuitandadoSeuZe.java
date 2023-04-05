

/* Desafio
Seu Zé está vendendo frutas com a seguinte tabela de preços:

Exemplo 1

 	Até 5 Kg	Acima de 5 Kg
Morango	R$ 2,50 por Kg	R$ 2,20 por Kg
Maçã	R$ 1,80 por Kg	R$ 1,50 por Kg

Se o cliente comprar mais de 8 Kg em frutas ou o valor total da compra ultrapassar R$ 25,00, 
receberá ainda um desconto de 10% sobre este total. 
Escreva um algoritmo para ler a quantidade (em Kg) de morangos e a quantidade (em Kg) de
 maças adquiridas e escreva o valor a ser pago pelo cliente.

Entrada
Como entrada, você recebera a quantidade em kg de morangos e a 
quantidade em kg de maçãs.

Saída
Será o valor a ser pago pelo cliente, conforme a tabela de preços 
da quintanda do seu Zé.
 * 
 * 
 */

public class desafioQuitandadoSeuZe {
        public static void main(String[] args) {
        
           // Scanner input = new Scanner(System.in);
           int morangos = 5;
           int macas = 5;

           /* 
            *  Morango	R$ 2,50 por Kg	R$ 2,20 por Kg
                 Maçã	R$ 1,80 por Kg	R$ 1,50 por Kg 

            */
            //TODO: Implemente as condições necessárias para retornar o preço a ser pago pelo cliente, conforme a tabela de preços do seu Zé:

           Double valorMornagoKg = 2.50;
           Double valorMornagoAcimaDeCincoKg = 2.20;
           Double valormMacaKg = 1.80;
           Double valormMacaAcimaDeCincoKg = 1.50;
                                              
           Double valor8kg = 17.6;
           double deconto = 10.0;
           
            
            Double valorDeCompraMacã = verificaValorFrutaPorkg(macas, valormMacaKg, valormMacaAcimaDeCincoKg); 
            Double valorDeCompraMorango = verificaValorFrutaPorkg(morangos, valorMornagoKg, valorMornagoAcimaDeCincoKg);

            /* if( macas <= 5)
               { valorDeCompraMacã = valormMacaKg;}
               else{valorDeCompraMacã = valormMacaAcimaDeCincoKg;}     
            
            if( morangos <= 5)
               { valorDeCompraMorango = valorMornagoKg;}
               else{valorDeCompraMorango = valorMornagoAcimaDeCincoKg;}  */  
           
            
            double valorCompra =  calcularValorCompra(
            macas, morangos,valorDeCompraMacã , valorDeCompraMorango);

           if(valorCompra > valor8kg || valorCompra > 25.0 ){
            Double valorProduto = apilicarDesconto(deconto, valorCompra);
            System.out.println(valorProduto);
           }else{
            System.out.println(valorCompra);
           }
           
         
        }
        
        public static Double verificaValorFrutaPorkg(Integer kgfruta , Double precoNormal,Double precoComDesconto){
            if( kgfruta <= 5){
                   return precoNormal;
            }else{
                return precoComDesconto;
            } 
        }

        public static Double calcularValorCompra(Integer kgMAcas , Integer kgMorangos,Double valorMaca, Double valorMorango){
              Double valor = (kgMAcas * valorMaca) + ( kgMorangos * valorMorango);       

            return valor;
        }   

        public static Double apilicarDesconto(Double desconto, Double valorTotal){
            Double valorComDesconto = valorTotal - ( valorTotal * (desconto/100));
               
            return valorComDesconto;
        }
}
