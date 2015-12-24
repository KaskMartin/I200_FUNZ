package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by martin on 13.12.15.
 */
public class SettingsView extends Pane {

    public SettingsView() {
        this.setHeight(600);
        this.setWidth(800);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

    //public void start(Stage primaryStage) {
        //primaryStage.setTitle("JavaFX Welcome");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Text scenetitle = new Text("VALI MÄNGIJA \n\nMÄNGIJA 1");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(scenetitle, 0, 0);
        Label valimangija1 = new Label("Vali mängija 1");
            //grid.add(valimangija1, 1, 1);
        Label valimangija2 = new Label("Vali mängija 2");
            //grid.add(valimangija1, 3, 1);

        final ToggleGroup valikunupp1 = new ToggleGroup();

        RadioButton var1 = new RadioButton("1");
        var1.setToggleGroup(valikunupp1);
        var1.setUserData("1");
        var1.setFont(theFontSmall);

        RadioButton var2 = new RadioButton("2");
        var2.setToggleGroup(valikunupp1);
        var2.setUserData("2");
        var2.setFont(theFontSmall);

        final ToggleGroup valikunupp2 = new ToggleGroup();
        RadioButton var3 = new RadioButton("3");
        var3.setToggleGroup(valikunupp2);
        var3.setUserData("3");
        var3.setFont(theFontSmall);

        RadioButton var4 = new RadioButton("4");
        var4.setToggleGroup(valikunupp2);
        var4.setUserData("4");
        var4.setFont(theFontSmall);

        Image kasutaja03 = new Image("kasutaja03.png");
                new Image(SettingsView.class.getResourceAsStream("kasutaja03.png")));
        grid.add(kasutaja03, 2, 1);

        //valikunupp.selectedToggleProperty().addListener((observable, oldValue, newValue) ->

                grid.add(var1, 1, 2);
                grid.add(var2, 3, 2);
                grid.add(var3, 1, 3);
                grid.add(var4, 3, 3);


        //var1.setToggleGroup(valikunupp);
        //var2.setToggleGroup(valikunupp);

        //valikunupp.getToggles().addAll(var1, var2);


        //Label userName = new Label("User Name:");
    //grid.add(userName, 0, 1);

    //TextField userTextField = new TextField();
    //grid.add(userTextField, 1, 1);

    //Label pw = new Label("Password:");
    //grid.add(pw, 0, 2);

    //PasswordField pwBox = new PasswordField();
    //grid.add(pwBox, 1, 2);

    //Button btn = new Button("Sign in");
    //HBox hbBtn = new HBox(10);
    //hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    //hbBtn.getChildren().add(btn);
    //grid.add(hbBtn, 1, 4);

    final Text actiontarget = new Text();
    grid.add(actiontarget, 1, 6);
        this.getChildren().addAll(grid);

   // btn.setOnAction(new EventHandler<ActionEvent>() {

        //@Override
        //public void handle(ActionEvent e) {
          //  actiontarget.setFill(Color.FIREBRICK);
            //actiontarget.setText("Sign in button pressed");
      //  }
    //});

    //Scene scene = new Scene(grid, 300, 275);
    //primaryStage.setScene(scene);
    //primaryStage.show();


    /**public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        // House icon in column 1, rows 1-2
        ImageView imageHouse = new ImageView(
                new Image(SettingsView.class.getResourceAsStream("images/kasutaja03.png")));
        grid.add(imageHouse, 0, 0, 1, 2);

        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

        // Chart in columns 2-3, row 3
        ImageView imageChart = new ImageView(
                new Image(SettingsView.class.getResourceAsStream("images/kasutaja03.png")));
        grid.add(imageChart, 1, 2, 2, 1);

        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);

        this.getChildren().addAll(grid);
        return grid;

       /** GridPane settinguGrid = new GridPane();
        Image mangija01= new Image("images/kasutaja03.png");
        ImageView mangija1 = new ImageView("images/kasutaja03.png");
        settinguGrid.add(mangija1, 2, 2);

        this.getChildren().addAll(settinguGrid, mangija1);


        Label settingsInfo1 = new Label("VALI MÄNGIJA \n\nMÄNGIJA 1");
        settingsInfo1.setTranslateX(100);
        settingsInfo1.setTranslateY(60);
        settingsInfo1.setFont(theFontSmall);
        Label settingsInfo2 = new Label("VALI MÄNGIJA \n\nMÄNGIJA 2");
        settingsInfo2.setTranslateX(475);
        settingsInfo2.setTranslateY(60);
        settingsInfo2.setFont(theFontSmall);
        this.getChildren().addAll(settingsInfo1, settingsInfo2);

        final ToggleGroup valikunupp = new ToggleGroup();

        RadioButton var1 = new RadioButton("");
        var1.setToggleGroup(valikunupp);
        var1.setUserData("");
        var1.setFont(theFontSmall);

        RadioButton var2 = new RadioButton("");
        var2.setToggleGroup(valikunupp);
        var2.setUserData("");
        var2.setFont(theFontSmall);

        RadioButton var3 = new RadioButton("");
        var3.setToggleGroup(valikunupp);
        var3.setUserData("");
        var3.setFont(theFontSmall);

        RadioButton var4 = new RadioButton("");
        var4.setToggleGroup(valikunupp);
        var4.setUserData("");
        var4.setFont(theFontSmall);

        valikunupp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {


        });

        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        VBox vbox = new VBox();
        VBox vbox2 = new VBox();

        vbox.getChildren().add(var1);
        vbox.getChildren().add(var2);
        vbox.setSpacing(80);
        vbox.setTranslateX(70);
        vbox.setTranslateY(0);

        vbox2.getChildren().add(var3);
        vbox2.getChildren().add(var4);
        vbox2.setSpacing(80);//valikunuppude omavaheline kaugus
        vbox2.setTranslateX(70);
        vbox2.setTranslateY(50);

        hbox.getChildren().add(vbox);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox.setTranslateX(50);
        hbox.setTranslateY(100);

        hbox2.getChildren().add(vbox2);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox2.setTranslateX(450);
        hbox2.setTranslateY(100);

        this.getChildren().addAll(hbox, hbox2);
        */
    }
}
