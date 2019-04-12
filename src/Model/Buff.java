package Model;

public class Buff {
    private BuffType type;
    private int time;
    private int BuffPower;
    private String sign;

    public Buff(BuffType type, int time, int buffPower) {
        this.type = type;
        this.time = time;
        BuffPower = buffPower;
    }

    public BuffType getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public int getBuffPower() {
        return BuffPower;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void decrementOfTime(){
        time--;
    }
}
