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
public class CheckboxQuestionEntity extends BasisQuestionEntity {
  
  private Boolean randomized;
  private Integer maxSelectable;
  private CheckboxOption[] options;

  public CheckboxQuestionEntity(Boolean randomized, Integer maxSelectable, CheckboxOption[] options, BasisQuestionEntity basis) {
    super(basis);
    this.randomized = randomized;
    this.maxSelectable = maxSelectable;
    this.options = options;
  }
  
  public CheckboxQuestionEntity(Boolean randomized, Integer maxSelectable, CheckboxOption[] options, String key, String type, String question, String instruction, Boolean required) {
    super(key, type, question, instruction, required);
    this.randomized = randomized;
    this.maxSelectable = maxSelectable;
    this.options = options;
  }

  public Boolean getRandomized() {
    return randomized;
  }

  public void setRandomized(Boolean randomized) {
    this.randomized = randomized;
  }

  public Integer getMaxSelectable() {
    return maxSelectable;
  }

  public void setMaxSelectable(Integer maxSelectable) {
    this.maxSelectable = maxSelectable;
  }

  public CheckboxOption[] getOptions() {
    return options;
  }

  public void setOptions(CheckboxOption[] options) {
    this.options = options;
  }
  
}
