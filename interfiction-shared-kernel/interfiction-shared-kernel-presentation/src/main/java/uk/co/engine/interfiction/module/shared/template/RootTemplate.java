package uk.co.engine.interfiction.module.shared.template;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/components/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport(value = "./styles/components/vaadin-button-styles.css", themeFor = "vaadin-button")
@StyleSheet("https://fonts.googleapis.com/css2?family=Cedarville+Cursive&display=swap")
@Theme(value = Material.class)
public class RootTemplate extends VerticalLayout implements RouterLayout {

    public RootTemplate() {
        setPadding(false);
        setMargin(false);
        setSizeFull();

        add(buildHeaderBar());
    }

    private FlexLayout buildHeaderBar() {
        final FlexLayout headerLayout = new FlexLayout();
        headerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setWidthFull();
        headerLayout.setClassName("header");

        headerLayout.add(buildLogo());
        headerLayout.add(buildMenuItems());

        return headerLayout;
    }

    private UnorderedList buildMenuItems() {
        final UnorderedList menuItems = new UnorderedList();
        menuItems.getStyle().set("list-style", "none");
        menuItems.add(buildMenuItem("Sign In", "signin"));
        menuItems.add(buildMenuItem("Register", "register"));
        return menuItems;
    }

    private ListItem buildMenuItem(final String text, final String href) {
        final Anchor link = styleMenuItem(new Anchor(href, text));
        return new ListItem(link);
    }

    private Anchor styleMenuItem(Anchor anchor) {
        anchor.getStyle().set("display", "inline-block");
        anchor.getStyle().set("text-decoration", "none");
        anchor.getStyle().set("color", "#FFFFFF");
        return anchor;
    }

    private Image buildLogo() {
       return new Image("image/logo.png", "Logo");
    }

}
