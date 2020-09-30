package com.example.kaiqueferrazado_01_calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var nomeProduto = findViewById(R.id.editTextNomeProduto) as EditText
        var precoVenda = findViewById(R.id.editTextPrecoVenda) as EditText
        var precoCusto = findViewById(R.id.editTextPrecoCusto) as EditText
        var btSalvar = findViewById(R.id.btSalvar) as Button
        var btAbrir = findViewById(R.id.btAbrir) as Button
        var btCalcular = findViewById(R.id.btCalcular) as Button


        val sh = getSharedPreferences("produtos", Context.MODE_PRIVATE) // leitura e gravação


        //SALVAR
        btSalvar.setOnClickListener { view ->

            var listaProduto = nomeProduto.text.toString()
            var listaCusto = precoVenda.text.toString()
            var listaPreco = precoCusto.text.toString()

            if (editTextNomeProduto.text.isNotEmpty() && editTextPrecoVenda.text.isNotEmpty() && editTextPrecoCusto.text.isNotEmpty()) {

                sh.edit().putString(listaProduto, listaPreco + ";" + listaCusto).apply()
                Toast.makeText(this, "Produto Cadastrado", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Tente novamente", Toast.LENGTH_SHORT).show()
            }


        }

        //ABRIR
        btAbrir.setOnClickListener { view ->
            var listaProduto = nomeProduto.text.toString()

            var texto = sh.getString(listaProduto, "")

            if (texto!!.isNotEmpty()) {

                var lista = texto.split(";")

                var preco = lista.get(0)
                var venda = lista.get(1)

                precoCusto.setText(preco)
                precoVenda.setText(venda)

                Toast.makeText(this, "Produto ok", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Produto não localizado", Toast.LENGTH_SHORT).show()
            }
        }


        //LIMPAR
        btLimpar.setOnClickListener { v: View? ->
            editTextNomeProduto.text.clear()
            editTextPrecoCusto.text.clear()
            editTextPrecoVenda.text.clear()


        }

        //CALCULAR
        btCalcular.setOnClickListener { view ->


            var custo = precoCusto.text.toString().toDouble()
            var venda = precoVenda.text.toString().toDouble()


            if (custo < venda) {
                Toast.makeText(
                        this, "Voce teve um lucro de: $ " + (venda - custo) + " reais", Toast.LENGTH_SHORT
                ).show()
            } else if (custo == venda) {
                Toast.makeText(this, "Não obteve lucro nem prejuizo nesta venda", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(
                        this,
                        "Você teve um prejuizo de: $ " + (custo - venda) + " reais",
                        Toast.LENGTH_SHORT
                ).show()

            }

        }




    }

}