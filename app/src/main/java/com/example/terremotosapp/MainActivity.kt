package com.example.terremotosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terremotosapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerTerremoto.layoutManager = LinearLayoutManager(this)

        val misTerremotos = DatosTerremoto().cargarTerremotos()

        val adapter = TerremotoAdapter(this, misTerremotos)

        if (misTerremotos.size > 0 && misTerremotos.isNotEmpty()) {
            binding.recyclerTerremoto.adapter = adapter
            binding.recyclerTerremoto.setHasFixedSize(true)
            adapter.onItemClick = { item ->
                Toast.makeText(this, item.lugar, Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.emptyView.visibility = View.VISIBLE
        }


    }
}