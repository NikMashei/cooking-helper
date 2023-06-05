package dev.mashei.cookinghelper.persistence

import dev.mashei.cookinghelper.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository

interface IngredientRepository : JpaRepository<Ingredient, Int>