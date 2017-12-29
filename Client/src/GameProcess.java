import ConnectionProcess.ConnectionWindow;
import Entities.Language;
import Entities.Player;
import Graph.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by DoctorZlo on 26.12.2017.
 */
public class GameProcess {
    private  Socket socket;
    private  BufferedReader in;
    private  Writer out;
    private  ArrayList<Player> players = new ArrayList<>();
    private  int selfId;
    private  int currentPlayer = 0;
    private  int selectedEnemyId;
    private  Interpreter interpreter;
    private  boolean gameState = true;
    private  volatile GamePanel panel;

    public GameProcess() {
        this.interpreter = new Interpreter(this);
    }

    public  void startGame()  {
        ConnectionWindow window = new ConnectionWindow();
        window.getConnectionWindow();

        while (window.getSocket() == null) {}
        socket = window.getSocket();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        greetengs();

        prepareWindow();

        process();
    }

    public  void process() {
        System.out.println("Игровой процесс начат");
        while(gameState) {
            if (currentPlayer == selfId) {
                turn();
            } else {
                repaint("Please, wait for opponent turn");
                String sourse = null;
                try {
                    sourse = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(sourse);
                interpreter.interprete(sourse);
                repaint("Please, wait for opponent turn");
            }
        }
    }

    public  void turn() {
        look();
        move();
        throwBomb();
    }

    public  void look() {
        Player me = players.get(selfId);
        repaint("Please, choose look direction");
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyChar() == 'w'){
                    AnseverComposer.addLook("up");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 'a'){
                    AnseverComposer.addLook("left");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 's'){
                    AnseverComposer.addLook("down");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 'd'){
                    AnseverComposer.addLook("right");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == ' '){
                    AnseverComposer.addLook("none");
                    send(AnseverComposer.getAnswer());
                }
            }
        });
        repaint("Please, choose look direction");
    }

    public  void move() {
        Player me = players.get(selfId);
        repaint("Please, choose move direction");
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyChar() == 'w'){
                    AnseverComposer.addMove("up");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 'a'){
                    AnseverComposer.addMove("left");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 's'){
                    AnseverComposer.addMove("down");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == 'd'){
                    AnseverComposer.addMove("left");
                    send(AnseverComposer.getAnswer());
                    try {
                        interpreter.interprete(in.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(e.getKeyChar() == ' '){

                }
            }
        });
        repaint("Please, choose move direction");
    }

    public  void throwBomb() {
        Player me = players.get(selfId);
        repaint("Please, choose throwing bomb direction");
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyChar() == 'w'){

                }
                if(e.getKeyChar() == 'a'){

                }
                if(e.getKeyChar() == 's'){

                }
                if(e.getKeyChar() == 'd'){

                }
                if(e.getKeyChar() == ' '){

                }
            }
        });
        repaint("Please, choose throwing bomb direction");
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


    public void repaint(String state) {
        panel.setState(state);
        panel.setSelectedEnemyId(selectedEnemyId);
    }


    //First options
    private  void prepareWindow() {
        if(selfId != 0) {
            selectedEnemyId = 0;
        } else {
            selectedEnemyId = 1;
        }

        JFrame frame = new JFrame();
        frame.setTitle("Btooom!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        panel = new GamePanel();
        frame.getContentPane().add(panel, "Center");
        panel.gameRepaint();
        frame.setVisible(true);
        panel.setSelfId(selfId);
        panel.setPlayers(players);
        panel.setSelectedEnemyId(selectedEnemyId);
        panel.setState("Starting...");
        panel.gameRepaint();

        System.out.println("Окно готово");
    }

    private  void greetengs() {
        send(" ( greeting ) ");
        System.out.println("Отправляю приветствие!");
        try {
            interpreter.interprete(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Получил ответное приветствие: мой АЙ ДИ:" + selfId);

        String options = "";
        for (String keyword:
                Language.getLanguage()) {
            options += (keyword + " ");
        }
        send("( options "+ options +" )");
        System.out.println("Отправлены опции");
        try {
            interpreter.interprete(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Получены опции сервера");
        //then, the number of players
        try {
            interpreter.interprete(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void send(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
