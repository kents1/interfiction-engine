package uk.co.engine.interfiction.module.account.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.engine.interfiction.module.account.exception.UserAccountCreationException;
import uk.co.engine.interfiction.module.account.model.RegistrationModel;
import uk.co.engine.interfiction.module.account.service.UserService;
import uk.co.engine.interfiction.module.shared.converter.StringCapitaliseConverter;
import uk.co.engine.interfiction.module.shared.converter.StringLowercaseConverter;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;

import javax.annotation.PostConstruct;

@Route(value = "register", layout = RegistrationAndSignInLayout.class)
@CssImport("./styles/account-styles.css")
@PageTitle("Register")
public class RegisterView extends Div {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterView.class);

    private final UserService userService;

    private final Binder<RegistrationModel> registrationFormBinder = new Binder<>(RegistrationModel.class);
    private final RegistrationModel model = new RegistrationModel();
    private final PasswordField passwordField;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    public RegisterView(final UserService userService) {
        this.userService = userService;
        this.passwordField = createPasswordField("Password");
    }

    @PostConstruct
    void initUI() {
        LOGGER.debug("Building UI");
        add(createOverlayDiv());
        registrationFormBinder.readBean(model);
    }

    private Div createOverlayDiv() {
        final Div div = new Div(createRegistrationForm());
        div.setId("overlay");
        return div;
    }

    private Div createRegistrationForm() {
        final Div div = new Div();
        div.setId("register-form");
        div.add(createDisplayPanel(), createFormContent());
        return div;
    }

    private Div createDisplayPanel() {
        final Div div = new Div();
        div.setId("display-panel");
        div.add(new Image("image/logo_with_text.png", "InterFiction"));
        div.add(new H1("Registration"));
        div.add(new Span("Sign up to create your own interactive story"));
        return div;
    }

    private Div createFormContent() {
        final Div div = new Div(createForm());
        div.setId("form-content");
        return div;
    }

    private Div createForm() {
        final Div div = new Div();
        div.setSizeFull();
        div.setId("form");
        div.add(
                createEmailField(),
                createFirstNameField(),
                createLastNameField(),
                createPasswordField(),
                createConfirmPasswordField(),
                createLanguageOptions(),
                createRegisterButton(),
                createSignInButton());
        return div;
    }

    private Button createRegisterButton() {
        final Button button = new Button("Register");
        button.setId("register-btn");
        button.setThemeName("contained");
        button.addClickListener(e -> onCreateAccount());
        return button;
    }

    private void onCreateAccount() {
        try {
            if (registrationFormBinder.writeBeanIfValid(model)) {
                LOGGER.debug("Creating account for {}", model.getEmailAddress());
                final EmailAddressVO emailAddress = new EmailAddressVO(model.getEmailAddress());
                userService.createAccount(emailAddress, model.getPassword(), model.getFirstName(), "country");
                userService.authenticate(emailAddress, model.getPassword());
            }
        }
        catch (final UserAccountCreationException e) {
            LOGGER.error("Unable to create user account.", e);
        }
    }

    private Button createSignInButton() {
        final Button button = new Button("I'm already registered");
        button.setId("sign-in-btn");
        button.setThemeName("link");
        button.addClickListener(e -> button.getUI().ifPresent(ui -> ui.navigate(SignInView.class)));
        return button;
    }

    private TextField createTextField(final String label) {
        final TextField textField = new TextField();
        textField.setLabel(label);
        textField.setClassName("form-field");
        textField.setThemeName("always-float-label");
        return textField;
    }

    private PasswordField createPasswordField(final String label) {
        final PasswordField passwordField = new PasswordField();
        passwordField.setLabel(label);
        passwordField.setClassName("form-field");
        passwordField.setThemeName("always-float-label");
        return passwordField;
    }

    private ComboBox<String> createLanguageOptions() {
        final ComboBox<String> comboBox = new ComboBox<>("LANGUAGE");
        comboBox.setClassName("form-field");
        comboBox.getElement().setAttribute("theme", "always-float-label");
        comboBox.setItems("English");
        comboBox.setAllowCustomValue(false);
        comboBox.setValue("English");
        return comboBox;
    }

    private TextField createEmailField() {
        final TextField field = createTextField("Email address");
        field.setId("email-address");
        registrationFormBinder.forField(field)
                .asRequired()
                .withNullRepresentation("")
                .withValidator(new EmailValidator("Please provide a valid email address format"))
                .withConverter(new StringLowercaseConverter())
                .bind(RegistrationModel::getEmailAddress, RegistrationModel::setEmailAddress);
        return field;
    }

    private TextField createFirstNameField() {
        final TextField field = createTextField("First name");
        field.setId("first-name");
        field.addClassName("inline");
        registrationFormBinder.forField(field)
                .asRequired()
                .withNullRepresentation("")
                .withConverter(new StringCapitaliseConverter())
                .bind(RegistrationModel::getFirstName, RegistrationModel::setFirstName);
        return field;
    }

    private TextField createLastNameField() {
        final TextField field = createTextField("Last name");
        field.setId("last-name");
        field.addClassName("inline");
        registrationFormBinder.forField(field)
                .asRequired()
                .withNullRepresentation("")
                .withConverter(new StringCapitaliseConverter())
                .bind(RegistrationModel::getLastName, RegistrationModel::setLastName);
        return field;
    }

    private PasswordField createPasswordField() {
        this.passwordField.setId("frm-password");
        registrationFormBinder.forField(this.passwordField)
                .asRequired()
                .withNullRepresentation("")
                .bind(RegistrationModel::getPassword, RegistrationModel::setPassword);
        return this.passwordField;
    }

    private PasswordField createConfirmPasswordField() {
        final PasswordField field = createPasswordField("Confirm password");
        field.setId("frm-confirm-password");
        field.setErrorMessage("Password does not match.");
        field.setInvalid(false);
        field.addKeyPressListener(listener ->
                field.setInvalid(!this.passwordField.getValue().equals(field.getValue())));
        field.setRequired(true);
        field.setRequiredIndicatorVisible(true);
        return field;
    }

}
