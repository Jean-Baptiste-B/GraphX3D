/**
 * Copyright (c) 2013-2015 Jens Deters http://www.jensd.de
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.yahiab.graphix.view.glyphs.weathericons;

import com.yahiab.graphix.view.glyphs.GlyphIcon;
import javafx.scene.text.Font;

/**
 *
 * @author Jens Deters (mail@jensd.de)
 */
public class WeatherIcon extends GlyphIcon<WeatherIcons> {

    public final static String TTF_PATH = "/de/jensd/fx/glyphs/weathericons/weathericons-regular-webfont.ttf";
    public final static String FONT_FAMILY = "\'weather icons\'";

    static {
        Font.loadFont(WeatherIcon.class.getResource(TTF_PATH).toExternalForm(), 10.0);
    }

    public WeatherIcon() {
        setFont(new Font("weather icons", DEFAULT_ICON_SIZE));
    }

    @Override
    public WeatherIcons getDefaultGlyph() {
        return WeatherIcons.ALIEN;
    }

}
