package gui;

public class CommonController {
    protected SceneManager manager;


    public CommonController getController() {
        return this;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.manager = sceneManager;
    }

}