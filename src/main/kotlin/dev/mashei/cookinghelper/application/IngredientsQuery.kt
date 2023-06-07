package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.model.Ingredient

interface IngredientsQuery {

    fun queryIngredients(): Iterable<Ingredient>
}