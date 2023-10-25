package up.edu.br.cadastroshows.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDAO {

    @Query("select * from eventos")
    fun listar(): Flow<List<Evento>>

    @Insert
    suspend fun inserir(evento: Evento)

    @Update
    suspend fun atualizar(evento: Evento)

    @Delete
    suspend fun excluir(evento: Evento)

    @Query("delete from eventos where id = :id")
    suspend fun excluir(id: Int)

    @Query("delete from eventos")
    suspend fun excluirTodos()
}