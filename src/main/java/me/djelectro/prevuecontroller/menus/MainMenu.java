package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(ArrayList<Menu> menuHistory1) {
        super(menuHistory1, "Main Menu", "Select a category", new HashMap<Integer, String>() {{

            put(1, "Open Test Menu");
            put(2, "Open Video Menu");
            put(3, "Quit");

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
                new VideoMenu(menuHistory);
                break;
            case 3:
                Util.Instance.shutdownController();
                System.exit(0);
                break;
        }
    }


}
