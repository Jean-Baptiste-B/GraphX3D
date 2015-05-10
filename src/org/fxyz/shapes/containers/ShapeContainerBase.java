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
package org.fxyz.shapes.containers;

import javafx.collections.ObservableList;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

/**
 * Basic Group for holding FX(yz) primitive MeshViews
 * Allows for selfIllumination and emissiveLighting.
 * Lazily implemented Transforms: Rotate(x,y,z), Translate, Scale.
 *
 * @author Dub
 * @param <T> the primitiveMeshClass this container implements
 */
public interface ShapeContainerBase<T extends MeshView>{
    
    
    default void initialize(){
        getSelfIlluminationLight().setLightOn(false);
        getEmissiveLight().setLightOn(false);
        
        getShape().setMaterial(getMaterial());
        getContainer().getChildren().addAll(getSelfIlluminationLight(), getEmissiveLight(), getShape());
    }

    default Color getEmissiveLightingColor() {
        return getEmissiveLight().getColor();
    }

    /*
     *
     * Material and Lighting methods
     *
     */
    default void setEmissiveLightingColor(Color value) {
        getEmissiveLight().setColor(value);
    }

    default boolean isEmissiveLightingOn() {
        return getEmissiveLight().isLightOn();
    }

    default void setEmissiveLightingOn(boolean value) {
        getEmissiveLight().setLightOn(value);
    }

    default ObservableList<Node> getEmissiveLightingScope() {
        return getEmissiveLight().getScope();
    }

    default Color getDiffuseColor() {
        return getMaterial().getDiffuseColor();
    }

    default void setDiffuseColor(Color value) {
        getMaterial().setDiffuseColor(value);
    }

    default Color getSpecularColor() {
        return getMaterial().getSpecularColor();
    }

    default void setSpecularColor(Color value) {
        getMaterial().setSpecularColor(value);
    }

    default double getSpecularPower() {
        return getMaterial().getSpecularPower();
    }

    default void setSpecularPower(double value) {
        getMaterial().setSpecularPower(value);
    }

    default Image getDiffuseMap() {
        return getMaterial().getDiffuseMap();
    }

    default void setDiffuseMap(Image value) {
        getMaterial().setDiffuseMap(value);
    }

    default Image getSpecularMap() {
        return getMaterial().getSpecularMap();
    }

    default void setSpecularMap(Image value) {
        getMaterial().setSpecularMap(value);
    }

    default Image getBumpMap() {
        return getMaterial().getBumpMap();
    }

    default void setBumpMap(Image value) {
        getMaterial().setBumpMap(value);
    }

    default Image getSelfIlluminationMap() {
        return getMaterial().getSelfIlluminationMap();
    }

    default void setSelfIlluminationMap(Image value) {
        getMaterial().setSelfIlluminationMap(value);
    }

    default Color getSelfIlluminationColor() {
        return getSelfIlluminationLight().getColor();
    }

    default void setSelfIlluminationColor(Color value) {
        getSelfIlluminationLight().setColor(value);
    }

    default boolean isSelfIlluminationLightOn() {
        return getSelfIlluminationLight().isLightOn();
    }

    default void setSelfIlluminationLightOn(boolean value) {
        getSelfIlluminationLight().setLightOn(value);
    }

    T getShape();
    Group getContainer();
    PhongMaterial getMaterial();
    PointLight getEmissiveLight();
    AmbientLight getSelfIlluminationLight();
    
}
