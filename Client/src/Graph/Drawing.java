package Graph;

import Entities.Direction;
import Entities.Block;
import Entities.Map;
import Entities.Player;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;
import properties.FrameMarkup;

import java.util.Properties;

/**
 * Created by DoctorZlo on 26.12.2017.
 */
public class Drawing {
    public static void drawMap(Player player, WSwingConsoleInterface frame, boolean isMyself) {
        Map map = player.getMap();
        int[] coords;
        if(isMyself) {
            coords = FrameMarkup.SMC;
        } else {
            coords = FrameMarkup.EMC;
        }

        for(int i = 0; i < map.getHeight(); i++) {
            for(int j = 0; j < map.getWeight(); j++) {
                frame.print(i + coords[0], j + coords[1], map.getByCoords(i, j), CSIColor.ALICE_BLUE);
            }
        }
    }
}
