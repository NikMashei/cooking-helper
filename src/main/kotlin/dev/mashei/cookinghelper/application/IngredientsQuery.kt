package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.model.Ingredient

interface IngredientsQuery {

    fun queryIngredients(query: Query): Iterable<Ingredient>

    class Query(val searchFilter: String? = null)
}