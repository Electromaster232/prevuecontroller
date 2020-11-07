package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(ArrayList<Menu> menuHistory1) {
        super(menuHistory1, "Select a category", new HashMap<Integer, String>() {{

            put(1, "Open Test Menu");
            put(2, "Quit");

        }});


        print();
    }

    @Override
    protected void doAnswer(int choice){
        switch(choice){
            case 1:
                new BackTestMenu(menuHistory);
                break;
            case 2:
                System.exit(0);
        }
    }


}
