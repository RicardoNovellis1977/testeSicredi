package com.example.testeeventos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeeventos.R
import com.example.testeeventos.adapter.ListaEventoAdapter
import com.example.testeeventos.model.Eventos
import com.example.testeeventos.viewmodel.ViewModelEventos
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private val viewModel: ViewModelEventos by viewModel()
    var eventoLista = listOf<Eventos>()
    val mAdapter by lazy { ListaEventoAdapter(eventoLista) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvListaEventos)
        setupRecycler()
        setUpEventList()
    }

    private fun setupRecycler() {
        recyclerView?.layoutManager = LinearLayoutManager(application)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                recyclerView?.context,
                (recyclerView?.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView?.adapter = mAdapter
    }
    private fun setUpEventList() {
        viewModel.events.observe(this, Observer {
            it?.let {
                with(recyclerView) {
                    this?.layoutManager =
                        LinearLayoutManager(application, RecyclerView.VERTICAL, false)
                    this?.setHasFixedSize(true)
                    setEvent(it)
                }
            }
        })
        viewModel.getEvents()
    }

    private fun setEvent(event: List<Eventos>) {
        mAdapter.updateList(event)
    }
}