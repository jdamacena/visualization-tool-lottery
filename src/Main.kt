@file:Suppress("SpellCheckingInspection")

import java.text.NumberFormat
import java.util.Locale

fun Double.formatarDinheiro(locale: Locale = Locale.of("pt", "BR")): String {
    val nf = NumberFormat.getCurrencyInstance(locale)
    return nf.format(this)
}

fun Long.formatarDinheiro(locale: Locale = Locale.of("pt", "BR")): String {
    val nf = NumberFormat.getCurrencyInstance(locale)
    return nf.format(this)
}

fun Int.formatarQuantidade(locale: Locale = Locale.of("pt", "BR")): String {
    val nf = NumberFormat.getNumberInstance(locale)
    return nf.format(this)
}

fun main() {
    val chancesDeGanhar = 50_000_000 // no formato 1 chance em [variável] ex. 1 em 500
    val precoApostaEmReais = 5.50
    val valorPremioEmReais = 50_000_000.00
    val numSimulacoes = 10

    val totalApostasFeitas = mutableListOf<Int>()

    (1..numSimulacoes).forEach { _ ->
        var qtdApostas = 0
        var ganhouNaLoteria = false

        while (!ganhouNaLoteria) {
            qtdApostas++

            val totalGastoEmApostas = qtdApostas * precoApostaEmReais

            val numeroAposta = (1..chancesDeGanhar).random()
            val numeroSorteio = (1..chancesDeGanhar).random()

            if (numeroSorteio == numeroAposta) {
                ganhouNaLoteria = true

                val lucro: Double = valorPremioEmReais - totalGastoEmApostas

                println("\n".padEnd(50, '-'))
                println("Parabéns, você ganhou na loteria com ${qtdApostas.formatarQuantidade()} apostas")
                println("\tVocê gastou: ${totalGastoEmApostas.formatarDinheiro()}")
                println("\tVocê ganhou: ${valorPremioEmReais.formatarDinheiro()}")
                println("\tLucro:       ${lucro.formatarDinheiro()}")
                println("\n".padStart(50, '-'))
            } else {
                if (qtdApostas % 1_000_000 == 0)
                    println("Aposta #${qtdApostas.formatarQuantidade()} - Total gasto: ${totalGastoEmApostas.formatarDinheiro()}")
            }
        }
        totalApostasFeitas.add(qtdApostas)
    }

    val totalGanhos = numSimulacoes * valorPremioEmReais
    val totalGastos: Long = (totalApostasFeitas.sum() * precoApostaEmReais).toLong()
    val totalLucro = totalGanhos - totalGastos

    println("\n\nDepois de ${numSimulacoes.formatarQuantidade()} simulações e ${totalApostasFeitas.sum().formatarQuantidade()} apostas")
    println("\tVocê ganhou:     ${totalGanhos.formatarDinheiro()}")
    println("\tVocê perdeu:     ${totalGastos.formatarDinheiro()}")
    println("\tTotal de ${if (totalLucro  > 0) "lucros" else "perdas"}: ${totalLucro.formatarDinheiro()}")
}














