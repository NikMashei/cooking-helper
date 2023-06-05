package dev.mashei.cookinghelper.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*


@Embeddable
class MealIngredientId(

    @Column(name = "meal_id")
    var mealId: Int,

    @Column(name = "ingredient_id")
    var ingredientId: Int
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other == null || javaClass != other.javaClass) return false

        val that: MealIngredientId = other as MealIngredientId
        return mealId == that.mealId && ingredientId == that.ingredientId
    }

    override fun hashCode(): Int {
        return Objects.hash(mealId, ingredientId)
    }
}