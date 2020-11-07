package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class BackTestMenu extends Menu {

    public BackTestMenu(ArrayList<Menu> menuHistory1) {
        super(menuHistory1, "Go back or append and reprint?", new HashMap<Integer, String>() {{

            put(1, "Test Console Height/Width Formatting");
            put(2, "[Test] Go Back");

        }});

        print();
    }

    @Override
    protected void doAnswer(int choice){
        switch(choice){
            case 1:
                String oof = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                System.out.println(oof);
                int consoleSize = Util.getTerminalHeight();
                consoleSize -= (oof.length() / Util.getTerminalWidth()) + 2;
                while(consoleSize != 0){System.out.print("\n"); consoleSize--;}
                System.out.print("END");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                goBack();
                break;
        }
    }


}
