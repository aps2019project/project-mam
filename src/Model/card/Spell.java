package Model.card;

import Model.Buffs.Buff;

import java.util.ArrayList;

public class Spell extends Card {

    public Spell(String name, int price, int MP, String desc) {
        super(name, price, MP, desc);
        type = "spell";
    }

    public Spell() {
    }

    public Spell(String name, int price, int MP, ArrayList<Buff> buffs) {
        super(name, MP, price);
        type = "spell";
    }

    public Spell(String name, int price, int MP) {
        super(name, MP, price);
        type = "spell";
    }

    @Override
    public String getCardType() {
        return "spell";
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Spell - Name : ").append(getName()).append(" - MP : ").append(getMP());
        info.append(" - Description : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice())
                .append(" - count : ").append(getCount());
        return info.toString();
    }

    @Override
    public String getInfoInShop() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Spell\nName : ").append(getName()).append("\nMP : ").append(getMP());
        info.append("\nDescription : ").append(getDesc()).append("\nSell Cost : ").append(getPrice());
        return info.toString();
    }

    @Override
    public Card copyCard() {
        Card newCard = new Spell(this.getName(), this.getPrice(), this.getMP(), this.getSpecialPower());
        return newCard;
    }

    @Override
    public String getCardInfoInGame() {
        StringBuilder info = new StringBuilder();
        info.append("Spell:").append("\nName: ").append(this.getName());
        info.append("\nMP: ").append(this.getMP()).append("\nCost: ").append(this.getPrice());
        info.append("\nDesc: ").append(this.getDesc()).append("\n");
        return info.toString();
    }
}
