package com.example.terremotosapp


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.terremotosapp.databinding.TerremotoListItemBinding

private val TAG = TerremotoAdapter::class.java.simpleName

class TerremotoAdapter(
    private val context: Context,
    private val terremotoSet: MutableList<Terremoto>
) : RecyclerView.Adapter<TerremotoAdapter.ViewHolder>() {

    lateinit var onItemClick: (Terremoto) -> Unit

    //inner para acceder a los miembros externos de su clase
    inner class ViewHolder(private val binding: TerremotoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(terremoto: Terremoto) {
            binding.magnitud.text = terremoto.magnitud.toString()
            binding.lugar.text = terremoto.lugar
            binding.root.setOnClickListener {
                if (::onItemClick.isInitialized)  onItemClick(terremoto)
                else{
                    Log.e(TAG, "onItemClick no está inicializado")
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val terremotoListItem =
            TerremotoListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(terremotoListItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val terremoto = terremotoSet[position]
        holder.bind(terremoto)
    }

    override fun getItemCount() = terremotoSet.size
}