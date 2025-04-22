package com.example.minecraft12302120

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var materialSpinner: Spinner
    private lateinit var construtoresEditText: EditText
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        materialSpinner = findViewById(R.id.escolha)
        construtoresEditText = findViewById(R.id.editTextConstrutores)
        calcularButton = findViewById(R.id.btn_calcular)
        resultadoTextView = findViewById(R.id.resultado)


        val materiais = arrayOf("Madeira", "Ouro", "Diamante")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, materiais)
        materialSpinner.adapter = adapter

        calcularButton.setOnClickListener {
            calcularTempoConstrucao()
        }
    }

    private fun calcularTempoConstrucao() {
        val materialSelecionado = materialSpinner.selectedItem.toString()
        val numConstrutoresStr = construtoresEditText.text.toString()

        if (numConstrutoresStr.isEmpty()) {
            resultadoTextView.text = "Por favor, insira o número de construtores."
            return
        }

        val numConstrutores = numConstrutoresStr.toInt()

        if (numConstrutores <= 0) {
            resultadoTextView.text = "O número de construtores deve ser maior que zero."
            return
        }

        var tempoBase: Double = 0.0

        when (materialSelecionado) {
            "Madeira" -> tempoBase = 100.0
            "Ouro" -> tempoBase = 50.0
            "Diamante" -> tempoBase = 25.0
        }

        val tempoTotal = tempoBase / 2.0.pow((numConstrutores - 1).toDouble())

        val formatoTempo = NumberFormat.getInstance(Locale("pt", "BR"))
        formatoTempo.maximumFractionDigits = 2
        val tempoFormatado = formatoTempo.format(tempoTotal)

        resultadoTextView.text = "Tempo estimado de construção: $tempoFormatado horas"
    }
}