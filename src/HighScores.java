import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class HighScores {
    private ArrayList<Results> scores;
    private static final String fileName = "scores.dat"; //Faili nimi
    public int maxScores = 10; //n2itab 10 tulemust
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;


    //Peaks kontrollima, et m‰nguskoor on piisavalt suur, et edetabelisse p‰‰seda
    public boolean checkScore(int score) {
        /*if (scores != null && !scores.isEmpty()) {
            int lowestHighScore = scores.size() - 1; //vale praegu
        }*/
        int lowestHighScore = scores.size() - 1;
        if (score < lowestHighScore)
            return false;
        return true;
    }

    public HighScores() {
        scores = new  ArrayList<Results>();
    }

    public ArrayList<Results> getHighScores() {
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Error: scores.dat file not found!");
        } catch (IOException e) {
            System.out.println("Error: scores.dat file not found!");
        }
    }

    public void updateFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("Error: scores.dat file not found!");
        } catch (IOException e) {
            System.out.println("Error: scores.dat file not found!");
        }
    }

    public String scoresToText() {
        String textToDisplay = "";
        int max = 10;
        ArrayList<Results> scores;
        scores = getHighScores();
        int i = 0;
        int x = scores.size();
        if (x > max) {
            x= max;
        }
        while (i < x) {
            textToDisplay += (i + 1) + ".\t" + scores.get(i).getName() + " " + scores.get(i).getScore() + "\n";
            i++;
        }
        return textToDisplay;
    }



}