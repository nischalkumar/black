package org.wizindia.black.common.Enums;

/**
 * Created by hari_om on 6/28/15.
 */
public enum Priority {
    HIGH(0),
    LOW(10),
    MEDIUM(5);

    private final int value;

    private  Priority(final int value) {
        this.value = value;
    }

    private int getValue() {
        return this.value;
    }
}
