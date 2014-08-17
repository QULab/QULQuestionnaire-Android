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
public class RankingQuestionEntity extends BasisQuestionEntity {
  
  private Boolean randomized;
  private Option[] options;

  public RankingQuestionEntity(Boolean randomized, Option[] options, BasisQuestionEntity basis) {
    super(basis);
    this.randomized = randomized;
    this.options = options;
  }
  
  

  public RankingQuestionEntity(Boolean randomized, Option[] options, String key, String type, String question, String instruction, Boolean required) {
    super(key, type, question, instruction, required);
    this.randomized = randomized;
    this.options = options;
  }

  public Boolean getRandomized() {
    return randomized;
  }

  public void setRandomized(Boolean randomized) {
    this.randomized = randomized;
  }

  public Option[] getOptions() {
    return options;
  }

  public void setOptions(Option[] options) {
    this.options = options;
  }
}
