package com.yahiab.graphix.controller._3d.controls.numberspinner;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.util.Duration;

/**
 * Created by Yahia on 29/03/2015.
 */
public class ClickRepeater {

    /**
     * This is the initial pause until the button is fired for the first time. This is 500 ms as it the same value used by key events.
     */
    private final PauseTransition initialPause = new PauseTransition(Duration.millis(500));

    /**
     * This is for all the following intervals, after the first one. 80 ms is also used by key events.
     */
    private final PauseTransition pauseTransition = new PauseTransition();

    /**
     * This transition combines the first two.
     */
    private final SequentialTransition sequentialTransition = new SequentialTransition(initialPause, pauseTransition);

    /**
     * Store the change listener, so that it can be removed in the {@link #uninstall(javafx.scene.control.ButtonBase)} method.
     */
    private final ChangeListener<Boolean> changeListener;

    /**
     * Private constructor.
     *
     * @param buttonBase The button.
     */
    private ClickRepeater(final ButtonBase buttonBase, final Duration interval) {
        initialPause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Fire the button the first time after the initial pause.
                buttonBase.fire();
            }
        });

        pauseTransition.setDuration(interval);
        pauseTransition.setCycleCount(Animation.INDEFINITE);
        pauseTransition.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration duration2) {
                // Every time a new cycle starts, fire the button.
                if (duration.greaterThan(duration2)) {
                    buttonBase.fire();
                }
            }
        });
        changeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (aBoolean2) {
                    // If the button gets armed, start the animation.
                    sequentialTransition.playFromStart();
                } else {
                    // Stop the animation, if the button is no longer armed.
                    sequentialTransition.stop();
                }
            }
        };
        buttonBase.armedProperty().addListener(changeListener);
    }

    /**
     * I
     * nstalls the click repeating behavior for a {@link ButtonBase}.
     * The default click interval is 80ms.
     *
     * @param buttonBase The button.
     */
    public static void install(ButtonBase buttonBase) {
        install(buttonBase, Duration.millis(80));
    }

    /**
     * I
     * nstalls the click repeating behavior for a {@link ButtonBase} and also allows to set a click interval.
     *
     * @param buttonBase The button.
     * @param interval   The click interval.
     */
    public static void install(ButtonBase buttonBase, Duration interval) {
        // Uninstall any previous behavior.
        uninstall(buttonBase);

        // Initializes a new ClickRepeater
        if (!buttonBase.getProperties().containsKey(ClickRepeater.class)) {
            // Store the ClickRepeater in the button's properties.
            // If the button will get GCed, so will its ClickRepeater.
            buttonBase.getProperties().put(ClickRepeater.class, new ClickRepeater(buttonBase, interval));
        }
    }

    /**
     * U
     * ninstalls the click repeater behavior from a button.
     *
     * @param buttonBase The button.
     */
    public static void uninstall(ButtonBase buttonBase) {
        if (buttonBase.getProperties().containsKey(ClickRepeater.class) && buttonBase.getProperties().get(ClickRepeater.class) instanceof ClickRepeater) {
            ClickRepeater clickRepeater = (ClickRepeater) buttonBase.getProperties().remove(ClickRepeater.class);
            buttonBase.armedProperty().removeListener(clickRepeater.changeListener);
        }
    }
}