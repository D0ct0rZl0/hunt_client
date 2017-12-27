package Entities;

import net.slashie.libjcsi.CSIColor;

import java.util.HashMap;

/**
 * Created by DoctorZlo on 27.12.2017.
 */

public class Language {
    private static String[] language = new String[] {
      "up", "down", "left", "right",
            "move", "look", "blow", "greeting", "options", "win", "lose", "rules"
    };

    public static String[] getLanguage() {
        return language;
    }
}
