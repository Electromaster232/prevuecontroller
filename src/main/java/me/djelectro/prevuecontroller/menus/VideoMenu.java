package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;
import me.djelectro.genday.Genday;

public class VideoMenu extends Menu {

    public VideoMenu(ArrayList<Menu> menuHistory1) {
        super(menuHistory1, "Video Menu", "Select a video option", new HashMap<Integer, String>() {{

            put(1, "Play TV Guide Demo Video");
            put(2, "Stop Demo Video");
            put(3, "Go Back");

        }});

        print();
    }

    @Override
    protected void doAnswer(int choice){
        switch(choice) {
            case 1:
                Util.Instance.playVideo("TestVideo");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print();
                break;
            case 2:
                Util.Instance.stopVideo("TestVideo");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print();
                break;
            case 3:
                goBack();
                break;
        }
    }


}
