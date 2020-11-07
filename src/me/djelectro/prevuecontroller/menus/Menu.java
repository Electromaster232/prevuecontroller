package me.djelectro.prevuecontroller.menus;

import me.djelectro.prevuecontroller.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private String question = "";
    private Map<Integer, String> answerChoices = new HashMap<Integer, String>();
    protected ArrayList<Menu> menuHistory;


    public Menu(ArrayList<Menu> menuHistory1, String question1, Map<Integer, String> answerChoices1){
        menuHistory = menuHistory1;
        question = question1;
        answerChoices = answerChoices1;
        menuHistory.add(this);
    }


    protected void updateQuestion(String newQuestion){question = newQuestion;}

    protected void updateAnswers(Map<Integer, String> newMap){answerChoices = newMap;}

    public void print(){
        int consoleSize = Util.getTerminalHeight();
        Util.clearConsole();
        System.out.println(question);
        consoleSize -= (question.length() / Util.getTerminalWidth()) + 2;
        while(consoleSize != 0){System.out.print("\n"); consoleSize--;}
        answerChoices.forEach((a, n) -> {System.out.print("(" + a + ") " + n + "  ");});
        try {
            Scanner s1 = new Scanner(System.in);
            doAnswer(Integer.parseInt(s1.nextLine()));
        }catch(Exception e){
            System.out.println("Error Reading Input. Reloading Menu");
            print();
        }
    }

    // Override me!
    protected void doAnswer(int choice){System.out.println("Choice was " + choice + " Value was " + answerChoices.get(choice));}

    protected void goBack(){
        Menu toGoBack = menuHistory.get(menuHistory.size() - 2);
        menuHistory.add(toGoBack);
        toGoBack.print();
    }

}
