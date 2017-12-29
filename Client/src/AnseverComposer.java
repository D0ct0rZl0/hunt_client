import Entities.Player;

/**
 * Created by DoctorZlo on 27.12.2017.
 */
public class AnseverComposer {
    private static String answer = "";

    public static void init(Player player) {
        answer += player.getId() + " ";
    }

    public static void addLook(String direction) {
        answer += " ( look" + " " + direction + " ) ";
    }

    public static void addMove(String direction) {
        answer += " ( move" + " " + direction + " ) ";
    }

    public static void addBlow(String direction) {
        answer += " ( blow" + " " + direction + " ) ";
    }

    public static String getAnswer() {
        String reply = answer;
        answer = "";
        return answer;
    }
}
