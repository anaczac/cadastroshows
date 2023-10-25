package up.edu.br.cadastroshows.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nome: String,
    var descricao: String = String(),
    var preco: Double = 0.0,
    var foto: String = "semfoto.jpg"
) {
    constructor() : this(0,"","",0.0,"semfoto.jpg")
}