package ru.traphouse;

import ru.traphouse.model.controller.SceneController;

public class Main {

    public static void main(String[] args) {
        SceneController scene = SceneController.defaultScene();
        scene.playTextEpisode();

        System.out.println("Arthur state: " + scene.getArthur().getState());
        System.out.println("Jaw state: " + scene.getArthur().getJawState());
        System.out.println("Unbelievable things seen: " + scene.getArthur().getUnbelievableThingsSeen());
        System.out.println("Left head: " + scene.getTwoHeadedPerson().getLeftHead().getState());
        System.out.println("Right head: " + scene.getTwoHeadedPerson().getRightHead().getState());
    }
}
