package me.djelectro.prevuecontroller;


import me.djelectro.prevuecontroller.menus.MainMenu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Util.clearConsole();
        System.out.println("Welcome to Prevue Controller");
        Thread.sleep(1000);
        new MainMenu(new ArrayList<>());
    }

}
