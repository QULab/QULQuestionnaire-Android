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
package de.tel.questionnaire.util;

import android.util.Log;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class AnswerLogger implements AnswerLogging {
  
  public static final String START_JSON_KEY = "start";
  public static final String END_JSON_KEY = "end";
  public static final String DATA_JSON_KEY = "data";
  public static final String QUESTION_JSON_KEY = "q";
  public static final String ANSWER_JSON_KEY = "a";
  
  private JSONObject log;
  private JSONArray data;

  public AnswerLogger() {
    log = new JSONObject();
    data = new JSONArray();
  }
  
  public void setStart() {
    addValueToJSON(log, START_JSON_KEY, Long.toString(new Date().getTime()));
  }

  public void setEnd() {
    addValueToJSON(log, END_JSON_KEY, Long.toString(new Date().getTime()));
  }

  public void addAnswer(String question, String answer) {
    JSONObject json = new JSONObject();
    addValueToJSON(json, QUESTION_JSON_KEY, question);
    addValueToJSON(json, ANSWER_JSON_KEY, answer);
    data.put(json);
  }
  
  
  public void addValueToJSON(JSONObject json, String key, String value) {
    if (key == null || value == null)
      return;
    
    try {
      json.put(key, value);
    } catch (JSONException ex) {
      Log.e(AnswerLogger.class.getName(), key + " value: " + value, ex);
    }
  }

  public String getAnswerLog() {
    if (!log.has(DATA_JSON_KEY)) {
      addValueToJSON(log, DATA_JSON_KEY, data.toString());
    }
    return log.toString();
  }
  
}
