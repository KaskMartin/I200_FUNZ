import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScores extends Game implements Serializable {
    private ArrayList<Results> scores;
    private static final String fileName = "scores.dat"; //Faili nimi
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    public TextField text;

    public HighScores() {
        scores = new  ArrayList<Results>();
    }

    public ArrayList<Results> getScoresList() {
        loadFile();
        sort();
        return scores;
    }

    private void sort() {
        SortScores comparator = new SortScores();
        Collections.sort(scores, comparator);
    }

    public void addNewScore(String name, int score) {
        loadFile();
        scores.add(new Results(name, score));
        updateFile();
    }

    public void loadFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            scores = (ArrayList<Results>) inputStream.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void updateFile() {
        loadFile();
        try{
            FileWriter write = new FileWriter(fileName);
            PrintWriter out = new PrintWriter(write);

            for (int i = 0; i < scores.size() && i < 10; i++){
                out.println(scores.get(i));
            }
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public String getHighScores() {
        String textToDisplay = "";
        int max = 10;

        ArrayList<Results> scores;
        scores = getScoresList();

        int i;
        int x = scores.size();

        if (x != 0) {
            for (i = 0; i < x; i++ ) {
                textToDisplay += (i + 1) + ". " + scores.get(i).getName() + " " + scores.get(i).getScore() + "\n";
            }
        } else textToDisplay = "Tulemusi ei ole";
        return textToDisplay;

    }

    public class SortScores implements Comparator<Results> {
        public int compare(Results score1, Results score2) {
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
    }

    public class Results {
        private int score;
        private String name;

        public int getScore() {

            return score;
        }

        public String getName() {
            Dialog<Game> dialog = new Dialog<>();
            Label label = new Label("Siseta oma nimi: ");
            text = new TextField();
            GridPane grid = new GridPane();
            grid.add(label, 1, 1);
            grid.add(text, 2, 1);
            dialog.getDialogPane().setContent(grid);
            ButtonType button = new ButtonType("OK");

            dialog.getDialogPane().getButtonTypes().add(button);
            dialog.show();
            return name = text.getText();

        }

        public Results(String name, int score) {
            this.score = score;
            this.name = name;
        }
    }

}

