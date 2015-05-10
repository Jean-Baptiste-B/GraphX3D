package com.yahiab.graphix.controller._3d.controls.numberspinner;

/**
 * Created by Yahia on 29/03/2015.
 */

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * The default skin for the NumberSpinnerLight control.
 *
 * @author Christian Schudt
 */
public class NumberSpinnerLightSkin extends StackPane implements Skin<NumberSpinnerLight> {

    private static final String TOP_LEFT = "top-left";

    private static final String BOTTOM_LEFT = "bottom-left";

    private static final String LEFT = "left";

    private static final String RIGHT = "right";

    private static final String BOTTOM_RIGHT = "bottom-right";

    private static final String TOP_RIGHT = "top-right";

    private static final String CENTER = "center";

    private final String[] cssClasses = {TOP_LEFT, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};

    private final TextField textField;

    private final NumberSpinnerLight numberSpinnerLight;

    private final ChangeListener<IndexRange> changeListenerSelection;

    private final ChangeListener<Number> changeListenerCaretPosition;

    private final ChangeListener<Number> changeListenerValue;

    private final ChangeListener<HPos> changeListenerHAlignment;

    private final Button btnIncrement;

    private final Button btnDecrement;

    private final Region arrowIncrement;

    private final Region arrowDecrement;

    /**
     * @param numberSpinnerLight The control.
     */
    public NumberSpinnerLightSkin(final NumberSpinnerLight numberSpinnerLight) {

        this.numberSpinnerLight = numberSpinnerLight;

        minHeightProperty().bind(numberSpinnerLight.minHeightProperty());

        // The TextField
        textField = new TextField();
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean1) {
                if (textField.isEditable() && aBoolean1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.selectAll();
                        }
                    });
                }

                // setStyle explicitly is a workaround for this JavaFX 2.2 bug:
                // https://javafx-jira.kenai.com/browse/RT-23085
                String javafxVersion = System.getProperty("javafx.runtime.version");
                if (textField.isFocused()) {
                    getStyleClass().add("number-spinner-light-focused");
                    if (javafxVersion.startsWith("2.2")) {
                        setStyle("-fx-background-color: -fx-focus-color, -fx-text-box-border, -fx-control-inner-background;\n" +
                                "    -fx-background-insets: -0.4, 1, 2;\n" +
                                "    -fx-background-radius: 3.4, 2, 2");
                    }
                } else {
                    getStyleClass().remove("number-spinner-light-focused");
                    if (javafxVersion.startsWith("2.2")) {
                        setStyle("-fx-background-color: null;\n" +
                                "    -fx-background-insets: null;\n" +
                                "    -fx-background-radius: null");
                    }
                    parseText();
                    setText();
                }
            }
        });

        // Mimic bidirectional binding: Whenever the selection changes of either the control or the text field, propagate it to the other.
        // This ensures that the selectionProperty of both are in sync.
        changeListenerSelection = new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observableValue, IndexRange indexRange, IndexRange indexRange2) {
                textField.selectRange(indexRange2.getStart(), indexRange2.getEnd());
            }
        };
        numberSpinnerLight.selectionProperty().addListener(changeListenerSelection);

        textField.selectionProperty().addListener(new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observableValue, IndexRange indexRange, IndexRange indexRange1) {
                numberSpinnerLight.selectRange(indexRange1.getStart(), indexRange1.getEnd());
            }
        });

        // Mimic bidirectional binding: Whenever the caret position changes in either the control or the text field, propagate it to the other.
        // This ensures that both caretPositions are in sync.
        changeListenerCaretPosition = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number1) {
                textField.positionCaret(number1.intValue());
            }
        };
        numberSpinnerLight.caretPositionProperty().addListener(changeListenerCaretPosition);

        textField.caretPositionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number1) {
                numberSpinnerLight.positionCaret(number1.intValue());
            }
        });

        // Bind the control's properties to the text field.
        textField.minHeightProperty().bind(numberSpinnerLight.minHeightProperty());
        textField.maxHeightProperty().bind(numberSpinnerLight.maxHeightProperty());
        textField.textProperty().bindBidirectional(numberSpinnerLight.textProperty());
        textField.alignmentProperty().bind(numberSpinnerLight.alignmentProperty());
        textField.editableProperty().bind(numberSpinnerLight.editableProperty());
        textField.prefColumnCountProperty().bind(numberSpinnerLight.prefColumnCountProperty());
        textField.promptTextProperty().bind(numberSpinnerLight.promptTextProperty());
        textField.onActionProperty().bind(numberSpinnerLight.onActionProperty());
        textField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!keyEvent.isConsumed()) {
                    if (keyEvent.getCode().equals(KeyCode.UP)) {
                        btnIncrement.fire();
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode().equals(KeyCode.DOWN)) {
                        btnDecrement.fire();
                        keyEvent.consume();
                    }
                }
            }
        });
        setText();

        changeListenerValue = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                setText();
            }
        };
        numberSpinnerLight.valueProperty().addListener(changeListenerValue);
        changeListenerHAlignment = new ChangeListener<HPos>() {
            @Override
            public void changed(ObservableValue<? extends HPos> observableValue, HPos hPos, HPos hPos1) {
                align(numberSpinnerLight.getHAlignment());
            }
        };
        numberSpinnerLight.hAlignmentProperty().addListener(changeListenerHAlignment);


        // The increment button.
        btnIncrement = new Button();
        btnIncrement.setFocusTraversable(false);
        btnIncrement.disableProperty().bind(new BooleanBinding() {
            {
                super.bind(numberSpinnerLight.valueProperty(), numberSpinnerLight.maxValueProperty());
            }

            @Override
            protected boolean computeValue() {

                return numberSpinnerLight.valueProperty().get() != null &&
                        numberSpinnerLight.maxValueProperty().get() != null &&
                        numberSpinnerLight.valueProperty().get().doubleValue() >=
                                numberSpinnerLight.maxValueProperty().get().doubleValue();
            }
        });
        btnIncrement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parseText();
                numberSpinnerLight.increment();
            }
        });
        arrowIncrement = createArrow();
        btnIncrement.setGraphic(arrowIncrement);

        btnIncrement.setMinHeight(0);
        ClickRepeater.install(btnIncrement, Duration.millis(8));


        // The decrement button
        btnDecrement = new Button();
        btnDecrement.setFocusTraversable(false);
        btnDecrement.disableProperty().bind(new BooleanBinding() {
            {
                super.bind(numberSpinnerLight.valueProperty(), numberSpinnerLight.minValueProperty());
            }

            @Override
            protected boolean computeValue() {
                return numberSpinnerLight.valueProperty().get() != null &&
                        numberSpinnerLight.minValueProperty().get() != null &&
                        numberSpinnerLight.valueProperty().get().doubleValue() <=
                                numberSpinnerLight.minValueProperty().get().doubleValue();
            }
        });
        btnDecrement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parseText();
                numberSpinnerLight.decrement();
            }
        });
        arrowDecrement = createArrow();
        btnDecrement.setGraphic(arrowDecrement);
        btnDecrement.setMinHeight(0);
        ClickRepeater.install(btnDecrement, Duration.millis(8));

        // Allow the buttons to grow vertically.
        VBox.setVgrow(btnIncrement, Priority.ALWAYS);
        VBox.setVgrow(btnDecrement, Priority.ALWAYS);

        // Allow the text field to allow horizontally.
        HBox.setHgrow(textField, Priority.ALWAYS);
        align(numberSpinnerLight.getHAlignment());
    }

    /**
     * Creates an arrow for the buttons.
     *
     * @return The arrow.
     */
    private Region createArrow() {
        Region arrow = new Region();
        arrow.setMaxSize(8, 8);
        arrow.getStyleClass().add("arrow");
        return arrow;
    }

    /**
     * Aligns the text field relative to the buttons.
     *
     * @param hPos The horizontal position of the text field.
     */
    private void align(HPos hPos) {
        getChildren().clear();
        clearStyles();
        btnIncrement.maxHeightProperty().unbind();
        btnDecrement.maxHeightProperty().unbind();
        switch (hPos) {
            case LEFT:
            case RIGHT:
                alignLeftOrRight(hPos);
                break;
            case CENTER:
                alignCenter();
                break;
        }
    }

    /**
     * Aligns the text field in between both buttons.
     */
    private void alignCenter() {
        btnIncrement.getStyleClass().add(RIGHT);
        btnDecrement.getStyleClass().add(LEFT);
        textField.getStyleClass().add(CENTER);

        btnIncrement.maxHeightProperty().setValue(Double.MAX_VALUE);
        btnDecrement.maxHeightProperty().setValue(Double.MAX_VALUE);

        arrowIncrement.setRotate(-90);
        arrowDecrement.setRotate(90);

        getChildren().add(HBoxBuilder.create().children(btnDecrement, textField, btnIncrement).build());
    }

    /**
     * Aligns the buttons either left or right.
     *
     * @param hPos The HPos, either {@link HPos#LEFT} or {@link HPos#RIGHT}.
     */
    private void alignLeftOrRight(HPos hPos) {
        // The box which aligns the two buttons vertically.
        final VBox buttonBox = new VBox();
        HBox hBox = new HBox();
        switch (hPos) {
            case RIGHT:
                btnIncrement.getStyleClass().add(TOP_LEFT);
                btnDecrement.getStyleClass().add(BOTTOM_LEFT);
                textField.getStyleClass().add(RIGHT);
                hBox.getChildren().addAll(buttonBox, textField);
                break;
            case LEFT:
                btnIncrement.getStyleClass().add(TOP_RIGHT);
                btnDecrement.getStyleClass().add(BOTTOM_RIGHT);
                textField.getStyleClass().add(LEFT);
                hBox.getChildren().addAll(textField, buttonBox);
                break;
            case CENTER:
                break;
        }

        btnIncrement.maxHeightProperty().bind(textField.heightProperty().divide(2.0));
        // Subtract 0.5 to ensure it looks fine if height is odd.
        btnDecrement.maxHeightProperty().bind(textField.heightProperty().divide(2.0).subtract(0.5));
        arrowIncrement.setRotate(180);
        arrowDecrement.setRotate(0);

        buttonBox.getChildren().addAll(btnIncrement, btnDecrement);
        getChildren().add(hBox);
    }

    /**
     * Clears all styles on all controls.
     */
    private void clearStyles() {
        btnIncrement.getStyleClass().removeAll(cssClasses);
        btnDecrement.getStyleClass().removeAll(cssClasses);
        textField.getStyleClass().removeAll(cssClasses);
    }

    /**
     * Parses the text and sets the {@linkplain NumberSpinnerLight#valueProperty() value} accordingly.
     * If parsing fails, the value is set to null.
     */
    private void parseText() {
        if (textField.getText() != null) {
            try {
                numberSpinnerLight.setValue(numberSpinnerLight.getNumberStringConverter().fromString(textField.getText()).doubleValue());
            } catch (Exception e) {
                numberSpinnerLight.setValue(null);
            }

        } else {
            numberSpinnerLight.setValue(null);
        }
    }

    /**
     * Sets the formatted value to the text field.
     */
    private void setText() {
        if (numberSpinnerLight.getValue() != null && !Double.isInfinite((numberSpinnerLight.getValue().doubleValue())) && !Double.isNaN(numberSpinnerLight.getValue().doubleValue())) {
            textField.setText(numberSpinnerLight.getNumberStringConverter().toString(numberSpinnerLight.getValue()));
        } else {
            textField.setText(null);
        }
    }

    @Override
    public NumberSpinnerLight getSkinnable() {
        return numberSpinnerLight;
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void dispose() {

        // Unbind everything and remove listeners, in order to avoid memory leaks.
        minHeightProperty().unbind();

        textField.minHeightProperty().unbind();
        textField.maxHeightProperty().unbind();
        textField.textProperty().unbindBidirectional(numberSpinnerLight.textProperty());
        textField.alignmentProperty().unbind();
        textField.editableProperty().unbind();
        textField.prefColumnCountProperty().unbind();
        textField.promptTextProperty().unbind();
        textField.onActionProperty().unbind();

        numberSpinnerLight.selectionProperty().removeListener(changeListenerSelection);
        numberSpinnerLight.caretPositionProperty().removeListener(changeListenerCaretPosition);
        numberSpinnerLight.valueProperty().removeListener(changeListenerValue);
        numberSpinnerLight.hAlignmentProperty().removeListener(changeListenerHAlignment);
        btnIncrement.disableProperty().unbind();
        btnDecrement.disableProperty().unbind();

    }
}
