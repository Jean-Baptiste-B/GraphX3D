/**
 * Copyright (c) 2013-2015 Jens Deters http://www.jensd.de
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.yahiab.graphix.view.glyphs;

import javafx.scene.layout.StackPane;

/**
 *
 * @author Jens Deters
 */
public class GlyphsStack extends StackPane {

    public static GlyphsStack create() {
        return new GlyphsStack();
    }


    public GlyphsStack add(GlyphIcon icon) {
        getChildren().add(icon);
        return this;
    }

    public GlyphsStack remove(GlyphIcon icon) {
        getChildren().remove(icon);
        return this;
    }

}
