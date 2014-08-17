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
public class TextQuestionEntity extends BasisQuestionEntity {
  public static final String INPUT_TEXT = "text";
  public static final String INPUT_EMAIL = "email";
  public static final String INPUT_NUMBER = "text";
  
  
  private String input;
  private String placeHolder;

  public TextQuestionEntity(String input, String placeHolder, BasisQuestionEntity basis) {
    super(basis);
    this.input = input;
    this.placeHolder = placeHolder;
  }

  
  
  public TextQuestionEntity(String input, String placeHolder, String key, String type, String question, String instruction, Boolean required) {
    super(key, type, question, instruction, required);
    this.input = input;
    this.placeHolder = placeHolder;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public String getPlaceHolder() {
    return placeHolder;
  }

  public void setPlaceHolder(String placeHolder) {
    this.placeHolder = placeHolder;
  }
  
}
