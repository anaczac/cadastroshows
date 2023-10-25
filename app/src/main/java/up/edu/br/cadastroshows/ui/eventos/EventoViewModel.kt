package up.edu.br.cadastroshows.ui.eventos
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import up.edu.br.cadastroshows.data.Evento
import up.edu.br.cadastroshows.data.EventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventoViewModel
@Inject constructor(val repository: EventoRepository) : ViewModel() {

    var evento: Evento = Evento()

    private var _eventos = MutableStateFlow(listOf<Evento>())
    val eventos: Flow<List<Evento>> = _eventos

    init {
        viewModelScope.launch {
            repository.eventos.collect{eventos ->
                _eventos.value = eventos
            }
        }
    }

    fun novo(){
        this.evento = Evento()
    }

    fun editar(evento: Evento){
        this.evento = evento
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(evento)
    }

    fun excluir(evento: Evento) = viewModelScope.launch {
        repository.excluir(evento)
    }
}