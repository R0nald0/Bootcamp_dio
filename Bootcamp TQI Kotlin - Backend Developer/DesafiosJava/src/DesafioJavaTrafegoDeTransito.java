import java.util.HashMap;
import java.util.Map;

/*Desafio
        Crie um programa que simule o comportamento de um semáforo.
        O usuário deverá inserir um número correspondente à cor do
        semáforo (1 para vermelho, 2 para amarelo, 3 para verde), e o
        programa deve imprimir uma mensagem indicando qual a ação
        que deve ser tomada com base na cor selecionada.

        Para isso, utilize estruturas de controle de fluxo, como a
        instrução if-else if para tomar decisões com base no valor
        de entrada do usuário.

        Entrada
        O programa espera um número inteiro correspondente à cor do
        semáforo (1 para vermelho, 2 para amarelo, 3 para verde).
        O usuário deve inserir esse número através do teclado e pressionar Enter para confirmar a entrada.

        Saída
        O programa imprime uma mensagem na tela informando qual a
         ação que deve ser tomada, com base no número inserido:

        Se o número for 1, o sinal está vermelho e o programa
        deve imprimir "Pare! O sinal está vermelho."
        Se o número for 2, o sinal está amarelo e o programa deve
         imprimir "Atenção! O sinal está amarelo."
        Se o número for 3, o sinal está verde e o programa deve
        imprimir "Siga em frente! O sinal está verde."
        Se o número não for 1, 2 ou 3, o programa deve imprimir
        "Valor inválido! Digite 1, 2 ou 3 para indicar a cor do semáforo."*/
public class DesafioJavaTrafegoDeTransito {
    public static void main(String[] args) {
        int numero = 5;

       /* String vermelho  ="Pare! O sinal esta vermelho.";
        String amarelo  ="Atencao! O sinal esta amarelo.";
        String verde ="Siga em frente! O sinal esta verde.";*/

        Map<Integer,String> sinais = new HashMap<Integer,String>();
            sinais.put(1,"Pare! O sinal esta vermelho.");
            sinais.put(2,"Atencao! O sinal esta amarelo.");
            sinais.put(3,"Siga em frente! O sinal esta verde.");

            String valor = "";
        switch (numero) {
            case 1 -> {
                valor = sinais.get(1);
            }
            case 2 -> {
                valor = sinais.get(2);
            }
            case 3 -> {
                valor = sinais.get(3);
            }
            default -> {
                valor = "Valor invalido! Digite 1, 2 ou 3 para indicar a cor do semaforo.";
            }
        }

        System.out.println(valor);
    }

}