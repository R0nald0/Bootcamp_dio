
/*
Desafio
        Neste desafio, você deverá ajudar o gerente de uma loja
        geek a gerenciar o desempenho de seus funcionários em um
        determinado período. O gerente pode inserir a média das avaliações de desempenho dos funcionários e o programa irá classificar cada um deles em "APR" (bom), "MED" (médio) ou "REP" (ruim), com base em critérios predefinidos.

        Entrada
        O programa solicitará ao usuário que insira a média das
        avaliações de desempenho dos funcionários em um determinado período.

        Saída
        O programa irá classificar cada funcionário
        em "APR" (bom), "MED" (médio) ou "REP" (ruim), de
        acordo com as seguintes regras:

        Se a média da avaliação for menor que 5, o
        funcionário estará reprovado em seu teste e
        será classificado como "REP";
        Se a média da avaliação for menor que 7, mas
        maior ou igual a 5, o funcionário terá sua
        avaliação como "MED";
        Se a média for maior ou igual a 7, o funcionário está
         aprovado no teste de desempenho e será classificado
         como "APR".*/
public class DesafioJavaGerenciamentoDeLojaGeek {
    public static void main( String[] args){
       // Scanner scanner = new Scanner(System.in);

        Double media =  7.0 ;//scanner.nextDouble();

        //TODO: Verifique a média lida e imprima a avaliação do funcionário.
        //Onde: "APR" é bom, "MED" é médio e "REP" é ruim.
        if (media < 5) {
            System.out.println("REP");
        } else if (media >= 5 && media < 7) {
            System.out.println("MED");
        } else if (media >=  7) {
            System.out.println("APR");
        }
    }
}