package dev.mashei.cookinghelper.model

import dev.mashei.cookinghelper.common.MeasureUnit
import jakarta.persistence.*
import java.util.Objects

@Entity
class Ingredient(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false, unique = true)
    var name: String,

    var unit: MeasureUnit,

    @OneToMany(
        mappedBy = "ingredient",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var meals: List<MealIngredient> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other == null || javaClass != other.javaClass) return false

        val that = other as Ingredient

        return name == that.name && unit == that.unit
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, unit)
    }
}