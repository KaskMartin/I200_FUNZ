
import javafx.scene.control.TextInputDialog;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;


public class HighScores {
    private static final String fileName = "scores.dat"; //Faili nimi
    public int maxScores = 10; //n2itab 10 tulemust

    private ArrayList<HighScores> scores;
    public String name;
    public int score;
    public HighScores(int score, String name) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
    return score + " " + name;
    }

    //Faili laadimine
    private static void loadFile()
    {
        HighScores[] h = {new HighScores(0," "),new HighScores(0," "),new HighScores(0," "),
                new HighScores(0," "),new HighScores(0," "),new HighScores(0," "),
                new HighScores(0," "),new HighScores(0," "),new HighScores(0," "),
                new HighScores(0," ")};
        try
        {
            ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream("scores.dat"));
            object.writeObject(h);
            object.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {
            System.out.println("Error: scores.dat file not found!");}
    }

    //Faili muutmine
    public void changeFile() {
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

    //Sorteerimine
    public void addHighScore(String name, int score) {
        scores.add(new HighScores(score, name));
        int s;
        int i;
        int tempScore;
        String tempName;

        for(s = scores.size() - 1; s >= 0; s --) {
            for(i = 0; i <= s - 1; i++){
                if(scores.get(i).score < scores.get(i + 1).score) {
                    tempScore = scores.get(i).score;
                    scores.get(i).score = scores.get(i + 1).score;
                    scores.get(i + 1).score = tempScore;

                    tempName = scores.get(i).name;
                    scores.get(i).name = scores.get(i + 1).name;
                    scores.get(i+1).name = tempName;
                }
            }
        }
    }
    public boolean checkScore(int score) {
        HighScores lowestHighScore = scores.get(scores.size() - 1);

        return score >= lowestHighScore.score;
    }

    //Uue tulemuse lisamine ja kasutajalt nime k�simine
    public void newHighScore(int currentGamesScore) {
        if (this.checkScore(score)) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setContentText("Palun sisesta oma nimi");
            String name = String.valueOf(dialog.showAndWait());
            /*
            if (user.isPresent()){
                return user;
            } */



            this.addHighScore(name, score);
            changeFile();
        }

    }

    public ArrayList<HighScores> getHighScores() {
        return scores;
    }


}