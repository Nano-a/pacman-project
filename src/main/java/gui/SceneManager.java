package gui;

import enums.FxmlFiles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class SceneManager {
    private FxmlFiles current;
    private Stage rootStage = null;
    private Scene[] scenes;
    private CommonController[] controller;
    private boolean mainMenuCalled;

    /**
     * Constructs a new scene manager for a stage and loads all FXML files.
     * @param root The stage to use the manager.
     */
    public SceneManager(Stage root) {

        this.rootStage = root;
        try {
            this.scenes = new Scene[FxmlFiles.values().length];
            this.controller = new CommonController[FxmlFiles.values().length];
            this.mainMenuCalled = false;
            for(FxmlFiles file : FxmlFiles.values()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + file.getFxmlLocation()));
                scenes[file.ordinal()] = new Scene(loader.load());
                scenes[file.ordinal()].getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
                controller[file.ordinal()] = loader.getController();
                controller[file.ordinal()].setSceneManager(this);

            }
            internalInitialization();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void callInitialScene(FxmlFiles file) {
        switchScene(file);
        current = file;
        rootStage.setResizable(false);
        rootStage.sizeToScene();
        rootStage.show();
    }

    public void switchScene(FxmlFiles file) {
        rootStage.setScene(scenes[file.ordinal()]);
        current = file;
    }

    public Scene getScene(FxmlFiles file) {
        return scenes[file.ordinal()];
    }

    public CommonController getController(FxmlFiles file) {
        return controller[file.ordinal()];
    }

    private void internalInitialization() {}

}