package gui;

/**
 * CommonController
 * @Description: Cette classe est le parent de tous les controlleurs, elle contient l'attribut  SceneManager manager
 */

public class CommonController {
    protected SceneManager manager; // l'attribut manager qui permet de changer de scène
    public CommonController getController() {
        return this;
    }
    public void setSceneManager(SceneManager sceneManager) {
        this.manager = sceneManager;
    }

}