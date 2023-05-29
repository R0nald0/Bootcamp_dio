import java.sql.Array;
import java.util.ArrayList;

/*Desafio
        Crie um programa em Java para calcular o valor total
        de uma compra de livros em uma livraria. O programa
        deve perguntar o nome, o preço e a quantidade de cada
        livro que o cliente deseja comprar. O programa deve exibir
        o valor total da compra e o número de livros comprados.

        Entrada
        A entrada consiste em dados informados pelo usuário via
        teclado. O usuário deve informar o nome do
        livro (uma string sem espaços), o preço do livro (um número real)
         e a quantidade de livros desejados (um número inteiro).

        Saída
        A saída consiste em mensagens exibidas na tela do usuário.
        O programa deve exibir o valor total da compra, formatado
        com duas casas decimais, seguido do número de livros comprados.
        Por fim, o programa deve exibir a mensagem "Obrigado por comprar
        na nossa livraria!"*/
public class DesafioJavaComprasNaLivraria {
    public static void main(String[] args ){
        //Scanner scanner = new Scanner(System.in);

        // Instancia 2 objetos do tipo "Livro" com os dados de entrada.
        Livro livro1 = new Livro("Senhor dos Anéis - As Duas Torres", 29.99, 1);
       // scanner.nextLine(); // Limpa o buffer do teclado
        Livro livro2 = new Livro("A Cor que Caiu do Espaço",31.00, 2);

        //TODO: Considerando os objetos "livro1" e "livro2", calcule o valor total da compra.
           Compra compraRetorno = calcularValor(livro1,livro2);

        //TODO: Considerando os objetos "livro1" e "livro2", calcule o número de livros comprados.
             Compra compra = new Compra("",compraRetorno.precoTotal,compraRetorno.quantidade);
             exibirDadosDacaompra(compra);
        //TODO: Imprima as linhas necessárias conforme a saída deste desafio.

    }
    public static void exibirDadosDacaompra(Compra compra){
            System.out.printf("Valor total da compra:%.2f ", compra.precoTotal);
            System.out.println("Número de livros comprados " + compra.quantidade +"\n");
            System.out.println("Obrigado por comprar na nossa livraria!");
    }

     public static Compra calcularValor(Livro...livros){
         Double valorTotal = 0.0;
         int quantidade = 0;
         for (Livro livro : livros) {
             valorTotal += livro.quantidade * livro.preco;
             quantidade += livro.quantidade;
         }
        Compra compra = new Compra("" ,valorTotal,quantidade);
        return compra;
    }
    static class Compra {
        String nomeLivro;
        double precoTotal;
        int quantidade;

        Compra(String nomeLivro, double precoTotal, int quantidade) {
            this.nomeLivro = nomeLivro;
            this.precoTotal = precoTotal;
            this.quantidade = quantidade;
        }
    }
    static class Livro {
        String nome;
        double preco;
        int quantidade;

        Livro(String nome, double preco, int quantidade) {
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
        }
    }
}
