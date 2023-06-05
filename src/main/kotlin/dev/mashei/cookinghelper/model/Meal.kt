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

    @ManyToMany
    @JoinTable(
        name="meal_ingredient",
        joinColumns = [JoinColumn(name="fk_meal")],
        inverseJoinColumns = [JoinColumn(name="fk_ingredient")])
    var indredients: List<Ingredient> = mutableListOf()
)