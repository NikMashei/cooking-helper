package dev.mashei;

import dev.mashei.cookinghelper.common.MeasureUnit;
import dev.mashei.cookinghelper.model.Ingredient;
import dev.mashei.cookinghelper.persistence.IngredientRepository;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(IngredientRepository ingredientRepository) {
        return args -> {
            List<Ingredient> ingredients = List.of(
                    new Ingredient(null, "milk", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "chocolate", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "tomato", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "pepper", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "salt", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "apple", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "sugar", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "powder", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "water", MeasureUnit.MILLILITER, List.of()),
                    new Ingredient(null, "onion", MeasureUnit.MILLILITER, List.of()));
            ingredientRepository.deleteAll();
            ingredientRepository.saveAll(ingredients);
        };
    }
}
