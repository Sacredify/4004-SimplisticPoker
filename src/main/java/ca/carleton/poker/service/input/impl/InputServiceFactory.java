package ca.carleton.poker.service.input.impl;

import ca.carleton.poker.service.input.InputService;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Factory class for the creation of service inputs.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public final class InputServiceFactory {

    public static InputService getInputService(final Object... serviceParameters) {
        if (isEmpty(serviceParameters)) {
            return new DefaultInputService();
        }
        return null;
    }
}
