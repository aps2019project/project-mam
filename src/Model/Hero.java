package Model;

public class Hero extends Card {
    private int AP;
    private int HP;
    private int TargetCommunity;
    private int cooldown;
    private ImpactType heroClass;
    //private Spell specialPower;

    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity, int MP, int cooldown, String desc) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
    }

    public int getAP() {
        return AP;
    }

    public int getHP() {
        return HP;
    }

    public int getTargetCommunity() {
        return TargetCommunity;
    }

    public int getCooldown() {
        return cooldown;
    }

    public ImpactType getHeroClass() {
        return heroClass;
    }
}
