/*
 * QUe
 * 
 * Copyright (c) 2014 Quality and Usability Lab,
 * Telekom Innvation Laboratories, TU Berlin. All rights reserved.
 * https://github.com/QULab/QUe-Android
 * 
 * This file is part of QUe.
 * 
 * QUe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QUe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with QUe. If not, see <http://www.gnu.org/licenses/>.
 */
package de.tel.questionnaire.entities;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class SliderQuestionEntity extends BasisQuestionEntity {
  private Float minValue;
  private Float maxValue;
  private String minLabel;
  private String maxLabel;
  private Boolean showSelectedValue;
  private Integer stepValue;

  public SliderQuestionEntity(Float maxValue, BasisQuestionEntity basis) {
    super(basis);
    this.maxValue = maxValue;
  }
  
  public SliderQuestionEntity(Float minValue, Float maxValue, String minLabel, String maxLabel, Boolean showSelectedValue, Integer stepValue, BasisQuestionEntity basis) {
    super(basis);
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.minLabel = minLabel;
    this.maxLabel = maxLabel;
    this.showSelectedValue = showSelectedValue;
    this.stepValue = stepValue;
  }
  
  

  public SliderQuestionEntity(Float minValue, Float maxValue, String minLabel, String maxLabel, Boolean showSelectedValue, Integer stepValue, String key, String type, String question, String instruction, Boolean required) {
    super(key, type, question, instruction, required);
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.minLabel = minLabel;
    this.maxLabel = maxLabel;
    this.showSelectedValue = showSelectedValue;
    this.stepValue = stepValue;
  }

  public Float getMinValue() {
    return minValue;
  }

  public void setMinValue(Float minValue) {
    this.minValue = minValue;
  }

  public Float getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Float maxValue) {
    this.maxValue = maxValue;
  }

  public String getMinLabel() {
    return minLabel;
  }

  public void setMinLabel(String minLabel) {
    this.minLabel = minLabel;
  }

  public String getMaxLabel() {
    return maxLabel;
  }

  public void setMaxLabel(String maxLabel) {
    this.maxLabel = maxLabel;
  }

  public Boolean getShowSelectedValue() {
    return showSelectedValue;
  }

  public void setShowSelectedValue(Boolean showSelectedValue) {
    this.showSelectedValue = showSelectedValue;
  }

  public Integer getStepValue() {
    return stepValue;
  }

  public void setStepValue(Integer stepValue) {
    this.stepValue = stepValue;
  }
}
