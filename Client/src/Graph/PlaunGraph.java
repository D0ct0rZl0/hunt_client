package Graph;

import javax.swing.*;

/**
 * Created by DoctorZlo on 29.12.2017.
 */
public class PlaunGraph {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Btooom!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        GamePanel panel = new GamePanel();
        frame.getContentPane().add(panel, "Center");
        panel.gameRepaint();
        frame.setVisible(true);
    }
}
