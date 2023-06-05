package dev.mashei.cookinghelper.model

import dev.mashei.cookinghelper.common.MealType
import jakarta.persistence.*

@Entity
class Meal(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var name: String,

    var type: MealType,

    @OneToMany(
        mappedBy = "meal",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var ingredients: List<MealIngredient> = mutableListOf()
)