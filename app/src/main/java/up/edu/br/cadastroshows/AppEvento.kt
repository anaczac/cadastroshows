package up.edu.br.cadastroshows
import android.app.Application
import android.content.Context
import up.edu.br.cadastroshows.data.BancoSQLite
import up.edu.br.cadastroshows.data.EventoDAO
import up.edu.br.cadastroshows.data.EventoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppEvento : Application() {

    @Provides
    fun provideEventoRepository(eventoDAO: EventoDAO) : EventoRepository {
        return EventoRepository(eventoDAO)
    }

    @Provides
    fun provideEventoDAO(bancoSQLite: BancoSQLite): EventoDAO {
        return bancoSQLite.eventoDAO()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}