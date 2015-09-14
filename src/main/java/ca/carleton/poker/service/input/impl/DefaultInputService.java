package ca.carleton.poker.service.input.impl;

import ca.carleton.poker.service.input.InputService;

import java.util.Scanner;

/**
 * Default input service class that uses the command line for user input.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public class DefaultInputService implements InputService {

    private final Scanner input;

    protected DefaultInputService() {
        this.input = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return this.input.nextLine();
    }
}
