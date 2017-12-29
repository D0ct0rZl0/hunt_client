package Graph;

import Entities.Map;
import Entities.Player;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by DoctorZlo on 29.12.2017.
 */
public class ConsoleDrawing {
    public static void drawAll(ArrayList<Player> players, int selfId, int currentEnemyId, JTextArea textArea, String currentStatus) {
        textArea.setText("");
        Map self = players.get(selfId).getMap();
        Map enemy = players.get(currentEnemyId).getMap();

        String field = "Your map:            Enemy №"+currentEnemyId+" map:";
        for (int i = 0; i < self.getHeight(); i++) {
            for (int j = 0; j < self.getWeight(); j++) {
                field += self.getByCoords(i, j);
            }
            field += "          ";
            for (int j = 0; j < self.getWeight(); j++) {
                field += enemy.getByCoords(i, j);
            }
            field += "\n";
        }
        field += "\n";
        field += currentStatus;
        field += "\n\n\n\n\n";
        textArea.setText(field);
    }
    public static void drawSmth(JTextArea textArea) {
        textArea.setText("");
        textArea.setText("HUI");
    }
}
