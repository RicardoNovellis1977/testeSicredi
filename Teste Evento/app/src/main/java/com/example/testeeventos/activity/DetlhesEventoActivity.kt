package com.example.testeeventos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.testeeventos.R
import com.example.testeeventos.adapter.ListaEventoAdapter.Companion.EVENTO_ID
import com.example.testeeventos.util.Converter
import com.example.testeeventos.util.Converter.Companion.adicionarImageEvento
import com.example.testeeventos.util.Converter.Companion.inserirTexto
import com.example.testeeventos.viewmodel.ViewModelDetailEvento
import com.example.testeeventos.viewmodel.ViewModelEventos
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetlhesEventoActivity : AppCompatActivity() {

    private lateinit var imageDetalhe: ImageView
    private lateinit var eventoNome: TextView
    private lateinit var eventoDrescricao: TextView
    private lateinit var eventoData: TextView
    private lateinit var eventoPreco: TextView
    private lateinit var btnChechIn: Button
    private lateinit var btnShare: Button
    private lateinit var progressBar: ProgressBar
    private val viewModel: ViewModelDetailEvento by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detlhes_evento)

        initViews()
        val idEvento = intent.getStringExtra(EVENTO_ID)

        viewModel.eventoData.observe(this, Observer { evento ->

            progressBar.visibility = View.GONE
            eventoNome.text = evento.title
            eventoDrescricao.text = evento.description
            eventoData.text = Converter.getDateTime(evento.date)
            eventoPreco.text = Converter.currencyFormatter(evento.price)
            if(!evento.image.contains("https")){
                val imageFormatada = inserirTexto(evento.image, "s", 4)
                adicionarImageEvento(imageFormatada, imageDetalhe,null)
            }else{
                adicionarImageEvento(evento.image, imageDetalhe,null)
            }


            btnShare.setOnClickListener {
                shareContent(evento.title)
            }

        })
        idEvento?.let { viewModel.getEvent(it) }

    }

    fun initViews(){
        eventoNome = findViewById(R.id.txtDetailEventName)
        imageDetalhe = findViewById(R.id.ivDetailEvent)
        eventoDrescricao = findViewById(R.id.txtDetailEventDescription)
        eventoData = findViewById(R.id.txtDetailEventDate)
        eventoPreco = findViewById(R.id.txtDetailEventPrice)
        btnChechIn = findViewById(R.id.btnCheckIn)
        btnShare = findViewById(R.id.btnShare)
        progressBar = findViewById(R.id.progressBar)

    }
    private fun shareContent(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }
}