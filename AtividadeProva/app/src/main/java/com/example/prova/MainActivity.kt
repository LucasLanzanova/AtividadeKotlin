package com.example.prova

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val campoBaseA = findViewById<EditText>(R.id.etEntradaLargura)
        val campoAlturaA = findViewById<EditText>(R.id.etEntradaAltura)
        val textoSaidaPerimetro = findViewById<TextView>(R.id.tvSaidaPerimetro)
        val textoSaidaArea = findViewById<TextView>(R.id.tvSaidaArea)
        val acaoCalcularGeometria = findViewById<Button>(R.id.btProcessarGeometria)

        acaoCalcularGeometria.setOnClickListener {
            val sLarg = campoBaseA.text.toString()
            val sAlt = campoAlturaA.text.toString()

            if (sLarg.isNotEmpty() && sAlt.isNotEmpty()) {
                val numLarg = sLarg.toDouble()
                val numAlt = sAlt.toDouble()

                val calculoArea = GeometriaUtil.calcularAreaBasePorAltura(numLarg, numAlt)
                val calculoPerimetro = GeometriaUtil.calcularPerimetroAdequado(numLarg, numAlt)

                textoSaidaArea.text = "%.2f".format(calculoArea)
                textoSaidaPerimetro.text = "%.2f".format(calculoPerimetro)
            } else {
                Toast.makeText(this, "Preencha os valores da geometria", Toast.LENGTH_SHORT).show()
            }
        }

        val campoTempoViagem = findViewById<EditText>(R.id.etEntradaTempo)
        val campoVelocidadeMédia = findViewById<EditText>(R.id.etEntradaVelocidade)
        val campoConsumoMédio = findViewById<EditText>(R.id.etEntradaMediaComb)
        val textoSaidaLitros = findViewById<TextView>(R.id.tvSaidaLitros)
        val acaoCalcularViagem = findViewById<Button>(R.id.btProcessarViagem)

        acaoCalcularViagem.setOnClickListener {
            val stTempo = campoTempoViagem.text.toString()
            val stVelocidade = campoVelocidadeMédia.text.toString()
            val stConsumo = campoConsumoMédio.text.toString()

            if (stTempo.isNotEmpty() && stVelocidade.isNotEmpty() && stConsumo.isNotEmpty()) {
                val nTempo = stTempo.toDouble()
                val nVelocidade = stVelocidade.toDouble()
                val nConsumo = stConsumo.toDouble()

                if (nConsumo > 0) {
                    val nDistancia = CombustivelUtil.calcularDistanciaPercorrida(nTempo, nVelocidade)
                    val nLitrosNecessarios = CombustivelUtil.calcularLitrosNecessarios(nDistancia, nConsumo)
                    textoSaidaLitros.text = "%.2f Lts".format(nLitrosNecessarios)
                } else {
                    Toast.makeText(this, "O consumo não pode ser zero", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os dados da viagem", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

object GeometriaUtil {
    fun calcularAreaBasePorAltura(b: Double, h: Double): Double {
        return b * h
    }

    fun calcularPerimetroAdequado(b: Double, h: Double): Double {
        return if (b == h) {
            4 * b
        } else {
            2 * (b + h)
        }
    }
}

object CombustivelUtil {
    fun calcularDistanciaPercorrida(tempo: Double, velocidade: Double): Double {
        return tempo * velocidade
    }

    fun calcularLitrosNecessarios(distancia: Double, media: Double): Double {
        return distancia / media
    }
}