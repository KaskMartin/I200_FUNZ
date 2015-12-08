import javafx.scene.control.TextInputDialog;

public class Results {
    private int score;
    private String name;

    public int getScore() {
        //get game score
        return score;
    }



    public String getName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Palun sisesta oma nimi");
        String name = String.valueOf(dialog.showAndWait());
        return name;
    }

    public Results(String name, int score) {
        this.score = score;
        this.name = name;
    }
}
