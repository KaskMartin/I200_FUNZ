package views.gms;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class HighScores {
    private static final String fileName = "scores.txt"; //Faili nimi
    private static int score, index;
    private static String[][] highScoresList;

    //Tulemuste kuvamine, vajalik HighScoreViews
    public String printOutHighScores() {
        String textToDisplay = "";
        getResults();
        if (highScoresList != null) {
            for (int i = 0; i < highScoresList.length; i++) {
                textToDisplay += (i + 1) + ". " + highScoresList[i][0] + " " + highScoresList[i][1] + "\n";
            }

        } else {
            textToDisplay = "Tulemusi ei leitud";
        }

        return textToDisplay;

    }

    //Mänguskoori lisamine, seda meetodit kasutame main classis
    public static void addScore(int gameScore) {
        score = gameScore;
        getName();
    }

    //Tulemuste laadimine failist
    public void getResults() {
        ArrayList<ArrayList<String>> scores = new ArrayList<>();
        try {
            Scanner readFromFile = new Scanner(new FileReader(fileName));
            readFromFile.useDelimiter("\n");
            String line;
            int i = 0;
            while (readFromFile.hasNext()) {
                ArrayList<String> temp = new ArrayList<>();
                line = readFromFile.nextLine();
                temp.add(line.substring(0, line.lastIndexOf(',')));
                temp.add(line.substring(line.lastIndexOf(',')+ 1));
                scores.add(temp);
                i++;
            }
            //Ajutine array tulemuste listi jaoks
            String ajutineArray[][] = new String[i][2];
            i = 0;
            for (ArrayList a:scores) {
                ajutineArray[i][0] = a.get(0).toString();
                ajutineArray[i][1] = a.get(1).toString();
                i++;
            }
            highScoresList = ajutineArray;
        }
        catch (FileNotFoundException e) {
            highScoresList = null;
        }

    }

    //Sorteerimine, et oleks õiges järjekorras
    /*public class SortScores implements Comparator<Result> {
        public int compare(Result score1, Result score2) {
            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
    }*/

    //Nime küsimise dialoog, vormindust vaja muuta veel
    private static void getName() {
        Stage NimeSisestamisAken = new Stage();
        BorderPane paigutus = new BorderPane();
        Label label = new Label("Sisesta oma nimi");
        TextField nimeSisestamiseV2li = new TextField();
        Button nimeSisestamisNupp = new Button("OK");
        nimeSisestamisNupp.setOnAction(event -> {
            nimeSisestamiseV2li.getText();
            writeToScoresFile(nimeSisestamiseV2li.getText());
            NimeSisestamisAken.close();
        });

        paigutus.setTop(label);
        paigutus.setCenter(nimeSisestamiseV2li);
        paigutus.setBottom(nimeSisestamisNupp);
        Scene getName = new Scene(paigutus, 200, 100);
        NimeSisestamisAken.setScene(getName);
        NimeSisestamisAken.show();
    }

    //Tulemuste faili kirjutamine
    private static void writeToScoresFile(String name) {

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            int i = 0;
            if (highScoresList != null) {
                for (String tulemus[]: highScoresList) {
                    if (i < 9) {
                        if (i == index) {
                            //uue tulemuse faili kirjutamine
                            fileWriter.write(name + "," + score + "\n");
                        }
                        //eelmise tulemuse allesjäämiseks
                        fileWriter.write(tulemus[0] + "," + tulemus[1]);

                        if (i == highScoresList.length - 1 && index > i) {
                            fileWriter.write("\n" + name + "," + score);
                        }
                        i++;

                        if (i < highScoresList.length && i != 9) {
                            fileWriter.write("\n"); //reavahetus
                        }
                    }
                }
            } else {
                fileWriter.write(name + "," + score);
            }
            fileWriter.close();
        } catch (IOException e) {
        }
    }

}
