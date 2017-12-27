import ConnectionProcess.ConnectionManager;
import ConnectionProcess.ConnectionWindow;
import Entities.Language;
import Entities.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


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
    private  Interpreter interpreter;
    private  boolean gameState = true;

    public GameProcess() {
        this.interpreter = new Interpreter(this);
    }

    public  void startGame() throws IOException {
        ConnectionWindow.getConnectionWindow();
        socket = ConnectionManager.getSocket();
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        greetengs();
        options();

        process();
    }

    public  void process() throws IOException {
        while(gameState) {
            if (currentPlayer == selfId) {
                turn();
            } else {
                interpreter.interprete(input.readLine());
            }
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
    private  void greetengs() throws IOException {
        output.write("( greeting )");
        interpreter.interprete(input.readLine());
    }
    private void options() throws IOException {
        String options = "";
        for (String keyword:
                Language.getLanguage()) {
            options += (keyword + " ");
        }
        output.write("( "+ options +" )");
        interpreter.interprete(input.readLine());

        //then, the number of players
        interpreter.interprete(input.readLine());
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
