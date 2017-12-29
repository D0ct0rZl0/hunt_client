package Graph;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PseudoConsole extends JFrame {

    public static JTextArea getConsole() {
        Console console = new Console();
        console.init();
        PseudoConsole launcher = new PseudoConsole();
        launcher.setVisible(false);
        console.getFrame().setLocation(
                launcher.getX() + launcher.getWidth() + launcher.getInsets().right,
                launcher.getY());
        return console.getTextArea();
    }

    public PseudoConsole() {
        super();
        setSize(600, 600);
        setTitle("KaBooom!!!");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class Console {
    final JFrame frame = new JFrame();
    private JTextArea textArea;
    public Console() {
        textArea = new JTextArea(40, 120);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        }));
        frame.add(textArea);
    }
    public void init() {
        frame.pack();
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
    public JTextArea getTextArea() {
        return textArea;
    }
}