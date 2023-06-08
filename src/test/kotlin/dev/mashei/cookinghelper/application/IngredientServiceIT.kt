package dev.mashei.cookinghelper.application

import dev.mashei.cookinghelper.ApiError
import dev.mashei.cookinghelper.application.EditIngredientUseCase.EditIngredient
import dev.mashei.cookinghelper.application.IngredientsQuery.Query
import dev.mashei.cookinghelper.application.SaveIngredientUseCase.Command
import dev.mashei.cookinghelper.common.MeasureUnit
import dev.mashei.cookinghelper.model.Ingredient
import dev.mashei.cookinghelper.persistence.IngredientRepository
import dev.mashei.cookinghelper.testcontainer.AbstractPostgresContainer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class IngredientServiceIT : AbstractPostgresContainer() {

    @Autowired
    lateinit var repository: IngredientRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

    lateinit var service: IngredientService

    @BeforeEach
    fun initEach() {
        entityManager.entityManager.createQuery("DELETE FROM Ingredient").executeUpdate()
        service = IngredientService(repository)
    }

    @Test
    fun `service should save an ingredient`() {
        // given
        val ingredientToSave = Ingredient(name = "milk", unit = MeasureUnit.MILLILITER)

        // when
        val savedIngredient = service.saveIngredient(Command(ingredientToSave))

        // then
        assert(savedIngredient.id != null)
    }

    @Test
    fun `service should throw error if ingredient is not unique`() {
        // given
        val ingredient = Ingredient(name = "eggs", unit = MeasureUnit.PC)
        entityManager.persist(ingredient)

        // when
        val exception = assertThrows<ApiError> {
            service.saveIngredient(Command(ingredient))
        }

        // then
        assert(exception.message == "not unique ingredient - eggs")
    }

    @Test
    fun `service should edit a persisted ingredient`() {
        // given
        val id = entityManager.persistAndGetId(
            Ingredient(name = "pasta", unit = MeasureUnit.GRAM))

        // when
        service.editIngredient(
            EditIngredient(
                Ingredient(id = id as Int, name = "ravioli", unit = MeasureUnit.PC)
            )
        )

        // then
        val ingredientById = entityManager.find(Ingredient::class.java, id)
        assert(ingredientById.name == "ravioli")
        assert(ingredientById.unit == MeasureUnit.PC)
    }

    @Test
    fun `service should throw error if ingredient is missing`() {
        // given
        val ingredient = Ingredient(id = 100, name = "", unit = MeasureUnit.GRAM)

        // when
        val exception = assertThrows<ApiError> {
            service.editIngredient(EditIngredient(ingredient))
        }

        // then
        assert(exception.message == "ingredient not found")
    }

    @Test
    fun `service should throw error if new name of ingredient is not unique`() {
        // given
        entityManager.persistAndFlush(Ingredient(name = "tomato", unit = MeasureUnit.PC))
        val id = entityManager.persistAndGetId(Ingredient(name = "cherry", unit = MeasureUnit.PC))

        // when
        val exception = assertThrows<ApiError> {
            service.editIngredient(
                EditIngredient(Ingredient(id = id as Int, name = "tomato", unit = MeasureUnit.PC)))
        }

        // then
        assert(exception.message == "not unique ingredient - tomato")
    }

    @Test
    fun `service should delete persisted ingredient`() {
        // given
        val persistedIngredient =
            entityManager.persistAndFlush(Ingredient(name = "chocolate", unit = MeasureUnit.GRAM))

        // when
        service.deleteIngredient(persistedIngredient.id!!)

        // then
        val ingredientById = entityManager.find(Ingredient::class.java, persistedIngredient.id)
        assert(ingredientById == null)
    }

    @Test
    fun `service should throw error if ingredient to delete is missing`() {
        // when
        val exception = assertThrows<ApiError> {
            service.deleteIngredient(200)
        }

        // then
        assert(exception.message == "ingredient not found")
    }

    @Test
    fun `service should return ingredients matching a search criteria`() {
        // given
        val ingredients = listOf(
            Ingredient(name = "chicken", unit = MeasureUnit.GRAM),
            Ingredient(name = "chia", unit = MeasureUnit.GRAM),
            Ingredient(name = "turkey", unit = MeasureUnit.GRAM)
        )

        ingredients.forEach { ingredient ->
            entityManager.persistAndFlush(ingredient)
        }

        // when
        val ingredientsByQuery = service.queryIngredients(Query("chi"))

        // then
        assert(ingredientsByQuery.size == 2)
    }

    @Test
    fun `service should returns all ingredients if a search criteria is not specified`() {
        // given
        val ingredients = listOf(
            Ingredient(name = "chicken", unit = MeasureUnit.GRAM),
            Ingredient(name = "chia", unit = MeasureUnit.GRAM),
            Ingredient(name = "turkey", unit = MeasureUnit.GRAM)
        )

        ingredients.forEach { ingredient ->
            entityManager.persistAndFlush(ingredient)
        }

        // when
        val ingredientsByQuery = service.queryIngredients(Query())

        // then
        assert(ingredientsByQuery.size == 3)
    }
}