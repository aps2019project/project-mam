package Model;

public class Buff {
    private BuffType type;
    private int time;
    private int buffPower;
    private String sign;
    private TargetCommunity targetCommunity;

    public Buff(BuffType type, int time, int buffPower, Model.TargetCommunity targetCommunity) {
        this.type = type;
        this.time = time;
        this.buffPower = buffPower;
        this.targetCommunity = targetCommunity;
    }

    public BuffType getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public int getBuffPower() {
        return buffPower;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void decrementOfTime() {
        time--;
    }
}
