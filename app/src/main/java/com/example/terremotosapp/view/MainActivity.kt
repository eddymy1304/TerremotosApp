package com.example.terremotosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terremotosapp.databinding.ActivityMainBinding
import com.example.terremotosapp.model.Terremoto
import com.example.terremotosapp.view.adapter.TerremotoAdapter
import com.example.terremotosapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerTerremoto.layoutManager = LinearLayoutManager(this)
        viewModel.terremotoList.observe(this) { terremoto ->
            val adapter = TerremotoAdapter(this, viewModel.terremotoList.value!!)
            binding.recyclerTerremoto.adapter = adapter
            //binding.recyclerTerremoto.setHasFixedSize(true)
            comprobarEmptyView(terremoto, adapter)
        }

    }

    private fun comprobarEmptyView(terremoto: MutableList<Terremoto>?, adapter: TerremotoAdapter) {
        if (terremoto!!.size > 0 && terremoto!!.isNotEmpty()) {
            adapter.onItemClick = { item ->
                Toast.makeText(this, item.lugar, Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.emptyView.visibility = View.VISIBLE
        }
    }
}