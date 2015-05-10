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
package com.yahiab.graphix.view.glyphs.fontawesome;

import com.yahiab.graphix.view.glyphs.GlyphIcon;
import javafx.scene.text.Font;

/**
 *
 * @author Jens Deters
 */
public class FontAwesomeIcon extends GlyphIcon<FontAwesomeIcons> {

    public final static String TTF_PATH = "/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf";

    static {
        Font.loadFont(FontAwesomeIcon.class.getResource(TTF_PATH).toExternalForm(), 10.0);
    }

    public FontAwesomeIcon() {
        setFont(new Font("FontAwesome", DEFAULT_ICON_SIZE));
    }

    @Override
    public FontAwesomeIcons getDefaultGlyph() {
        return FontAwesomeIcons.ANCHOR;
    }

}
