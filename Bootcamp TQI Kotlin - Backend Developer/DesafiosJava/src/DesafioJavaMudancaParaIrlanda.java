/*
Desafio
        Você está planejando uma mudança para a Irlanda e precisa
        calcular qual será o seu salário líquido, considerando o
        imposto a ser pago à Receita Federal. Para isso, você precisa
         implementar um programa que receba como entrada o seu salário
         bruto e o valor dos benefícios recebidos
         (como vale-refeição ou vale-transporte) e calcule o imposto
          a ser pago, de acordo com a tabela abaixo:

        Salário Bruto	Alíquota
        Até R$ 1.100,00	5%
        De R$ 1.100,01 até R$ 2.500,00	10%
        Acima de R$ 2.500,00	15%
        O programa deve calcular o Imposto a ser pago de acordo
        com a tabela acima e, em seguida, subtrair esse valor do
        Salário Bruto mais os Benefícios, para obter o Salário
        Líquido.

        Entrada
        Um valor do tipo double correspondente ao Salário Bruto.
        Um valor do tipo double correspondente aos Benefícios recebidos.
        Saída
        Um valor do tipo double correspondente ao salário líquido após
         o pagamento do imposto, formatado com 2 casas decimais.
         Resumidamente, podemos condensar esse cálculo por meio da seguinte fórmula:
         Salário Líquido = Salário Bruto - Imposto + Benefício
        */

public class DesafioJavaMudancaParaIrlanda {
    public static void main(String[] args) {
       // Scanner input = new Scanner(System.in);

        // Leitura do salário e dos benefícios
        double salarioBruto =  1000 ;//input.nextDouble();
        double beneficios =  100;//input.nextDouble();

        //TODO: Implemente as regras para o cálculo do imposto.
        double salarioTaxado = 0;
        if (salarioBruto <= 1100.00){
            salarioTaxado = calcularImposto(salarioBruto,5.0);
        }else if ( salarioBruto >= 1100.01 && salarioBruto <= 2500.00){
            salarioTaxado = calcularImposto(salarioBruto,10.0);
        }
        else if (  salarioBruto >= 2500.00){
            salarioTaxado = calcularImposto(salarioBruto,15.0);
        }

        // Cálculo do salário líquido
        double salarioLiquido = salarioTaxado + beneficios;

        //TODO: Imprimir o salário líquido com 2 casas decimais.

        System.out.printf("%.2f",salarioLiquido);
    }
    public static Double calcularImposto (Double salario,Double imposto){
         Double valorImposto = imposto /100;
        return   salario - ( salario * valorImposto);
    }
}
