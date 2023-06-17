package dev.mashei.cookinghelper.ui.ingredients

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.select.Select
import com.vaadin.flow.component.textfield.TextField
import dev.mashei.cookinghelper.common.MeasureUnit

class IngredientForm : FormLayout() {

    private val name: TextField = TextField("Name")
    private val unit: Select<MeasureUnit> = Select()

    private val saveButton: Button = Button("Save")
    private val closeButton: Button = Button("Close")

    init {
        addClassName("ingredient-form")
        add(
            name, unit, createButtonsLayout()
        )
    }

    private fun createButtonsLayout(): Component {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        saveButton.addClickShortcut(Key.ENTER)

        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY )
        closeButton.addClickShortcut(Key.ESCAPE )
        return HorizontalLayout(saveButton, closeButton)
    }
}