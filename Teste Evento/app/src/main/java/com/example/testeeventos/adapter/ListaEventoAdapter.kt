package com.example.testeeventos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeeventos.R
import com.example.testeeventos.model.Eventos
import com.example.testeeventos.util.ImageCallback
import com.squareup.picasso.Picasso

class ListaEventoAdapter( var eventos: List<Eventos>) : RecyclerView.Adapter<ListaEventoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_lista_eventos, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val events : Eventos = eventos[position]
        holder.bind(events)
//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, EventItemActivity::class.java)
//
//            intent.putExtra(EVENT_ID, events.id)
//            it.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int = eventos.size

    fun updateList(newList: List<Eventos>) {
        this.eventos = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.tv_title)
        val description = view.findViewById<TextView>(R.id.tv_description)
        val image = view.findViewById<ImageView>(R.id.iv_post_image)
        val data = view.findViewById<TextView>(R.id.tv_date)
        var pbItemEvent = view.findViewById<ProgressBar>(R.id.pb_item)

        fun bind(event: Eventos) {
            title.text = event.title
            description.text = event.description
            data.text = event.date
            if(!event.image.contains("https")){
               val imageFormatada =  inserirTexto(event.image,"s",4)
                imageFormatada.let {
                    Picasso.get()
                        .load(imageFormatada)
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(image, object : ImageCallback() {
                            override fun onAfterLoad() {
                                pbItemEvent?.visibility = View.GONE
                            }
                        })
                }
            }else{
                event.image.let {
                    Picasso.get()
                        .load(event.image)
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(image, object : ImageCallback() {
                            override fun onAfterLoad() {
                                pbItemEvent?.visibility = View.GONE
                            }
                        })
                }
            }

        }

    }
    fun inserirTexto (stringOriginal: String, addString : String, indices: Int): String {
        return(stringOriginal.substring(0, indices)
                + addString
                + stringOriginal.substring(indices)
                );
    }
}