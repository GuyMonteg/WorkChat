package bohdan.webchat;

/**
 * Created by Monteg on 12.03.2017.
 */
public class Data {
    public static String host = "127.0.0.1";      // = "127.0.0.1"
    public static int port = 7707;         // = 7707
    private static String name;
    private static int iD;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Data.name = name;
    }

    public static int getiD() {
        return iD;
    }

    public static void setiD(int iD) {
        Data.iD = iD;
    }

    /*public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Data.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Data.port = port;
    }

    public void writeFileProperties(String host, int port) {
        String pathTofile = "D:\\Docs\\workChatServer.properties";

        try (FileWriter writer = new FileWriter(pathTofile, false)) {
            writer.write(host + "\n" + port);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error when trying to write to a file with server properties.");
        }
    }*/
}
