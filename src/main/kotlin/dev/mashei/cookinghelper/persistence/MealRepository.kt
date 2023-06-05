package dev.mashei.cookinghelper.persistence

import dev.mashei.cookinghelper.model.Meal
import org.springframework.data.jpa.repository.JpaRepository

interface MealRepository : JpaRepository<Meal, Int>