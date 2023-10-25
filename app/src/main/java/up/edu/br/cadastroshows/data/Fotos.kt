package up.edu.br.cadastroshows.data

import up.edu.br.cadastroshows.R

class Fotos {
    companion object {
        fun get(key: String): Int {
            val mapOfFotos = mapOf(
                "show1.jpg" to R.drawable.show1,
                "show2.jpg" to R.drawable.show2,
                "show3.jpg" to R.drawable.show3,
                "show4.jpg" to R.drawable.show4,
                "show5.jpg" to R.drawable.show5
            )
            return mapOfFotos[key] ?: R.drawable.semfoto
        }
    }
}