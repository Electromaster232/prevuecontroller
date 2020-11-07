package me.djelectro.prevuecontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {

    public static int getTerminalHeight(){
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\u001b[s");
            System.out.print("\u001b[5000;5000H");
            System.out.print("\u001b[6n");
            System.out.print("\u001b[u");
            int result = Integer.parseInt(br.readLine().substring(7, 9));
            if(result < 20){
                result = 20;
            }
            return result;
        } catch (IOException | StringIndexOutOfBoundsException ioe) {
            //ioe.printStackTrace();
            return 20;
        }
         */
        try {
            return Integer.parseInt(System.getenv("LINES"));
        }catch(Exception e){
            return 10;
        }
    }

    public static int getTerminalWidth(){
        try {
            return Integer.parseInt(System.getenv("COLUMNS"));
        }catch(Exception e){
            return 80;
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }
}
