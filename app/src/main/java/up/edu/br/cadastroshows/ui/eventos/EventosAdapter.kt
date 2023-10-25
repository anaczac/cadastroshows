package up.edu.br.cadastroshows.ui.eventos

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import up.edu.br.cadastroshows.data.Fotos
import up.edu.br.cadastroshows.data.Evento
//import up.edu.br.cadastroshows.ui.evento.databinding.FragmentItemEventoBinding
import up.edu.br.cadastroshows.databinding.FragmentItemEventoBinding

class EventosAdapter(
    private val eventos: List<Evento>,
    val viewModel: EventoViewModel
) :
    RecyclerView.Adapter<EventosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemEventoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemEvento = eventos[position]

        val idFoto = Fotos.get(itemEvento.foto)

        holder.imgFoto.setImageResource(idFoto)
        holder.txtNome.text = itemEvento.nome
        holder.txtPreco.text = itemEvento.preco.toString()

        //clique para editar item da lista
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(itemEvento)
            val action = EventsFragmentDirections.actionNavHomeToEventoFragment()
            view.findNavController().navigate(action)
        }

        //clique para excluir item da lista
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("ATENÇÃO: Tem certeza que deseja excluir?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(itemEvento)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                    //ignorar
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = eventos.size

    inner class ViewHolder(binding: FragmentItemEventoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgFoto: ImageView = binding.imgFoto
        val txtNome: TextView = binding.txtNome
        val txtPreco: TextView = binding.txtPreco
    }

}