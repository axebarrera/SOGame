package com.example.sogame;

import com.example.sogame.characters.*;
import com.example.sogame.mechanism.Fighter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

//Stage is 1600 x 900
public class HelloApplication extends Application {
    Fighter[] fighters = new Fighter[4];
    int[] turnOrder = {0,1,2,3};

    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("stage-fight-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        //Grabbing controller to modify
        StageFightController controller = fxmlLoader.getController();
        controller.setApplication(this);
        //Decide Turn Order
        //randomizeTurns(turnOrder);
        System.out.println(Arrays.toString(turnOrder));
        //Create Fighters, This is temporary for testing reasons
        fighters[1] = new Jomedy();
        fighters[3] = new Pr0();
        fighters[2] = new Ritalin();
        fighters[0] = new Dyspo();

        //Set the Window
        Scene scene = new Scene(root, 1600, 900);
        stage.setTitle("Shattered Oath");
        stage.setScene(scene);
        stage.show();
        controller.startGame();
    }

    public static void main(String[] args) {
        launch();
    }

    public void switchScenery(String fxmlFile) {
        //Loading FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Parent root = (Parent) loader.load();
            //Send this class (HelloApplication) to next controller for access to this function
            Object controller = loader.getController();
            if (controller instanceof SceneSwitcher) {
                ((SceneSwitcher) controller).setApplication(this);
            }

            this.stage.setScene(new Scene(root));


        }catch (IOException e){e.printStackTrace();}
    }

    public void randomizeTurns(int[] array){
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}