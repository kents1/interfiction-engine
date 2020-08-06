package uk.co.engine.interfiction.module.account.view;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.function.ValueProvider;

public interface HasModel<SOURCE, TARGET> {

    default void bind(HasValue<?, TARGET> field, ValueProvider<SOURCE, TARGET> getter, Setter<SOURCE, TARGET> setter) {
        getBinder().forField(field).bind(getter, setter);
    }

    default void update(SOURCE model) throws ValidationException {
        getBinder().writeBean(model);
    }

    Binder<SOURCE> getBinder();

}
