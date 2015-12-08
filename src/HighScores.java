import javafx.scene.control.TextInputDialog;
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
    public void checkScore(int score) {
        if (scores != null && !scores.isEmpty()) {
            int lowestHighScore = scores.size() - 1; //vale praegu
        }


    }

    public HighScores() {
        scores = new  ArrayList<Results>();
    }

    public ArrayList<Results> getScores() {
        loadFile();
        sortScores();
        return scores;
    }

    private void sortScores() {
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
    //faili sisu tekstiks muutmine, et seda edetabelina n‰idata
    public String scoresToText() {

        String textToDisplay = "";
        ArrayList<Results> scores;
        scores = getScores();

        for (int i = 0; i < scores.size(); i++) {
            textToDisplay += (i + 1) + ". " + scores.get(i).getName() + " " + scores.get(i).getScore() + "\n";
        }

        return textToDisplay;
    }

}