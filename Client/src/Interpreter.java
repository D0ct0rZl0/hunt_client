import java.util.Stack;

/**
 * Created by DoctorZlo on 27.12.2017.
 */
public class Interpreter {
    private GameProcess process;

    public Interpreter(GameProcess process) {
        this.process = process;
    }

    public void interprete(String src) {
        String[] tokens = src.split(" ");
        Stack<String> machine = new Stack<String>();
        for (int i = tokens.length - 1; i > 0; i--) {
            if (tokens[i].matches("[0-9]+") || tokens[i].equals(")")) {
                machine.push(tokens[i]);
            }
            if(tokens[i].equals("greeting")) {
                process.setSelfId(Integer.parseInt(machine.pop()));
                String check = machine.pop();
                if (!check.equals(")") || !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
            if(tokens[i].equals("rules")) {
                System.out.println("rules keyword doesn't interpreted yet");
            }
            if(tokens[i].equals("userscount")) {
                process.fillPlayerList(Integer.parseInt(machine.pop()));
                String check = machine.pop();
                if (!check.equals(")") || !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
        }
    }
}
