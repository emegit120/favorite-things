package br.com.threeX.favoritethings.domain.entity

data class Thing(
    val name: String = "",
    val temporada: Double = 0.0,
    val capitulo: Double = 0.0,
    val motivo: Double = 0.0,
    var userId: String = ""
)
