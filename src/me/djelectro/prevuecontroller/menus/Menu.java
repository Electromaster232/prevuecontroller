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
    private String menuName;


    public Menu(ArrayList<Menu> menuHistory1, String menuName1, String question1, Map<Integer, String> answerChoices1){
        menuHistory = menuHistory1;
        question = question1;
        answerChoices = answerChoices1;
        menuHistory.add(this);
        menuName = "=========== " + menuName1 + " ===========\n";
    }


    protected void updateQuestion(String newQuestion){question = newQuestion;}

    protected String getQuestion(){return question;}

    protected void updateAnswers(Map<Integer, String> newMap){answerChoices = newMap;}

    public void print(){
        int consoleSize = Util.getTerminalHeight();
        Util.clearConsole();

        String finalMenuString = menuName + question;
        System.out.println(finalMenuString);

        consoleSize -= (finalMenuString.length() / Util.getTerminalWidth()) + 2;
        consoleSize -= finalMenuString.chars().filter(ch -> ch == '\n').count();

        StringBuilder answerString = new StringBuilder();
        answerChoices.forEach((a, n) -> {
            answerString.append("(").append(a).append(") ").append(n).append("  ");});

        consoleSize -= (answerString.length() / Util.getTerminalWidth());

        while(consoleSize != 0){System.out.print("\n"); consoleSize--;}
        System.out.print(answerString);

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
