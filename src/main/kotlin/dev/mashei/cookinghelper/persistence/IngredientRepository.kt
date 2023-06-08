package dev.mashei.cookinghelper.persistence

import dev.mashei.cookinghelper.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Int> {

    fun findByNameEquals(string: String): Optional<Ingredient>

    @Query("SELECT i FROM Ingredient i WHERE lower(i.name) like lower(concat('%', :searchFilter, '%'))")
    fun search(searchFilter: String): List<Ingredient>
}