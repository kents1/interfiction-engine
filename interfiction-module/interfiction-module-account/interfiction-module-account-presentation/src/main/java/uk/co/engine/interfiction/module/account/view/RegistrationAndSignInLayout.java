package uk.co.engine.interfiction.module.account.view;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import uk.co.engine.interfiction.module.shared.template.RootTemplate;

@ParentLayout(RootTemplate.class)
@StyleSheet("https://fonts.googleapis.com/css2?family=Varta&display=swap")
public class RegistrationAndSignInLayout extends Div implements RouterLayout {

    public RegistrationAndSignInLayout() {
        setSizeFull();
        setId("scripts");

        String paragraph1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Augue lacus viverra vitae congue eu consequat. Libero nunc consequat interdum varius sit amet mattis vulputate. Arcu odio ut sem nulla pharetra diam. Vitae purus faucibus ornare suspendisse sed nisi lacus sed.";
        String paragraph2 = "Donec et odio pellentesque diam volutpat commodo sed. Id venenatis a condimentum vitae sapien pellentesque habitant. Placerat orci nulla pellentesque dignissim enim sit. Odio euismod lacinia at quis. Diam maecenas ultricies mi eget mauris pharetra. Amet cursus sit amet dictum sit. Eu volutpat odio facilisis mauris sit.";
        String paragraph3 = "Morbi tristique senectus et netus et malesuada fames ac. Morbi tincidunt augue interdum velit euismod in pellentesque massa placerat. Porttitor leo a diam sollicitudin. Arcu odio ut sem nulla pharetra diam sit amet. At consectetur lorem donec massa sapien faucibus et. Eleifend mi in nulla posuere sollicitudin. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare. Lorem ipsum dolor sit amet consectetur adipiscing elit duis. Massa sed elementum tempus egestas sed sed risus pretium. Amet est placerat in egestas erat imperdiet. Elit pellentesque habitant morbi tristique senectus et netus.";
        String paragraph4 = "Amet dictum sit amet justo donec enim diam vulputate ut. Quis varius quam quisque id diam vel quam elementum. Egestas purus viverra accumsan in nisl nisi scelerisque eu ultrices. Nulla aliquet porttitor lacus luctus accumsan tortor posuere ac. Euismod in pellentesque massa placerat. Ac auctor augue mauris augue neque gravida in fermentum. Laoreet suspendisse interdum consectetur libero id faucibus nisl tincidunt. Massa placerat duis ultricies lacus sed. Nibh tellus molestie nunc non. Massa placerat duis ultricies lacus sed turpis tincidunt id aliquet. Sapien eget mi proin sed. Amet justo donec enim diam vulputate ut pharetra.";
        String paragraph5 = "Odio aenean sed adipiscing diam donec adipiscing tristique risus. Pellentesque habitant morbi tristique senectus et netus et malesuada. Suspendisse faucibus interdum posuere lorem. Lectus proin nibh nisl condimentum id venenatis. Nam libero justo laoreet sit amet. Nec nam aliquam sem et tortor consequat. Donec massa sapien faucibus et. Euismod quis viverra nibh cras pulvinar mattis. Eget nunc scelerisque viverra mauris. Odio ut sem nulla pharetra diam sit. In fermentum et sollicitudin ac. Accumsan in nisl nisi scelerisque eu ultrices vitae auctor. Eget egestas purus viverra accumsan in nisl nisi. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt tortor. Facilisis gravida neque convallis a cras semper auctor.";

        add(
                addScript("script-one", paragraph1),
                addScript("script-two", paragraph5),
                addScript("script-three", paragraph2),
                addScript("script-four", paragraph4),
                addScript("script-five", paragraph1),
                addScript("script-six", paragraph3),
                addScript("script-seven", paragraph2),
                addScript("script-eight", paragraph4),
                addScript("script-nine", paragraph5));
    }

    private Div addScript(final String id, final String content) {
        final Div scriptDiv = new Div();
        scriptDiv.setId(id);
        scriptDiv.setClassName("script");
        scriptDiv.add(new Paragraph(content));
        return scriptDiv;
    }

}
