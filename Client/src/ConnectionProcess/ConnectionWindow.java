package ConnectionProcess; /**
 * Created by DoctorZlo on 26.12.2017.
 */
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConnectionWindow {
    private  HashMap<String, String> connectionInfo = null;
    private  JFrame mainFrame;
    private  Socket socket = null;

    public void getConnectionWindow() {
        mainFrame = new JFrame("Connection window");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);

        mainFrame.getContentPane().add(getFrameContent(), "Center");
        mainFrame.setVisible(true);
    }

    public Socket getSocket() {
        return socket;
    }

    //SERVICE
    private void connect() {
        try {
            ConnectionManager manager = new ConnectionManager();
            manager.getConnection(connectionInfo);
            socket = manager.getSocket();
        } catch (Exception e) {
            e.printStackTrace();
            getErrorMessage();
        }
    }

    private void getErrorMessage() {
        JFrame frame = new JFrame("Error");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.lightGray);
        boardPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel error = new JLabel("Подключение не удалось.");
        error.setFont(new Font("Serif", Font.PLAIN, 24));
        JButton submit = new JButton("Вернуться");
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getConnectionWindow();
                frame.setVisible(false);
            }
        });

        boardPanel.add(error);
        boardPanel.add(submit);

        frame.getContentPane().add(boardPanel, "Center");
        frame.setVisible(true);
    }

    private JTextField getIpFild() {
        JTextField ipField = new JTextField();
        ipField.setFont(new Font("Serif", Font.PLAIN, 24));
        ipField.setBackground(Color.RED);
        ipField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String input = ipField.getText();
                Pattern patt = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])(\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])){3}$");
                Matcher matcher = patt.matcher(input);
                if(matcher.matches()) {
                    ipField.setBackground(Color.cyan);
                } else {
                    ipField.setBackground(Color.RED);
                }
            }
        });
        return ipField;
    }

    private JPanel getFrameContent() {
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.lightGray);
        boardPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel ipHint = new JLabel("Введите ip:");
        ipHint.setFont(new Font("Serif", Font.PLAIN, 36));
        JTextField ipField = getIpFild();

        JLabel portHint = new JLabel("Введите port:");
        portHint.setFont(new Font("Serif", Font.PLAIN, 36));
        JTextField portField = getPortFild();

        JButton submit = new JButton("Подключиться");

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setConnectionInfo(ipField.getText(), portField.getText());
                mainFrame.setVisible(false);
                connect();
            }
        });

        boardPanel.add(ipHint);
        boardPanel.add(ipField);
        boardPanel.add(portHint);
        boardPanel.add(portField);
        boardPanel.add(submit);

        return boardPanel;
    }

    private JTextField getPortFild() {
        JTextField portField = new JTextField();
        portField.setFont(new Font("Serif", Font.PLAIN, 24));
        portField.setBackground(Color.RED);
        portField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String input = portField.getText();
                Pattern patt = Pattern.compile("^[0-9]{2,4}$");
                Matcher matcher = patt.matcher(input);
                if(matcher.matches()) {
                    portField.setBackground(Color.cyan);
                } else {
                    portField.setBackground(Color.RED);
                }
            }
        });
        return portField;
    }

    private void setConnectionInfo(String ip, String port) {
        HashMap<String, String> conn = new HashMap<>();
        conn.put("ip", ip);
        conn.put("port", port);
        connectionInfo = conn;
    }
}
