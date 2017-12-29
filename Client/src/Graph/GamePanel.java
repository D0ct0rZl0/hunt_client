package Graph;

import Entities.Map;
import Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by DoctorZlo on 29.12.2017.
 */
public class GamePanel extends JPanel {
    private int selfId;
    private int selectedEnemyId;
    private String state;
    private ArrayList<Player> players;

    private Graphics2D g2;

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public int getSelfId() {
        return selfId;
    }
    public int getSelectedEnemyId() {
        return selectedEnemyId;
    }
    public String getState() {
        return state;
    }

    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public void setSelectedEnemyId(int selectedEnemyId) {
        this.selectedEnemyId = selectedEnemyId;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 800, 600);
        g2.setColor(Color.WHITE);
        Font font = new Font("Tahoma", Font.BOLD, 20);

        g2.setFont(font);
        Map self = players.get(selfId).getMap();
        Map selEnemy = players.get(selectedEnemyId).getMap();
        g2.drawString("Your map:", 0, 14);
        g2.drawString("Enemy №" + selectedEnemyId + " map:", self.getHeight()*15 + 50, 18);

        font = new Font("Tahoma", Font.BOLD, 14);
        g2.setFont(font);
        for (int i = 0; i < self.getHeight(); i++) {
            for (int j = 0; j < self.getWeight(); j++) {
                g2.drawString(self.getByCoords(i,j), i*15,  40 + j*15);
            }
        }
        for (int i = 0; i < selEnemy.getHeight(); i++) {
            for (int j = 0; j < selEnemy.getWeight(); j++) {
                g2.drawString(selEnemy.getByCoords(i,j), self.getHeight()*15 + 50 + i*15,  40 + j*15);
            }
        }

        g2.drawString(state, (self.getHeight()*15 + 50 + selEnemy.getHeight()*15)/2, 40 + selEnemy.getWeight()*15 + 50);
        g2.drawString("Tip: WASD keys for choosing direction", self.getHeight()*15 + 80 + selEnemy.getHeight()*15, 100);
        g2.drawString("SPACE for skipping action", self.getHeight()*15 + 100 + selEnemy.getHeight()*15, 140);
    }

    public void gameRepaint() {
        repaint();
    }
}
