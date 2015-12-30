package lib;

import javafx.event.ActionEvent;
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
    private static int MAX_SCORES = 10;
    public static Button nameEntryButton = new Button("OK");


    //Tulemuste kuvamine, vajalik HighScoreViews
    public static String printOutHighScores() {
        String textToDisplay = "";
        getResults();
        if (highScoresList != null) {
            //print NIMI vahe mingi siia ja SKOOR
            for (int i = 0; i < highScoresList.length; i++) {
                textToDisplay += (i + 1) + ". " + highScoresList[i][0] + " " + highScoresList[i][1] + "\n";
            }

        } else {
            textToDisplay = "Tulemusi ei leitud";
        }
        //set textToDisplay font !!!

        return textToDisplay;
    }


    //M?nguskoori lisamine, seda meetodit kasutame main classis
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

    //Nime k?simise dialoog, vormindust vaja muuta veel
    private static void getName() {
        Stage nameEntryWindow = new Stage();
        BorderPane borderPane = new BorderPane();
        Label label = new Label("Sisesta oma nimi");
        TextField nameEntryField = new TextField();
      //SIIA Meetod kuidas koma välistada
        // nameEntryField.setOnKeyPressed(event1 -> {
      //    if (nameEntryField.getUserData().toString().charAt(((nameEntryField.getUserData()).toString()).length() - 1) == ','){
      //        nameEntryField.setText(((nameEntryField.getUserData()).toString()).substring(0, (((nameEntryField.getUserData()).toString()).length()-2)));
      //    }
      //});
        nameEntryButton.addEventHandler(ActionEvent.ACTION, event -> {
            nameEntryField.getText();
            writeToScoresFile(nameEntryField.getText());
            nameEntryWindow.close();
            System.out.println("nameEntryButton pressed action in GetName method");
        });

        borderPane.setTop(label);
        borderPane.setCenter(nameEntryField);
        borderPane.setBottom(nameEntryButton);
        Scene getNameWindowScene = new Scene(borderPane, 200, 100);
        nameEntryWindow.setScene(getNameWindowScene);
        nameEntryWindow.show();
    }

    //vajalik, et faili kirjutades uus tulemus �igesse kohta l�heks
    private static int lowestScoreRowNumber() {
        getResults();
        if (highScoresList!= null) {
            int i = highScoresList.length;
            for (String b[]:highScoresList) {
                if (score > Integer.parseInt(b[1])) {
                    i--;
                }
            }
            return i;
        }
        return 0;
    }

    //Tulemuste faili kirjutamine
    private static void writeToScoresFile(String name) {
        index = lowestScoreRowNumber();
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
            if (highScoresList != null) {
                int i = 0;
                for (String result[]: highScoresList) {
                    if (i < MAX_SCORES - 1) {
                        if (i == index) {
                            //uue tulemuse faili kirjutamine
                            printWriter.println(name + "," + score);
                        }
                        //eelmised tulemused
                        printWriter.println(result[0] + "," + result[1]);
                        if (i == highScoresList.length - 1 && index > i) {
                            printWriter.println(name + "," + score);
                        }
                        i++;
                    }
                }
            } else {
                printWriter.println(name + "," + score);
            }
            printWriter.close();
        } catch (IOException e) {
        }
    }

}
