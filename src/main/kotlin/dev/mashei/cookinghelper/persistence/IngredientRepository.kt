package dev.mashei.cookinghelper.persistence

import dev.mashei.cookinghelper.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Int> {

    fun findByNameEquals(string: String): Optional<Ingredient>
}