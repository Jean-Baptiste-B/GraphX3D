package com.yahiab.graphix.model._3d.importers;

import javafx.animation.Timeline;
import javafx.scene.Group;

import java.io.IOException;

public abstract class Importer {
    /**
     * Loads the 3D file
     * 
     * @param url The url of the 3D file to load
     * @param asPolygonMesh When true load as a PolygonMesh if the loader 
     * supports. 
     * @throws IOException If issue loading file
     */
    public abstract void load(String url, boolean asPolygonMesh) throws IOException; 
    /**
     * Gets the 3D node that was loaded earlier through the load() call
     * @return The loaded node
     */
    public abstract Group getRoot();
    /**
     * Tests if the given 3D file extension is supported (e.g. "ma", "ase", 
     * "obj", "fxml", "dae"). 
     * 
     * @param supportType The file extension (e.g. "ma", "ase", "obj", "fxml", 
     * "dae")
     * @return True if the extension is of a supported type. False otherwise.
     */
    public abstract boolean isSupported(String supportType);
    /**
     * Can be overridden to return a timeline animation for the 3D file
     * @return A timeline animation. Null if there is no timeline animation.
     */
    public Timeline getTimeline() {
        return null;
    }
}
