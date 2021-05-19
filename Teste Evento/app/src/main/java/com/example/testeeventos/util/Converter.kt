package com.example.testeeventos.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.testeeventos.R
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Converter {
    companion object {
        fun getDateTime(s: String): String? {
            try {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val netDate = Date(s.toLong() * 1000)
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }

        fun currencyFormatter(value: Double): String? {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }

        fun inserirTexto (stringOriginal: String, addString : String, indices: Int): String {
            return(stringOriginal.substring(0, indices)
                    + addString
                    + stringOriginal.substring(indices)
                    );
        }
        fun adicionarImageEvento(imageEvento: String, view: ImageView, load : ProgressBar?){
            imageEvento.let {
                Picasso.get()
                    .load(imageEvento)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(view, object : ImageCallback() {
                        override fun onAfterLoad() {
                            load?.visibility = View.GONE
                        }
                    })
            }
        }

    }
}