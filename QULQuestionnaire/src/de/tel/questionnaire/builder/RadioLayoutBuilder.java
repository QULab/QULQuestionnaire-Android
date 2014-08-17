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
package de.tel.questionnaire.builder;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.entities.RadioOption;
import de.tel.questionnaire.entities.RadioQuestionEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class RadioLayoutBuilder extends QuestionLayoutBuilder {

  
  public static final String QUESTION_TYPE_RADIO = "radio";
  
  public RadioLayoutBuilder(Context context) {
    super(context);
  }
  
  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll, BasisQuestionEntity basis) {
      
    if (!basis.getType().equals(QUESTION_TYPE_RADIO))
      return ll;
    
    RadioQuestionEntity radioQuestion = (RadioQuestionEntity) basis;
    RadioGroup grp = new RadioGroup(context);
   
    String orientation = radioQuestion.getOrientation();
    
    if (orientation.equals(RadioQuestionEntity.HORIZONTAL))
      grp.setOrientation(RadioGroup.HORIZONTAL);
    
    RadioOption[] options = radioQuestion.getOptions();
    for (RadioOption option : options) {
      RadioButton radioBtn = new RadioButton(context);
      radioBtn.setText(option.getValue());
//      radioBtn.setChecked(option.getOpDefault()); // set default 
      grp.addView(radioBtn);
    }
    
    ll.addView(grp);
    return ll;
  }

  @Override
  public String getType() {
    return QUESTION_TYPE_RADIO;
  }
  
  @Override
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    BasisQuestionEntity basis = super.getQuestion(json);
    return createRadioQuestionEntity(json, basis);
  }
  
  private RadioQuestionEntity createRadioQuestionEntity(JSONObject json, BasisQuestionEntity basis) throws JSONException {
    JSONArray options = json.getJSONArray(JSON_KEY_OPTIONS);
    RadioQuestionEntity radio = new RadioQuestionEntity(json.getString(JSON_KEY_ORIENTATION),
                                                        json.getBoolean(JSON_KEY_RANDOMIZED),
                                                        json.optBoolean(JSON_KEY_OTHER, false), 
                                                        createRadioOptions(options),
                                                        basis);
    return radio;
  }
  
  private RadioOption[] createRadioOptions(JSONArray array) {
    int len = array.length();
    RadioOption[] options = new RadioOption[len];
    for (int i = 0; i < len; i++) {
      try {
        JSONObject obj = array.getJSONObject(i);
        options[i] = new RadioOption(obj.optBoolean(JSON_KEY_DEFAULT, false),
                                     obj.getString(JSON_KEY_KEY),
                                     obj.getString(JSON_KEY_VALUE));
      } catch (JSONException ex) {
        Log.e(QuestionnaireBuilder.class.getName(), "JSONException in createRadioOptions", ex);
      }
    }
    return options;
  }
  
  
}
