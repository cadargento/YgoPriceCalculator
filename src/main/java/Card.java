import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Card {
    public static ArrayList<String> cardFields = new ArrayList<>(Arrays.asList(
            "name", "type", "desc", "atk", "def", "level", "race", "attribute"));

    private String name;
    private String type;
    private String desc;
    private String atk;
    private String def;
    private String level;
    private String race;
    private String attribute;

    @Override
    public String toString() {
        return "main.Card: \n" +
                "\tName: " + this.name + "\n" +
                "\tType: " + this.type + "\n" +
                "\tDesc: " + this.desc + "\n" +
                "\tAtk: " + this.atk + "\n" +
                "\tDef: " + this.def + "\n" +
                "\tLevel: " + this.level + "\n" +
                "\tRace: " + this.race + "\n" +
                "\tAttribute: " + this.attribute + "\n"
                ;
    }

    public String getName() { return name;}
    public String getType() { return type;}
    public String getDesc() { return desc;}
    public String getAtk() { return atk;}
    public String getDef() { return def;}
    public String getLevel() { return level;}
    public String getRace() { return race;}
    public String getAttribute() { return attribute;}

    public Card (Map<String, String> cardInfoMap) {
        this.name = cardInfoMap.get("name");
        this.type = cardInfoMap.get("type");
        this.desc = cardInfoMap.get("desc");
        this.atk = cardInfoMap.get("atk");
        this.def = cardInfoMap.get("def");
        this.level = cardInfoMap.get("level");
        this.race = cardInfoMap.get("race");
        this.attribute = cardInfoMap.get("attribute");

    }

    public static class CardBuilder
    {
        private String name;
        private String type;
        private String desc;
        private String atk;
        private String def;
        private String level;
        private String race;
        private String attribute;


    }


    public static void main(String[] args) {
        Card c = YugiohPricesInterface.CardData("83994646");

    }

}
