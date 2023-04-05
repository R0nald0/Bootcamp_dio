import java.io.IOException;

/* 
 * Desafio
Há um país denominado Lolipad, todos os habitantes ficam felizes 
em pagar seus impostos, pois sabem que nele não existem políticos 
corruptos e os recursos arrecadados são utilizados em benefício da 
população, sem qualquer desvio. A moeda deste país é o Loli, cujo símbolo é o R$.

Você terá o desafio de ler um valor com duas casas decimais, 
equivalente ao salário de uma pessoa de Loli. Em seguida, 
calcule e mostre o valor que esta pessoa deve pagar de 
Imposto de Renda, segundo a tabela abaixo.



Lembre que, se o salário for R$ 3002.00, a taxa que incide é de 8% 
apenas sobre R$ 1000.00, pois a faixa de salário que fica de R$ 0.00 
até R$ 2000.00 é isenta de Imposto de Renda. No exemplo fornecido (abaixo)
 a taxa é de 8% sobre R$ 1000.00 + 18% sobre R$ 2.00, o que resulta em 
 R$ 80.36 no total. O valor deve ser impresso com duas casas decimais.

Entrada
A entrada contém apenas um valor de ponto flutuante, com duas casas decimais.

Saída
Imprima o texto "R$" seguido de um espaço e do valor total devido de Imposto de 
Renda, com duas casas após o ponto. Se o valor de entrada for menor ou 
igual a 2000, deverá ser impressa a mensagem "Isento".
 * 
 * 
 */

public class desafioTaxaImpostoRenda {
    public static void main(String[] args) throws IOException {
       // Scanner leitor = new Scanner(System.in);
        double renda = 4520.00 ;//leitor.nextDouble();
        double imposto;
     
          //TODO: Agora utilizando estrutura condicional implemente em seu código as condições necessárias para o cálculo da taxa de imposto de renda:
            if(renda <=2000.00){
                System.out.println("Isento");
            }else if ( renda >= 2000.01 && renda <= 3000.00){
                 imposto = calculaTaxaOitoPorCento(renda);
                 System.out.printf("R$ %.2f", imposto);
            }else if (renda >= 3000.01 && renda <= 4500.00){
                 Double faixaTaxaSalario = renda - 2000.00;
                 Double valor = calculaLimit(faixaTaxaSalario,1000.00);
                 imposto = calcularTaxaSalario(valor, 8.0);

                 Double faixaTaxaSalario2 = faixaTaxaSalario - valor;
                 imposto += calcularTaxaSalario(faixaTaxaSalario2, 18.0);
                 System.out.printf("R$ %.2f", imposto);
           
            }else if(renda >4500.00){
                Double faixaTaxaSalario = renda - 2000.00;  //2.520.00
                Double valor = calculaLimit(faixaTaxaSalario,1000.00);
                imposto = calcularTaxaSalario(valor, 8.0);

                Double faixaTaxaSalario2 = faixaTaxaSalario - valor; //1.520
                Double valorLimite = calculaLimit(faixaTaxaSalario2, 1500.00);

                imposto += calcularTaxaSalario(valorLimite, 18.0);
                 
                Double faixaTaxaSalario3 = faixaTaxaSalario2 - valorLimite;
                imposto +=  calcularTaxaSalario(faixaTaxaSalario3, 28.0);
                  
                System.out.printf("R$ %.2f",imposto);
            }

        
            }

            private static Double calculaLimit(double valor,Double limite) {
                        if (valor >=limite) {
                             return limite;
                        }else{
                            return valor;
                     }
                
                }
  

    private static Double calculaTaxaOitoPorCento(double renda) {
         Double taxaSalario = renda - 2000.00;
         Double valor = calcularTaxaSalario(taxaSalario, 8.0);
        return valor;
    }


  
        public static Double calcularTaxaSalario(Double salario ,Double porcetagemImpostoRenda){
              Double valorDevidoImposto =  (salario * (porcetagemImpostoRenda /100));
               return valorDevidoImposto;
            }

 }
