package uk.co.engine.interfiction.module.web.presentation.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import uk.co.engine.interfiction.module.shared.template.RootTemplate;

@Route(value = "", layout = RootTemplate.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        add(new H1("Test"));
    }

}
