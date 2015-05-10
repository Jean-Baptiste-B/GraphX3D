/**
 * Copyright (c) 2015 Jens Deters http://www.jensd.de
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.yahiab.graphix.view.glyphs;

import com.sun.javafx.css.ParsedValueImpl;
import com.sun.javafx.css.parser.CSSParser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.*;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A GlyphIcon represents an Icon Node.
 *
 * @author Jens Deters
 * @param <T> The type of GlyphIcons enum.
 */
public abstract class GlyphIcon<T extends Enum<T>> extends Text {

    public final static Double DEFAULT_ICON_SIZE = 12.0;
    public final static String DEFAULT_FONT_SIZE = "1em";
    private static final CSSParser CSS_PARSER = new CSSParser();
    public final Class<T> typeOfT;
    private StringProperty glyphStyle; // needed as setStyle() is final in javafx.scene.text.Text
    private ObjectProperty<String> glyphName;
    private ObjectProperty<Number> glyphSize;

    public GlyphIcon() {
        this.typeOfT = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        getStyleClass().addAll("root", "glyph-icon");
        glyphSizeProperty().addListener(observable -> {
            updateSize();
        });
        glyphStyleProperty().addListener(observable -> {
            updateStyle();
        });
        glyphNameProperty().addListener(observable -> {
            updateIcon();
        });
        setIcon(getDefaultGlyph());

    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @FXML
    public void init() {
    }

    // convenience method
    public final GlyphIcon setStyleClass(String styleClass) {
        getStyleClass().add(styleClass);
        return this;
    }

    public final StringProperty glyphStyleProperty() {
        if (glyphStyle == null) {
            glyphStyle = new SimpleStringProperty("");
        }
        return glyphStyle;
    }

    public final String getGlyphStyle() {
        return glyphStyleProperty().getValue();
    }

    public final void setGlyphStyle(String style) {
        glyphStyleProperty().setValue(style);
    }

    public final ObjectProperty<String> glyphNameProperty() {
        if (glyphName == null) {
            glyphName = new SimpleStyleableObjectProperty<>(StyleableProperties.GLYPH_NAME, GlyphIcon.this, "glyphName");
        }
        return glyphName;
    }

    public final String getGlyphName() {
        return glyphNameProperty().getValue();
    }

    public final void setGlyphName(String glyphName) {
        glyphNameProperty().setValue(glyphName);
    }

    public final ObjectProperty<Number> glyphSizeProperty() {
        if (glyphSize == null) {
            glyphSize = new SimpleStyleableObjectProperty<>(StyleableProperties.GLYPH_SIZE, GlyphIcon.this, "glyphSize");
        }
        return glyphSize;
    }

    public final Number getGlyphSize() {
        return glyphSizeProperty().getValue();
    }

    public final void setGlyphSize(Number size) {
        size = (size == null) ? DEFAULT_ICON_SIZE : size;
        glyphSizeProperty().setValue(size);
    }

    // kept for compability reasons and for SceneBuilder/FXML support
    public final String getSize() {
        return getGlyphSize().toString();
    }

    // kept for compability reasons and for SceneBuilder/FXML support
    public final void setSize(String sizeExpr) {
        Number s = convert(sizeExpr);
        setGlyphSize(s);
    }

    public final void setIcon(T glyph) {
        setGlyphName(glyph.name());
    }

    abstract public T getDefaultGlyph();

    private void updateSize() {
        Font f = new Font(getFont().getFamily(), getGlyphSize().doubleValue());
        setFont(f);
    }

    private void updateIcon() {
        GlyphIcons icon = (GlyphIcons) getDefaultGlyph();
        try {
            icon = ((GlyphIcons) Enum.valueOf(typeOfT, getGlyphName()));
        } catch (Exception e) {
            String msg = String.format("Icon '%s' not found. Using '%s' (default) instead", getGlyphName(), getDefaultGlyph());
            Logger.getLogger(GlyphIcon.class.getName()).log(Level.SEVERE, msg);
        }
        setText(icon.characterToString());
    }

    private void updateStyle() {
        setStyle(getGlyphStyle());
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    public Number convert(String sizeString) {
        ParsedValueImpl parsedValueImpl = CSS_PARSER.parseExpr("", sizeString);
        return (Number) parsedValueImpl.convert(getFont());
    }

    // CSS
    private static class StyleableProperties {

        private static final CssMetaData<GlyphIcon, String> GLYPH_NAME
                = new CssMetaData<GlyphIcon, String>("-glyph-name", StyleConverter.getStringConverter(), "BLANK") {

            @Override
            public boolean isSettable(GlyphIcon styleable) {
                return styleable.glyphName == null || !styleable.glyphName.isBound();
            }

            @Override
            public StyleableProperty<String> getStyleableProperty(GlyphIcon styleable) {
                return (StyleableProperty) styleable.glyphNameProperty();
            }

            @Override
            public String getInitialValue(GlyphIcon styleable) {
                return "BLANK";
            }
        };

        private static final CssMetaData<GlyphIcon, Number> GLYPH_SIZE
                = new CssMetaData<GlyphIcon, Number>("-glyph-size", StyleConverter.getSizeConverter(), DEFAULT_ICON_SIZE) {
            @Override
            public boolean isSettable(GlyphIcon styleable) {
                return styleable.glyphSize == null || !styleable.glyphSize.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(GlyphIcon styleable) {
                return (StyleableProperty) styleable.glyphSizeProperty();
            }

            @Override
            public Number getInitialValue(GlyphIcon styleable) {
                return DEFAULT_ICON_SIZE;
            }
        };
        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Text.getClassCssMetaData());
            Collections.addAll(styleables, GLYPH_NAME, GLYPH_SIZE);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
}
