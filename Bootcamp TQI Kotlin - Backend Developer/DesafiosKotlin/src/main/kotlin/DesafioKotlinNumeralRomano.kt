data class NumeroRomano(val letra : String ,val valor :Int)
fun main() {
    val numeroRomano: String? ="IV" //readLine()
    var resultado = 0

 /*   val listaRomanos = listOf(
        NumeroRomano(letra = "I", valor = 1),
        NumeroRomano(letra = "V", valor =5),
        NumeroRomano(letra = "X", valor = 10),
        NumeroRomano(letra = "L", valor = 50),
        NumeroRomano(letra = "C", valor = 100),
        NumeroRomano(letra = "D", valor = 500),
        NumeroRomano(letra = "m", valor = 1000),
    )*/
    val numerosRomanos = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )


    for (i in numeroRomano!!.indices) {
        val atual =  numerosRomanos.getValue(numeroRomano[i])

        var proximo =  when (i + 1) {
            // Caso o próximo indice não exista, atribui 0 à variável $proximo.
            numeroRomano.length -> 0
            // Caso contrário, atribui o valor em romano equivalente ao próximo indice.
            else -> numerosRomanos.getValue(numeroRomano[i + 1])
        }
        if ( atual >= proximo){
            resultado += atual
        }else{
            resultado -= atual
        }
    }

    print(resultado)
}