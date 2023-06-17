package dev.mashei.cookinghelper.ui.ingredients

import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import dev.mashei.cookinghelper.application.IngredientsQuery
import dev.mashei.cookinghelper.application.IngredientsQuery.Query
import dev.mashei.cookinghelper.model.Ingredient

@Route("")
@PageTitle("Ingredients | CookingHelper")
@CssImport("./styles/shared-styles.css")
class IngredientsList(private var ingredientsListQuery: IngredientsQuery) : VerticalLayout() {

    private val grid: Grid<Ingredient> = Grid(Ingredient::class.java)
    private val filterText: TextField = TextField()
    private val form: IngredientForm = IngredientForm()

    init {
        addClassName("ingredients-list-view")
        setSizeFull()
        configureFilter()
        configureGrid()

        val content = Div(grid, form)
        content.addClassName("content")
        content.setSizeFull()

        add(filterText, content)
        updateList()
    }

    private fun updateList() {
        grid.setItems(ingredientsListQuery.queryIngredients(Query(filterText.value)))
    }

    private fun configureFilter() {
        filterText.placeholder = "Filter by name"
        filterText.isClearButtonVisible = true
        filterText.valueChangeMode = ValueChangeMode.EAGER
        filterText.addValueChangeListener { updateList() }
    }

    private fun configureGrid() {
        grid.addClassName("ingredient-grid")
        grid.setSizeFull()
        grid.setColumns("name", "unit")
    }
}
