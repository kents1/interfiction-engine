package uk.co.engine.interfiction.module.shared.converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StringCapitaliseConverter implements Converter<String, String> {

    @Override
    public Result<String> convertToModel(final String value, ValueContext valueContext) {
        if (value == null) {
            return Result.ok(null);
        } else {
            return Result.ok(value.substring(0, 1).toUpperCase() + value.substring(1));
        }
    }

    @Override
    public String convertToPresentation(String value, ValueContext valueContext) {
        return value == null ? null : value.substring(0, 1).toUpperCase() + value.substring(1);
    }

}
