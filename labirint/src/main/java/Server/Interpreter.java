package Server;

import Keywords.Direction;

import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by User on 26.12.2017.
 */
public class Interpreter {
    public static void interpret(String src, Game g, Game.Player p) throws IOException {
        String[] tokens = src.split(" ");
        Stack<String> machine = new Stack<String>();
        for (int i = tokens.length-1; i > 0; i--) {
            if (tokens[i].matches("[0-9]+") || tokens[i].equals(")")) {
                machine.push(tokens[i]);
            }
            if (tokens[i].equals("up") || tokens[i].equals("down") || tokens[i].equals("left") || tokens[i].equals("right") || tokens[i].equals("none")) {
                if (!g.isOptionsPassed()) {
                    machine.push(tokens[i]);
                    continue;
                }
                machine.push(tokens[i]);
            }
            if (tokens[i].equals("move")) {
                if (!g.isOptionsPassed()) {
                    machine.push(tokens[i]);
                    continue;
                }
                String dir = machine.pop();
                Direction d = Direction.none;
                if (dir.equals("up")) {
                    d = Direction.up;
                }
                if (dir.equals("down")) {
                    d = Direction.down;
                }
                if (dir.equals("left")) {
                    d = Direction.left;
                }
                if (dir.equals("right")) {
                    d = Direction.right;
                }
                p.move(d);
                g.addToTurn("( move " + dir + " )");
                String check = machine.pop();
                if (!check.equals(")") || !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
            if (tokens[i].equals("look")) {
                if (!g.isOptionsPassed()) {
                    machine.push(tokens[i]);
                    continue;
                }
                String dir = machine.pop();
                Direction d = Direction.none;
                if (dir.equals("up")) {
                    d = Direction.up;
                }
                if (dir.equals("down")) {
                    d = Direction.down;
                }
                if (dir.equals("left")) {
                    d = Direction.left;
                }
                if (dir.equals("right")) {
                    d = Direction.right;
                }
                String s = String.valueOf(g.getPlayers().get(g.currentPlayer).look(d));
                if (d != Direction.none) {
                    p.send("( block " + s + " )");
                    g.setBlock("( block " + s + " )");
                }
                g.addToTurn("( look " + dir + " )");
                String check = machine.pop();
                if (!check.equals(")") && !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
            if (tokens[i].equals("blow")) {
                if (!g.isOptionsPassed()) {
                    machine.push(tokens[i]);
                    continue;
                }
                String dir = machine.pop();
                Direction d = Direction.none;
                if (dir.equals("up")) {
                    d = Direction.up;
                }
                if (dir.equals("down")) {
                    d = Direction.down;
                }
                if (dir.equals("left")) {
                    d = Direction.left;
                }
                if (dir.equals("right")) {
                    d = Direction.right;
                }
                if (p.blow(d)) {
                    p.send(" win");
                }
                g.addToTurn("( blow " + dir + " )");
                String check = machine.pop();
                if (!check.equals(")") && !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
            /*
            if (tokens[i].equals("greeting")) {
                System.out.println("Sending initialize");
                p.send("( greeting " + p.getPlayerId() + " )");
                System.out.println("Приветствие отправлено, АЙ ДИ отдан");
                String check = machine.pop();
                if (!check.equals(")") && !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
            */
            if (tokens[i].equals("options")) {
                String s = machine.pop();
                HashSet<String> rules = new HashSet<String>();
                while (!s.equals(")")) {
                    rules.add(s);
                    s = machine.pop();
                }
                machine.push(s);
                g.selectRules(rules);
                g.rulescount++;
                if (g.rulescount == 2) {
                    g.notifyRules();
                }
                String check = machine.pop();
                if (!check.equals(")") && !tokens[i - 1].equals("(")) {
                    throw new RuntimeException("Неправильно расставлены скобки!");
                }
            }
        }
        if(g.isOptionsPassed()) {
            g.currentPlayer++;
            if (g.currentPlayer == g.getPlayers().size()) {
                g.currentPlayer = 0;
            }
            g.sendTurn();
        }
    }
}
