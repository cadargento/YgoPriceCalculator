import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deck {
    private String filename;
    private ArrayList<Card> mainDeck = new ArrayList<>();
    private ArrayList<Card> sideDeck = new ArrayList<>();
    private ArrayList<Card> extraDeck = new ArrayList<>();
    private Map<String, ArrayList<Card> > fullDeck = new HashMap<>();



    public Deck(String inFile) {
        filename = inFile;
        ReadInFile();
        int i;
        fullDeck.put("main", mainDeck);
        fullDeck.put("extra", extraDeck);
        fullDeck.put("side", sideDeck);

    }

    private void ReadInFile() {
        /*
        #main
        #extra
        !side
         */
        String location = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (line.contains("#") || line.contains("side")) {
                    if (line.contains("main")) {
                        location = "main";
                    } else if (line.contains("extra")) {
                        location = "extra";
                    } else if (line.contains("side")) {
                        location = "side";
                    }
                }
                else {
                    Card c = YugiohPricesInterface.CardData(line);
                    switch (location) {
                        case "main":
                            mainDeck.add(c);
                            break;
                        case "extra":
                            extraDeck.add(c);
                            break;
                        case "side":
                            sideDeck.add(c);
                            break;
                        default:
                            break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Deck d = new Deck("/home/dev/IdeaProjects/YugiohPriceCalculator/src/self/SD/pricecalc/JoeyDeck.ydk");
        System.out.println("Done.");
    }
}
