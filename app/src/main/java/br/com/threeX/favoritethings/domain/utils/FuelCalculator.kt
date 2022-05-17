package br.com.threeX.favoritethings.domain.utils

import br.com.threeX.favoritethings.domain.entity.enums.FuelType
import br.com.threeX.favoritethings.domain.entity.holder.BetterFuelHolder

class FuelCalculator {

    fun betterFuel(
        betterFuelHolder: BetterFuelHolder
    ): FuelType {

        val performanceOfMyCar = betterFuelHolder.ethanolAverage / betterFuelHolder.gasAverage

        val priceOfFuelIndice = betterFuelHolder.ethanolPrice / betterFuelHolder.gasPrice

        return if (priceOfFuelIndice <= performanceOfMyCar) {
            FuelType.ETHANOL
        } else {
            FuelType.GASOLINE
        }
    }
}
