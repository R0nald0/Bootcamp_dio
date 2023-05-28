fun main (){
    /*Desafio
    Faça um programa que receba a média de um aluno e imprima o seguinte:

    Caso a média seja < 5 imprima "REP";
    Caso a média seja >= 5 e < 7 imprima "REC";
    Caso a média seja >7 imprima "APR".
    Entrada
    A entrada consiste em vários arquivos de teste, cada um com uma linha, que conterá o
    valor da média do aluno. Conforme mostrado no exemplo de entrada a seguir.

    Saída
    Para cada arquivo da entrada, terá um arquivo de saída. E como mencionado
    no Desafio, será gerado uma linha com a palavra "Reprovado" caso sua média
    seja < 5, "Recuperação" caso sua média seja >= 5 e < 7 e "Aprovado" caso a média seja > 7.
     Use o exemplo abaixo para clarear o que está sendo pedido.
     */

    val media = readLine()!!.toDouble();
    when(media.toDouble()) {
        in 0.0..5.0  -> println("REP")
        in 5.0..7.0  -> println("REC")
        in 7.1 .. 10000.0   -> println("APR")

        else -> TODO("Criar demais condições para a resolução deste desafio.")
    }
}