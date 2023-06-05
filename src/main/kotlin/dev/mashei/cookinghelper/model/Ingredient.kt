package dev.mashei.cookinghelper.model

import dev.mashei.cookinghelper.common.MeasureUnit
import jakarta.persistence.*

@Entity
class Ingredient(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    var unit: MeasureUnit
)