package com.mkiisoft.java;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokesLibrary {

    private List<String> jokes = Arrays.asList(
            "I invented a new word! plagiarism!",
            "What's the best thing about switzerland? I don't know, but the flag is a big plus.",
            "Hear about the new restaurant called karma? There's no menu: You get what you deserve.",
            "Accordion to research, 9 out of 10 people don't notice when you replace words with random musical instruments.",
            "-Anton, do you think I’m a bad mother?\n-My name is Paul.",
            "Patient: Oh doctor, I’m just so nervous. This is my first operation.\nDoctor: Don't worry. Mine too.",
            "I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.",
            "I thought I’d tell you a good time travel joke – but you didn't like it.",
            "What goes up and down but never moves? \nThe stairs!");

    public String generateJoke() {
        int random = new Random().nextInt(jokes.size());
        return jokes.get(random);
    }
}