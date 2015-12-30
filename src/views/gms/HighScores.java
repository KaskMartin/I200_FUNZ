package views.gms;

import com.sun.javafx.event.EventRedirector;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventTarget;
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
    public static Button nameEntryButton = new Button("OK");

    //Tulemuste kuvamine, vajalik HighScoreViews
    public static String printOutHighScores() {
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

    //M�nguskoori lisamine, seda meetodit kasutame main classis
    public static void addScore(int gameScore) {
        score = gameScore;
        getName();
        getResults();
    }

    //Tulemuste laadimine failist
    public static void getResults() {
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
            String temporaryArray[][] = new String[i][2];
            i = 0;
            for (ArrayList a:scores) {
                temporaryArray[i][0] = a.get(0).toString();
                temporaryArray[i][1] = a.get(1).toString();
                i++;
            }
            highScoresList = temporaryArray;
        }
        catch (FileNotFoundException e) {
            highScoresList = null;
        }

    }

    //Sorteerimine, et oleks �iges j�rjekorras
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

    //Nime k�simise dialoog, vormindust vaja muuta veel
    private static void getName() {
        Stage nameEntryWindow = new Stage();
        BorderPane borderPane = new BorderPane();
        Label label = new Label("Sisesta oma nimi");
        TextField nameEntryField = new TextField();
        nameEntryButton.setOnAction(event -> {
            nameEntryField.getText();
            writeToScoresFile(nameEntryField.getText());
            nameEntryWindow.close();
        });

        borderPane.setTop(label);
        borderPane.setCenter(nameEntryField);
        borderPane.setBottom(nameEntryButton);
        Scene getNameWindowScene = new Scene(borderPane, 200, 100);
        nameEntryWindow.setScene(getNameWindowScene);
        nameEntryWindow.show();
    }

    //Tulemuste faili kirjutamine
    private static void writeToScoresFile(String name) {

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            int i = 0;
            if (highScoresList != null) {
                for (String result[]: highScoresList) {
                    if (i < 9) {
                        if (i == index) {
                            //uue tulemuse faili kirjutamine
                            fileWriter.write(name + "," + score + "\n");
                        }
                        //eelmise tulemuse allesj��miseks
                        fileWriter.write(result[0] + "," + result[1]);

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
