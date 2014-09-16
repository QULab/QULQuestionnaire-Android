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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.entities.CheckboxOption;
import de.tel.questionnaire.entities.CheckboxQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CheckboxLayoutBuilder extends QuestionLayoutBuilder {

  public static final String QUESTION_TYPE_CHECKBOX = "checkbox";

  public CheckboxLayoutBuilder(Context context, AnswerLogging logging) {
    super(context, logging);
  }
  
  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll,
                                        BasisQuestionEntity basis,
                                        final Button next) {
    
    if (!basis.getType().equals(QUESTION_TYPE_CHECKBOX))
      return ll;
    
    final CheckboxQuestionEntity entity = (CheckboxQuestionEntity) basis;
    CheckboxOption[] options = entity.getOptions();
    
    for (CheckboxOption option : options) {
      CheckBox box = new CheckBox(context);
      box.setText(option.getValue());
      box.setSelected(option.getSelected());
      box.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                               ViewGroup.LayoutParams.WRAP_CONTENT));
      box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
          Log.d(CheckboxLayoutBuilder.class.getName(), arg0.getText().toString());
          logging.addAnswer(entity.getKey(), arg0.getText().toString());
          next.setVisibility(View.VISIBLE);
          
        }
      });
      ll.addView(box);
      
    }
    return ll;
  }

  @Override
  public String getType() {
    return QUESTION_TYPE_CHECKBOX;
  }

  @Override
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    BasisQuestionEntity basis = super.getQuestion(json);
    return createCheckboxQuestionEntity(json, basis);
  }
  
  private CheckboxQuestionEntity createCheckboxQuestionEntity(JSONObject json, BasisQuestionEntity basis) throws JSONException {
    JSONArray options = json.getJSONArray(JSON_KEY_OPTIONS);
    CheckboxQuestionEntity entity = new CheckboxQuestionEntity(
                                           json.getBoolean(JSON_KEY_RANDOMIZED),
                                           json.optInt(JSON_KEY_MAX_SELECTABLE, options.length()),
                                           createCheckboxOptions(options),
                                           basis);
    return entity;
  }
  
  private CheckboxOption[] createCheckboxOptions(JSONArray array) {
    int len = array.length();
    CheckboxOption[] options = new CheckboxOption[len];
    for (int i = 0; i < len; i++) {
      try {
        JSONObject obj = array.getJSONObject(i);
        options[i] = new CheckboxOption(obj.optBoolean(JSON_KEY_DEFAULT, false),
                                     obj.getString(JSON_KEY_KEY),
                                     obj.getString(JSON_KEY_VALUE));
      } catch (JSONException ex) {
        Log.e(QuestionnaireBuilder.class.getName(), "JSONException in createCheckboxOptions", ex);
      }
    }
    return options;
  }
  
  
}
