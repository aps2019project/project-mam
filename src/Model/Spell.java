package Model;

public class Spell extends Card{
    private TargetCommunity TargetCommunity;

    public Spell(String name, int price, int MP, Model.TargetCommunity targetCommunity, String desc) {
        super(name, price, MP, desc);
        TargetCommunity = targetCommunity;
    }

    public Model.TargetCommunity getTargetCommunity() {
        return TargetCommunity;
    }
}
