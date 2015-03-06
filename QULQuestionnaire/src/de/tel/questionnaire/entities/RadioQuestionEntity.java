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
public class RadioQuestionEntity extends BasisQuestionEntity {

  public static final Integer HORIZONTAL = 0;//"horizontal";
  public static final Integer VERTICAL = 1;//"vertical";
  
  private Integer orientation;
  private Boolean randomized;
  private Boolean other;
  private String minLabel;
  private String maxLabel;
  private RadioOption[] options;

  public RadioQuestionEntity(Integer orientation, Boolean randomized, Boolean other, String minLabel, String maxLabel, RadioOption[] options, BasisQuestionEntity basis) {
    super(basis);
    this.orientation = orientation;
    this.randomized = randomized;
    this.other = other;
    this.minLabel = minLabel;;
    this.maxLabel = maxLabel;
    this.options = options;
  }

  public RadioQuestionEntity(Integer orientation, Boolean randomized, Boolean other, String minLabel, String maxLabel, RadioOption[] options, String key, String type, String question, String instruction, Boolean required) {
    super(key, type, question, instruction, required);
    this.orientation = orientation;
    this.randomized = randomized;
    this.other = other;
    this.minLabel = minLabel;
    this.maxLabel = maxLabel;
    this.options = options;
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

  public Integer getOrientation() {
    return orientation;
  }

  public void setOrientation(Integer orientation) {
    this.orientation = orientation;
  }

  public Boolean getRandomized() {
    return randomized;
  }

  public void setRandomized(Boolean randomized) {
    this.randomized = randomized;
  }

  public Boolean getOther() {
    return other;
  }

  public void setOther(Boolean other) {
    this.other = other;
  }

  public RadioOption[] getOptions() {
    return options;
  }

  public void setOptions(RadioOption[] options) {
    this.options = options;
  }
  
}
