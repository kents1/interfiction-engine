package uk.co.engine.interfiction.module.account.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import uk.co.engine.interfiction.module.shared.template.RootTemplate;

@Route(value = "signin", layout = RegistrationAndSignInLayout.class)
@CssImport("./styles/account-styles.css")
@PageTitle("Sign In")
public class SignInView extends Div {

    public SignInView() {
    }

}
