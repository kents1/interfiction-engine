package uk.co.interfiction.io;

import uk.co.interfiction.model.Story;

import java.io.InputStream;

public class IfxReader {

    public static Story fromResource(final String resourceName) {
        InputStream resourceAsStream = IfxReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (resourceAsStream == null) {
            throw new RuntimeException();
        }
        return new Story();
    }

}
