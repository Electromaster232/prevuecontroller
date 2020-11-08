package me.djelectro.prevuecontroller;

import jdk.nashorn.internal.codegen.CompilerConstants;
import net.twasi.obsremotejava.OBSRemoteController;
import net.twasi.obsremotejava.callbacks.Callback;
import net.twasi.obsremotejava.requests.ResponseBase;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
    public static Util Instance;
    private OBSRemoteController controller;

    static {
        Instance = new Util();
    }

    public Util() {
        controller = new OBSRemoteController("ws://localhost:4444", false);
    }

    public void shutdownController(){
        controller.registerDisconnectCallback(new Callback() {@Override public void run(ResponseBase responseBase) {

        }});
        controller.disconnect();
    }

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

    public void playVideo(String videoName){


        controller.setSourceVisibility("PrevueScene", videoName, true, new Callback() {
            @Override
            public void run(ResponseBase response) {
                if (response.getStatus().equals("ok")) {
                   displayMessage("Visibility changed.");
                } else {
                    displayMessage("Error while changing visibility: " + response.getError());
                }
            }
        });


        Thread newThread = new Thread(() -> {
            try {
                long length = getAudioLength(new File("videos/" + videoName + ".mp4"));
                Thread.sleep(length * 1000);
                controller.setSourceVisibility("PrevueScene", videoName, false, new Callback() {
                    @Override
                    public void run(ResponseBase response) {
                        if (response.getStatus().equals("ok")) {
                           displayMessage("Video ended. Visibility changed.");
                        } else {
                            displayMessage("Video ended. Error while changing visibility: " + response.getError());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newThread.start();
    }

    public void stopVideo(String videoName){

        controller.setSourceVisibility("PrevueScene", videoName, false, new Callback() {
            @Override
            public void run(ResponseBase response) {
                if (response.getStatus().equals("ok")) {
                    displayMessage("Visibility changed.");
                } else {
                    displayMessage("Error while changing visibility: " + response.getError());
                }
            }
        });
    }

    public static long getAudioLength(File file) throws Exception {
        IsoFile isoFile = new IsoFile(file);
        MovieHeaderBox mhb = isoFile.getMovieBox().getMovieHeaderBox();
        return mhb.getDuration() / mhb.getTimescale();
    }

    public void displayMessage(String message){
        clearConsole();
        String oof = "=========== Message ===========\n" + message;
        System.out.println(oof);
        int consoleSize = Util.getTerminalHeight();
        consoleSize -= (oof.length() / Util.getTerminalWidth()) + 2;
        consoleSize -= oof.chars().filter(ch -> ch == '\n').count();
        while (consoleSize != 0) {
            System.out.print("\n");
            consoleSize--;
        }
        System.out.println("The menu will return shortly");
    }
}
