package dev.mashei.cookinghelper.view

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.RouterLink


@Route("")
@PageTitle("Cooking helper")
class AppLayoutNavbar : AppLayout() {
    init {
        val title = H1("CookingHelper")
        val titleStyle = title.style
        titleStyle
            .set("font-size", "var(--lumo-font-size-l)")
            .set("left", "var(--lumo-space-l)")
            .set("margin", "0")
            .set("position", "absolute");

        addToNavbar(title, getTabs())
    }

    private fun getTabs(): Tabs {
        val tabs = Tabs()
        tabs.style["margin"] = "auto"
        tabs.add(
            createTab("Ingredients"),
        )
        return tabs
    }

    private fun createTab(viewName: String): Tab {
        val link = RouterLink()
        link.add(viewName)
        link.tabIndex = -1
        return Tab(link)
    }
}