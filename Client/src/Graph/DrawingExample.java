package Graph;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;
import java.util.Properties;

public class DrawingExample{
    public static void main(String[] args){
        Properties text = new Properties();
        text.setProperty("fontSize","20");
        text.setProperty("font", "Courier");
        ConsoleSystemInterface csi = null;
        try{
            csi = new WSwingConsoleInterface("Game map example", text);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            eiie.printStackTrace();
            System.exit(-1);
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                csi.print(i,j, "Ы", CSIColor.AMBER);
            }
        }
        csi.print(0, 22, "Ваше поле", CSIColor.AMBER);
        for (int i = 30; i < 50; i++) {
            for (int j = 0; j < 20; j++) {
                csi.print(i,j, "A", CSIColor.AMBER);
            }
        }
        csi.print(30, 22, "Поле противника", CSIColor.AMBER);
        csi.print(60, 0, "Область инструкций", CSIColor.AMBER);
        csi.refresh();
    }
}