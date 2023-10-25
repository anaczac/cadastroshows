package up.edu.br.cadastroshows.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Evento::class], version = 1, exportSchema = false)
abstract class BancoSQLite : RoomDatabase() {

    abstract fun eventoDAO(): EventoDAO

    companion object{

        @Volatile
        private var INSTANCIA: BancoSQLite? = null

        fun get(context: Context) : BancoSQLite{
            if(INSTANCIA == null){
                INSTANCIA = Room.databaseBuilder(
                    context.applicationContext,
                    BancoSQLite::class.java,
                    "meu_banco.db"
                ).build()
            }
            return INSTANCIA as BancoSQLite
        }
    }

}