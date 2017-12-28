import ConnectionProcess.ConnectionWindow;
import Entities.Language;
import Entities.Player;

import Graph.Drawing;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.CSIColor;

import java.util.Properties;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by DoctorZlo on 26.12.2017.
 */
public class GameProcess {
    private  Socket socket;
    private  BufferedReader input;
    private  Writer output;
    private  ArrayList<Player> players = new ArrayList<>();
    private  int selfId;
    private  int currentPlayer = 0;
    private  int selectedEnemyId;
    private  Interpreter interpreter;
    private  boolean gameState = true;
    private volatile WSwingConsoleInterface frame;

    public GameProcess() {
        this.interpreter = new Interpreter(this);
    }

    public  void startGame() throws IOException {
        ConnectionWindow window = new ConnectionWindow();
        window.getConnectionWindow();

        while (window.getSocket() == null) {}
        socket = window.getSocket();

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        Properties text = new Properties();
        text.setProperty("fontSize","15");
        text.setProperty("font", "Courier");
        frame = null;
        try{
            frame = new WSwingConsoleInterface("Boooom", text);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            eiie.printStackTrace();
            System.exit(-1);
        }

        greetengs();
        options();

        prepareWindow();
        process();
    }

    public  void process() throws IOException {
        System.out.println("Игровой процесс начат");
        while(gameState) {
            prepareWindow();
            /*
            if (currentPlayer == selfId) {
                turn();
            } else {
                String sourse = input.readLine();
                System.out.println(sourse);
                interpreter.interprete(sourse);
            }
            */
        }
    }

    public  void turn() {
        look();
        move();
        throwBomb();
    }

    public  void look() {

    }

    public  void move() {

    }

    public  void throwBomb() {

    }

    public void changeSelectedEnemy() {

    }



    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public  int getSelfId() {
        return selfId;
    }

    public void endGame() {
        gameState = false;
    }




    //First options
    private  void prepareWindow() {
        if(selfId != 0) {
            selectedEnemyId = 0;
        } else {
            selectedEnemyId = 1;
        }

        frame.print(0, 0, "Your window", CSIColor.ALIZARIN);
        frame.print(20, 0, "Enemy window", CSIColor.ALIZARIN);

        Drawing.drawMap(players.get(selfId), frame, true);
        Drawing.drawMap(players.get(selectedEnemyId), frame, false);
        System.out.println("Окно готово");
    }

    private  void greetengs() throws IOException {
        output.write(" ( greeting ) \n");
        output.flush();
        System.out.println("Отправляю приветствие!");
        interpreter.interprete(input.readLine());
        System.out.println("Получил ответное приветствие: мой АЙ ДИ:" + selfId);
    }


    private void options() throws IOException {
        String options = "";
        for (String keyword:
                Language.getLanguage()) {
            options += (keyword + " ");
        }
        output.write("( options "+ options +" )\n");
        output.flush();
        System.out.println("Отправлены опции");
        String optionsSet = input.readLine();
        interpreter.interprete(optionsSet);
        System.out.println("Получены опции сервера: " + optionsSet);
        //then, the number of players
        interpreter.interprete(input.readLine());
        System.out.println("Число игроков: " + players.size());
    }

    public  void fillPlayerList(int nuberOfPlayers) {
        for (int i = 0; i < nuberOfPlayers; i++) {
            players.add(new Player(i));
        }
    }
    public  void setSelfId(int id) {
        selfId = id;
    }
}
