// supplements.kt
package com.tfg.gymapp.presentation.data

import com.tfg.gymapp.R

data class Supplement(
    val name: String,
    val imageRes: Int,
    val route: String? = null,
    val description: String? = null,
    val hasSubList: Boolean = false
)

val supplementList = listOf(
    Supplement("Proteína", R.drawable.proteina, "sublist/proteina", hasSubList = true),
    Supplement("Creatina", R.drawable.creatina, "sublist/creatina", hasSubList = true),
    Supplement("Aminoácidos", R.drawable.aminoacidos, "sublist/aminoacidos", hasSubList = true),
    Supplement("Vitaminas y minerales", R.drawable.vitaminas, "sublist/vitaminas", hasSubList = true),
    Supplement("Productos especiales", R.drawable.especiales, "sublist/especiales", hasSubList = true),
    Supplement("L-carnitina", R.drawable.lcarnitina, "detail/L-carnitina", "Ayuda en la producción de energía transportando ácidos grasos a las mitocondrias."),
    Supplement("Gainer", R.drawable.gainer, "detail/Gainer", "Los Gainers son suplementos hipercalóricos para ganar masa muscular."),
    Supplement("Huevo", R.drawable.huevo, "detail/Huevo", "Proteína derivada del huevo, excelente para personas con intolerancia a la lactosa."),
    Supplement("Multivitamínico", R.drawable.multivitaminico, "detail/Multivitamínico", "Proporciona vitaminas y minerales esenciales para la salud general."),
    Supplement("BCAA", R.drawable.bcaa, "detail/BCAA", "Aminoácidos de cadena ramificada que ayudan en la recuperación muscular.")
)

fun getSupplementDetail(name: String): Supplement {
    return supplementList.find { it.name == name } ?: Supplement(
        name = "Desconocido",
        imageRes = R.drawable.ic_default_frasco,
        description = "No se encontró información sobre este suplemento."
    )
}
