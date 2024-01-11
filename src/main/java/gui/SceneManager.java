package gui;

import enums.FxmlFiles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description : Cette classe est utilisée pour gérer les scènes du jeu et les contrôleurs associés.
 * elle permet d'abord de charger les fichiers FXML et de les stocker dans un tableau de scènes.
 * Elle permet ensuite de changer de scène et de récupérer le contrôleur associé à la scène.
 *
 */
public class SceneManager {
    private FxmlFiles current;
    private Stage mainStage = null; // The stage to use the manager.
    private Scene[] scenes;
    private CommonController[] controller; // les controllers associés aux scènes , on utilisera un casting pour chaque type de controller
    // c'est pour cela que CommonController est utile

    /**
     * @param root : la scène principale du jeu
     */
    public SceneManager(Stage root) {
        this.mainStage = root;
        try {
            this.scenes = new Scene[FxmlFiles.values().length];
            this.controller = new CommonController[FxmlFiles.values().length];
            for(FxmlFiles file : FxmlFiles.values()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + file.getFxmlLocation())); // fichiers fxml sont dans resources pour les trouver facilement en chemin relatif
                // à partir de resources ; on utilise getClass().getResource() pour les trouver en chemin relatif de resources
                scenes[file.ordinal()] = new Scene(loader.load());
                scenes[file.ordinal()].getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
                controller[file.ordinal()] = loader.getController();
                controller[file.ordinal()].setSceneManager(this);
            }
        } catch(IOException e) {
            // si le fichier fxml n'est pas trouvé
            e.printStackTrace();
        }
    }


    public void callInitialScene(FxmlFiles file) { // la première scene appelée dans App.java
        switchScene(file);
        current = file;
        mainStage.setResizable(false);
        mainStage.sizeToScene();
        mainStage.show();
    }

    /**
     * @param file : le fichier fxml à charger et auxquel on bascule
     */
    public void switchScene(FxmlFiles file) {
        mainStage.setScene(scenes[file.ordinal()]);
        current = file;
    }

    public CommonController getController(FxmlFiles file) {
        return controller[file.ordinal()];
    }

    public void switchLanguage(boolean isFrench) {
        // on met à jour la langue de tous les contrôleurs
        for (CommonController controller : this.controller) {
            if(controller instanceof Menu) {
                ((Menu) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if (controller instanceof NewGamePageController) {
                ((NewGamePageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof SettingsController) {
                ((SettingsController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof SettingsLanguageController) {
                ((SettingsLanguageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof SettingsAudioController) {
                ((SettingsAudioController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof StoryPageController) {
                ((StoryPageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof WinPageController) {
                ((WinPageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if(controller instanceof PausePageController) {
                ((PausePageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
            if (controller instanceof LosePageController) {
                ((LosePageController) controller).updateLanguage(SettingsLanguageController.isFrench);
            }
        }
    }


}