package dev.mashei.cookinghelper.model

import jakarta.persistence.*

@Entity
class MealIngredient(

    @EmbeddedId
    var id: MealIngredientId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "mealId")
    var meal: Meal,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "ingredientId")
    var ingredient: Ingredient,

    var amount: Double
)