package uk.co.interfiction.model;

import static java.util.UUID.randomUUID;

public class Story {

    private String label;

    public Story() {
        this(randomUUID().toString());
    }

    public Story(final String label) {
        this.label = label;
    }

}
