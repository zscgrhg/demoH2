package com.example.demo;

import java.util.Random;

public class Hellowords {
      String welcome;


    public Hellowords(String welcome) {
        this.welcome = welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getWelcome() {
        return welcome;
    }

    @Override
    public String toString() {
        return "Hellowords{" +
                "welcome='" + welcome + '\'' +
                '}';
    }
}
