package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;
import me.djelectro.genday.Genday;

public class BackTestMenu extends Menu {

    public BackTestMenu(ArrayList<Menu> menuHistory1) {
        super(menuHistory1, "Test Menu", "Select a Test Menu Option.", new HashMap<Integer, String>() {{

            put(1, "Test Console Height/Width Formatting");
            put(2, "[Test] Go Back");
            put(3, "Generate Curday");

        }});

        print();
    }

    @Override
    protected void doAnswer(int choice){
        switch(choice) {
            case 1:
                String oof = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                System.out.println(oof);
                int consoleSize = Util.getTerminalHeight();
                consoleSize -= (oof.length() / Util.getTerminalWidth()) + 2;
                consoleSize -= oof.chars().filter(ch -> ch == '\n').count();
                while (consoleSize != 0) {
                    System.out.print("\n");
                    consoleSize--;
                }
                System.out.print("END");
                try {
                    Thread.sleep(5000);
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    print();
                }
                break;
            case 2:
                goBack();
                break;
            case 3:
                try {
                    String res = Genday.genCurday("curday.dat", "https://djelectro.endl.site/tv/buildxml.php?action=raw", 6, false, "PHIL", "Philadelphia");
                    updateQuestion(res + "\n" + getQuestion());
                    print();
                }catch (Exception e){
                    //e.printStackTrace();
                    updateQuestion("There was an error.\n" + e.getMessage() + getQuestion());
                    print();
                }
                break;
        }
    }


}
