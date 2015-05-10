package com.yahiab.graphix.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Yahia on 04/05/2015.
 */
public class ImageViewLoader {

    public static ImageView cube = new ImageView(new Image("/res/PNGs/cube.png"));
    public static ImageView box = new ImageView(new Image("/res/PNGs/box.png"));

    {
        cube.setFitHeight(100);
        cube.setFitWidth(100);
        box.setFitHeight(100);
        box.setFitWidth(100);
    }

}
