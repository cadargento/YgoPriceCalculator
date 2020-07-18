import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Card_With_Builder {
    public static ArrayList<String> cardFields = new ArrayList<>(Arrays.asList(
            "name", "text", "card_type", "type", "family", "atk", "def", "level", "property"));

    private final String name;
    private final String text;
    private final String card_type;
    private final String type;
    private final String family;
    private final String atk;
    private final String def;
    private final String level;
    private final String property;

    @Override
    public String toString() {
        return "main.Card: \n" +
                "\tName: " + this.name + "\n" +
                "\tText: " + this.text + "\n" +
                "\tCard Type: " + this.card_type + "\n" +
                "\tType: " + this.type + "\n" +
                "\tFamily: " + this.family + "\n" +
                "\tAttack: " + this.atk + "\n" +
                "\tDefense: " + this.def + "\n" +
                "\tLevel: " + this.level + "\n" +
                "\tProperty: " + this.property + "\n"
                ;
    }

    private Card_With_Builder(CardBuilder builder) {
        this.name = builder.name;
        this.text = builder.text;
        this.card_type = builder.card_type;
        this.type = builder.type;
        this.family = builder.family;
        this.atk = builder.atk;
        this.def = builder.def;
        this.level = builder.level;
        this.property = builder.property;
    }



    public String getName() { return name;}
    public String getText() { return text;}
    public String getCardType() { return card_type;}
    public String getType() { return type;}
    public String getFamily() { return family;}
    public String getAtk() { return atk;}
    public String getDef() { return def;}
    public String getLevel() { return level;}
    public String getProperty() { return property;}


    public static class CardBuilder
    {
        private final String name;
        private String text;
        private String card_type;
        private String type;
        private String family;
        private String atk;
        private String def;
        private String level;
        private String property;


        public CardBuilder(String name) {
            this.name = name;
        }
        public CardBuilder text(String text) {
            this.text = text;
            return this;
        }
        public CardBuilder card_type(String card_type) {
            this.card_type = card_type;
            return this;
        }
        public CardBuilder type(String type) {
            this.type = type;
            return this;
        }
        public CardBuilder family(String family) {
            this.family = family;
            return this;
        }
        public CardBuilder atk(String atk) {
            this.atk = atk;
            return this;
        }
        public CardBuilder def(String def) {
            this.def = def;
            return this;
        }
        public CardBuilder level(String level) {
            this.level = level;
            return this;
        }
        public CardBuilder property(String property) {
            this.property = property;
            return this;
        }


        //Return the finally consrcuted main.User object
        public Card_With_Builder build() {
            Card_With_Builder card =  new Card_With_Builder(this);
            validateUserObject(card);
            return card;
        }
        private void validateUserObject(Card_With_Builder card) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }

    public static void main(String[] args) {
        Card_With_Builder card1 = new CardBuilder("Blue-Eyes White Dragon")
                .text("This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.")
                .card_type("monster")
                .type("Dragon / Normal")
                .family("light")
                .atk("3000")
                .def("2500")
                .level("8")
                // no property
                .build();
        System.out.println(card1);
    }

}
