/*
 * Copyright (C) 2013-2015 F(X)yz, 
 * Sean Phillips, Jason Pollastrini and Jose Pereda
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fxyz.shapes.primitives.helper;

import javafx.scene.paint.Color;
import org.fxyz.geometry.Point3D;
import org.fxyz.utils.Palette.ColorPalette;
import org.fxyz.utils.Patterns.CarbonPatterns;

import java.util.function.Function;

/**
 * @author usuario
 */
public interface TextureMode {

    void setTextureModeNone();

    void setTextureModeNone(Color color);

    void setTextureModeNone(Color color, String image);

    void setTextureModeImage(String image);

    void setTextureModePattern(CarbonPatterns pattern, double scale);

    void setTextureModeVertices3D(int colors, Function<Point3D, Number> dens);

    void setTextureModeVertices3D(ColorPalette palette, int colors, Function<Point3D, Number> dens);

    void setTextureModeVertices3D(int colors, Function<Point3D, Number> dens, double min, double max);

    void setTextureModeVertices1D(int colors, Function<Number, Number> function);

    void setTextureModeVertices1D(ColorPalette palette, int colors, Function<Number, Number> function);

    void setTextureModeVertices1D(int colors, Function<Number, Number> function, double min, double max);

    void setTextureModeFaces(int colors);

    void setTextureModeFaces(ColorPalette palette, int colors);
}
