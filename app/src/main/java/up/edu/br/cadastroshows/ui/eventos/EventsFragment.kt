package up.edu.br.cadastroshows.ui.eventos

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import up.edu.br.cadastroshows.data.BancoSQLite
import up.edu.br.cadastroshows.databinding.FragmentListaEventosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val banco = BancoSQLite.get(requireContext())
       // val repository = EventoRepository(banco.eventoDAO())
        //val viewModel = EventoViewModel(repository)

        //Injeção automática de dependência
        val viewModel : EventoViewModel by activityViewModels()

        val binding = FragmentListaEventosBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.eventos.collect{ eventos ->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = EventosAdapter(eventos, viewModel)
                }
            }
        }
       return binding.root
    }
}