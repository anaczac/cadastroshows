package up.edu.br.cadastroshows.ui.eventos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import up.edu.br.cadastroshows.R
import up.edu.br.cadastroshows.data.Evento
import up.edu.br.cadastroshows.databinding.FragmentEventoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : EventoViewModel by this.activityViewModels()
        val binding = FragmentEventoBinding.inflate(layoutInflater)

        val evento = viewModel.evento
        binding.inputNome.setText(evento.nome)
        binding.inputDescricao.setText(evento.descricao)
        binding.inputPreco.setText(evento.preco.toString())
        binding.inputFoto.setText(evento.foto)

        binding.btnSalvar.setOnClickListener {
            val eventoSalvar = Evento(
                evento.id,
                binding.inputNome.text.toString(),
                binding.inputDescricao.text.toString(),
                binding.inputPreco.text.toString().toDouble(),
                binding.inputFoto.text.toString()
            )
            viewModel.evento = eventoSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return binding.root
    }
}