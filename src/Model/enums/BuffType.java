package Model.enums;

public enum BuffType {
    HOLY("Holy"),
    ATTACK_POWER("Power"), HEALTH_POWER("Power"),
    POISON("Poison"),
    ATTACK_WEAKNESS("Weakness"), HEALTH_WEAKNESS("Weakness"),
    STUN("Stun"),
    DISARM("Disarm"),
    CELL_EFFECT_HOLY("CellEffect"),
    CELL_EFFECT_POISON("CellEffect"),
    CELL_EFFECT_FIERY("CellEffect"),
    REMOVE_ENEMIES_BUFFS("DestroyPositives"),
    REMOVE_INSIDERS_BUFFS("DestroyNegatives"),
    KILL("Kill"),
    INCREASE_MANA("IncreaseMana"),
    NONE("None"),
    ;

    private String kind;

    private BuffType(String kind){
        this.kind = kind;
    }


    @Override
    public String toString() {
        return kind;
    }
}
