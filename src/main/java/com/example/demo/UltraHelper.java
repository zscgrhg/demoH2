package com.example.demo;

import org.jboss.byteman.rule.Rule;

public class UltraHelper {

    public void wel() {
        System.out.println("welcome!");
    }

    public static void activated() {
        System.out.println("handle activated!");
    }

    public static void installed(Rule rule) {
    }



    public static void uninstalled(Rule rule) {
    }



    public static void deactivated() {
    }
}
