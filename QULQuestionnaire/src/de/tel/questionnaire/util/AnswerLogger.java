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
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static org.json.JSONObject.NULL;

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
  
  /**
   * Date format as RFC 822
   * yyyy-MM-dd'T'HH:mm:ssZ e.g. 2015-03-31T10:49:58+0200
   * 
   * ISO 8601 will be:
   * YYYY-MM-DDThh:mm:ssTZD e.g. 2015-03-24T08:58:30+01:00
   * but is not supported in this current java version
   * yyyy-MM-dd'T'HH:mm:ssXXX would be the correct string.
   */
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); 
  
  private final JSONObject log;
  private final JSONArray data;

  public AnswerLogger() {
    log = new JSONObject();
    data = new JSONArray();
  }
  
  private String getDateInFormat() {
    return DATE_FORMAT.format(new Date());
  }
  
  public void setStart() {
    addValueToJSON(log, START_JSON_KEY, getDateInFormat());
  }

  public void setEnd() {
    addValueToJSON(log, END_JSON_KEY, getDateInFormat());
  }

  public void addAnswer(String question, Object answer) {
    JSONObject json = new JSONObject();
    addValueToJSON(json, QUESTION_JSON_KEY, question);
    addValueToJSON(json, ANSWER_JSON_KEY, answer);
    data.put(json);
  }
  
  /**
   * Adds the key-value pair to the given json object.
   * 
   * @param json the json object which gets the new key-value pair
   * @param key the json key which will be saved with the value
   * @param value the value for the json key can be a String, a Number, 
   *               other JSONObject or Arrary or NULL.
   */
  public void addValueToJSON(JSONObject json, String key, Object value) {
    if (key == null)
      return;
    
    if (value == null)
      value = NULL;
    
    try {
      json.put(key, value);
    } catch (JSONException ex) {
      Log.e(AnswerLogger.class.getName(), key + " value: " + value, ex);
    }
  }

  public String getAnswerLog() {
    if (!log.has(DATA_JSON_KEY)) {
      addValueToJSON(log, DATA_JSON_KEY, data);
    }
    return log.toString();
  }
  
}
