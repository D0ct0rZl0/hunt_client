package ConnectionProcess;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by DoctorZlo on 26.12.2017.
 */
public class ConnectionManager {
    private static Socket socket;

    static void getConnection(HashMap<String, String> connData) throws Exception{
        String ip = connData.get("ip");
        int port = Integer.parseInt(connData.get("port"));
        socket = new Socket(ip, port);
    }

    public static Socket getSocket() {
        return socket;
    }
}
