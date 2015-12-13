import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScores {
    private ArrayList<Result> scores;
    private static final String fileName = "scores.dat"; //Faili nimi
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScores() {
        scores = new  ArrayList<Result>();
    }

    public ArrayList<Result> getScoresList() {
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
        scores.add(new Result(name, score));
        updateFile();
    }

    public void loadFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            scores = (ArrayList<Result>) inputStream.readObject();
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

        ArrayList<Result> scores;
        scores = getScoresList();
        int scoresToDisplay = scores.size();
        if (scoresToDisplay > 10)
            scoresToDisplay = 10;

        if (scoresToDisplay != 0) {
            for (int i = 0; i < scoresToDisplay; i++) {
                textToDisplay += (i + 1) + ". " + scores.get(i).getNameString() + " " + scores.get(i).getScore() + "\n";
            }
        } else textToDisplay = "Tulemusi ei ole";
        return textToDisplay;

    }

    public class SortScores implements Comparator<Result> {
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
    }

}

