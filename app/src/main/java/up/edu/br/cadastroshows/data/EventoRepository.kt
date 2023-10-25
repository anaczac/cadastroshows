package up.edu.br.cadastroshows.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventoRepository
    @Inject constructor(val eventoDAO: EventoDAO) {

    val eventos: Flow<List<Evento>> get() = eventoDAO.listar()
    suspend fun salvar(evento: Evento) {
        if (evento.id == 0){
            eventoDAO.inserir(evento)
        } else {
            eventoDAO.atualizar(evento)
        }
    }
    suspend fun excluir(produto: Evento){
        eventoDAO.excluir(produto)
    }

    suspend fun excluirTodos(){
        eventoDAO.excluirTodos()
    }

    init {
        CoroutineScope(Job()).launch {

            eventoDAO.excluirTodos()
            delay(15000)
            val eventos = eventos()
            for(p in eventos){
                p.id = 0
                eventoDAO.inserir(p)
            }
        }
    }

    companion object {
        fun eventos(): MutableList<Evento> {
            val lista = mutableListOf(
                Evento(
                    1,
                    "Diogo Portugal",
                    "Não me Cobre Coerência",
                    27.56,
                    "show1.jpg"
                ),
                Evento(
                    2,
                    "Torque",
                    "Espetaculo Magnifico de Natal para toda a Familia.",
                    52.49,
                    "show2.jpg"
                ),
                Evento(
                    3,
                    "Chegadas e Partidas",
                    "Projeto de Teatro da Caixa de Assistência dos Advogados do Paranáe.",
                    35.1,
                    "show3.jpg"
                ),
                Evento(
                    4,
                    "Opa! Bão?",
                    "Stand Up com Santo Fole.",
                    32.34,
                    "show4.jpg"
                ),
                Evento(
                    5,
                    "Tour Morada 2023",
                    "Em seu décimo show, a Banda Morada se apresenta em Jundiaí.",
                    22.34,
                    "show5.jpg"
                )
            )
            return lista
        }
    }
}