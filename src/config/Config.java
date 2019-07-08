package config;

public class Config {

    private static final Config CONFIG = new Config();

    public static Config getCONFIG() {
        return CONFIG;
    }

    private Config() {
    }

    public int speed = 1;
    public int turnTime = 60;
    public int port = 8000;
    public String ip = "127.0.0.1";

    public void init(int speed, int turnTime, int port, String ip) {
        this.speed = speed;
        this.turnTime = turnTime;
        this.port = port;
        this.ip = ip;
    }
}
