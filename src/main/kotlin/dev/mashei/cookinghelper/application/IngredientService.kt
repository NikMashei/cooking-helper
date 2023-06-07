package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.ApiError
import dev.mashei.cookinghelper.application.EditIngredientUseCase.EditIngredient
import dev.mashei.cookinghelper.model.Ingredient
import dev.mashei.cookinghelper.persistence.IngredientRepository
import org.springframework.stereotype.Component

@Component
class IngredientService(var repository: IngredientRepository) :
    SaveIngredientUseCase, EditIngredientUseCase, DeleteIngredientUseCase, IngredientsQuery {

    override fun saveIngredient(command: SaveIngredientUseCase.Command): Ingredient {
        val ingredient = command.ingredient
        checkIngredientUnique(ingredient)
        return repository.save(ingredient)
    }

    override fun editIngredient(command: EditIngredient): Ingredient {
        val ingredient = command.ingredient
        val ingredientById = checkIngredientExists(ingredient.id)

        checkIngredientUnique(ingredient)
        ingredientById.name = ingredient.name
        ingredientById.unit = ingredient.unit
        return repository.save(ingredientById)
    }

    override fun deleteIngredient(id: Int) {
        checkIngredientExists(id)
        repository.deleteById(id)
    }

    override fun queryIngredients(): Iterable<Ingredient> {
        return repository.findAll()
    }

    private fun checkIngredientUnique(ingredient: Ingredient) {
        repository.findByNameEquals(ingredient.name).ifPresent { ing ->
            throw ApiError("not unique ingredient - ${ing.name}")
        }
    }

    private fun checkIngredientExists(id: Int?): Ingredient {
        return repository.findById(id!!).orElseThrow { ApiError("ingredient not found") }
    }
}