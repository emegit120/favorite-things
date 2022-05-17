package br.com.threeX.favoritethings.extensions

fun Double.format(digits: Int) = String.format("%.${digits}f", this)