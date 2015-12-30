package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.gms.AlertBox;

/**
 * Created by martin on 13.12.15.
 */
public class SettingsView extends Pane {
    public RadioButton user1Image1Button = new RadioButton("");
    public RadioButton user1Image2Button = new RadioButton("");
    public RadioButton user1Image3Button = new RadioButton("");
    public RadioButton user1Image4Button = new RadioButton("");
    public RadioButton user2Image1Button = new RadioButton("");
    public RadioButton user2Image2Button = new RadioButton("");
    public RadioButton user2Image3Button = new RadioButton("");
    public RadioButton user2Image4Button = new RadioButton("");
    public ToggleGroup user1SelectionChoicesToggleGroup = new ToggleGroup();
    public ToggleGroup user2SelectionChoicesToggleGroup = new ToggleGroup();
    public Button confirmSelectedUsersButton = new Button("KINNITA TEGELASED");

    public SettingsView() {
        this.setHeight(600);
        this.setWidth(800);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

        //GridPane nuppude jaoks
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        class MyImageView extends ImageView {
            public MyImageView (String fileName) {
                Image image = new Image(fileName);
                this.setImage(image);
                this.setScaleY(0.3);
                this.setScaleX(0.3);
                this.autosize();
                this.setFitHeight(100);
            }
        }



        //---mängija1 tegelased
        Label user1Label = new Label("MÄNGIJA 1");
        user1Label.setContentDisplay(ContentDisplay.TOP);
        user1Label.setPadding(new Insets(5, 5, 5, 250));
        user1Label.setFont(theFontSmall);

        user1Image1Button.setGraphic(new MyImageView("images/kasutaja01.png"));
        user1Image1Button.setSelected(true);
        user1Image1Button.setPadding(new Insets(5, 5, 5, 200));
        user1Image1Button.setId("1");

        user1Image2Button.setGraphic(new MyImageView("images/kasutaja02.png"));
        user1Image2Button.setPadding(new Insets(5, 5, 5, 200));
        user1Image2Button.setId("2");

        user1Image3Button.setGraphic(new MyImageView("images/kasutaja03.png"));
        user1Image3Button.setPadding(new Insets(5, 5, 5, 200));
        user1Image3Button.setId("3");

        user1Image4Button.setGraphic(new MyImageView("images/kasutaja04.png"));
        user1Image4Button.setPadding(new Insets(5, 5, 5, 200));
        user1Image4Button.setId("4");

        user1Image1Button.setToggleGroup(user1SelectionChoicesToggleGroup);
        user1Image2Button.setToggleGroup(user1SelectionChoicesToggleGroup);
        user1Image3Button.setToggleGroup(user1SelectionChoicesToggleGroup);
        user1Image4Button.setToggleGroup(user1SelectionChoicesToggleGroup);

        VBox paneForRadioButtons1 = new VBox(20);
        paneForRadioButtons1.setPadding(new Insets(5, 5, 5, 5));
        gridPane.add(user1Label, 2, 1);
        gridPane.add(user1Image1Button, 2, 2);
        gridPane.add(user1Image2Button, 2, 3);
        gridPane.add(user1Image3Button, 2, 4);
        gridPane.add(user1Image4Button, 2, 5);

        //---mängija2 tegelased
        Label user2Label = new Label("MÄNGIJA 2");
        user2Label.setContentDisplay(ContentDisplay.TOP);
        user2Label.setPadding(new Insets(5, 5, 5, 100));
        user2Label.setFont(theFontSmall);

        user2Image1Button.setGraphic(new MyImageView("images/kasutaja01.png"));
        user2Image1Button.setSelected(true);
        user2Image1Button.setId("1");
        user2Image1Button.setPadding(new Insets(5, 5, 5, 70));

        user2Image2Button.setGraphic(new MyImageView("images/kasutaja02.png"));
        user2Image2Button.setPadding(new Insets(5, 5, 5, 70));
        user2Image2Button.setId("2");

        user2Image3Button.setGraphic(new MyImageView("images/kasutaja03.png"));
        user2Image3Button.setPadding(new Insets(5, 5, 5, 70));
        user2Image3Button.setId("3");

        user2Image4Button.setGraphic(new MyImageView("images/kasutaja04.png"));
        user2Image4Button.setPadding(new Insets(5, 5, 5, 70));
        user2Image4Button.setId("4");


        VBox paneForRadioButtons2 = new VBox(20);
        paneForRadioButtons2.setPadding(new Insets(5, 5, 5, 5));
        gridPane.add(user2Label, 3, 1);
        gridPane.add(user2Image1Button, 3, 2);
        gridPane.add(user2Image2Button, 3, 3);
        gridPane.add(user2Image3Button, 3, 4);
        gridPane.add(user2Image4Button, 3, 5);

        user2Image1Button.setToggleGroup(user2SelectionChoicesToggleGroup);
        user2Image2Button.setToggleGroup(user2SelectionChoicesToggleGroup);
        user2Image3Button.setToggleGroup(user2SelectionChoicesToggleGroup);
        user2Image4Button.setToggleGroup(user2SelectionChoicesToggleGroup);

        //Tekita valikute kontroll. Kui on samad pildid valitud, siis viskab vea. Kui ei ole, siis ei viska.
        //AlertBox selleks, kui kasutajad valivad samad tegelased. EI TÖÖTA!
        confirmSelectedUsersButton.setFont(theFontSmall);
        gridPane.add(confirmSelectedUsersButton, 3, 7);
            confirmSelectedUsersButton.setOnAction(e -> {
                if ((user1SelectionChoicesToggleGroup.getSelectedToggle().toString()).equals(user2SelectionChoicesToggleGroup.getSelectedToggle().toString())) {
                    System.out.println(user1SelectionChoicesToggleGroup.getSelectedToggle());
                    System.out.println(user2SelectionChoicesToggleGroup.getSelectedToggle());
                    AlertBox.display("VIGA", "PALUN VALIGE ERINEVAD TEGELASED!");
                }
            });
        this.getChildren().addAll(gridPane);
    }
}








