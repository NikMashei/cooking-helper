package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.model.Ingredient

interface EditIngredientUseCase {

    fun editIngredient(command: EditIngredient): Ingredient

    class EditIngredient(val ingredient: Ingredient)
}