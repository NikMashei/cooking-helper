package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.model.Ingredient

interface SaveIngredientUseCase {

    fun saveIngredient(command: Command): Ingredient

    class Command(var ingredient: Ingredient)
}