package ca.carleton.poker.service.input;

/**
 * Service contract for gathering input from the user.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public interface InputService {

    /**
     * Gather user input from the service.
     *
     * @return the input as a string.
     */
    String getInput();
}
