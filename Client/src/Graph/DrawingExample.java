package Graph;

import Entities.Player;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.CSIColor;

import java.awt.*;
import java.util.Properties;

public class DrawingExample{
    public static void main(String[] args){
        Properties text = new Properties();
        text.setProperty("fontSize","20");
        text.setProperty("font", "Courier");
        WSwingConsoleInterface csi = null;

        try{
            csi = new WSwingConsoleInterface("Game map example", text);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            eiie.printStackTrace();
            System.exit(-1);
        }


        csi.print(0, 0, "Your window", CSIColor.ALIZARIN);
        csi.print(20, 0, "Enemy window", CSIColor.ALIZARIN);

        Drawing.drawMap(new Player(0), csi, true);
        Drawing.drawMap(new Player(1), csi, false);
    }
}
