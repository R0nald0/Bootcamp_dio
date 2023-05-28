fun main(){
  /*  Desafio
    Geronimo acredita que perde muito tempo lembrando qual número do mês
    representa cada um deles. Ele precisa de um programa que mude os meses do ano do
    calendário do celular dele para facilitar e agilizar a leitura. Ajude-o e faça um
    programa que, com uma determinada entrada de uma data, retorne essa data com o mês
    escrito por extenso.

    Entrada
    As entradas serão datas em formato numeral. O dia ou mês que possuirem somente
    um digito, terão um 0 (zero) na frente.

    Saida
    As saídas serão as mesmas datas da entrada porém, com o mês escrito por extenso.
    O mês deve ter a primeira letra em maiúsculo.
    */
    val entrada: String? = "1/5/1995"//readLine()

    // Utiliza o conceito de "destructuring" para atribuir cada parte da data (dia/mes/ano).
    // Referência: https://kotlinlang.org/docs/destructuring-declarations.html
    val (dia, mes, ano) = entrada!!.split("/")

    val mesPorExtenso = when (mes.toInt()) {
        1-> "Janeiro"
        2-> "Fevereiro"
        3-> "Marco"
        4-> "abril"
        5-> "Maio"
        6-> "Junho"
        7-> "Julho"
        8-> "Agosto"
        9-> "Setembro"
        10-> "Outubro"
        11-> "Novembro"
        12-> "Dezembro"
        else -> "Mês Inválido!"
    }

    println ("$dia de $mesPorExtenso de $ano")
}