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
package de.tel.questionnaire.layout;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import de.tel.questionnaire.R;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public abstract class QuestionLayout {
  
  
  //JSON KEYS for BASIS QUESTION
  protected static final String JSON_KEY_TYPE = "type";
  protected static final String JSON_KEY_QUESTION  = "question";
  protected static final String JSON_KEY_INSTRUCTION = "instruction";
  protected static final String JSON_KEY_KEY = "key";
  protected static final String JSON_KEY_REQUIRED = "required";
  
  //JSON KEYS for RADIO QUESTION
  protected static final String JSON_KEY_ORIENTATION = "orientation";
  protected static final String JSON_KEY_RANDOMIZED = "randomized";
  protected static final String JSON_KEY_OTHER = "other";
  protected static final String JSON_KEY_OPTIONS = "options";
  //JSON KEYS for RADIO QUESTION OPTION
  protected static final String JSON_KEY_VALUE = "value";
  protected static final String JSON_KEY_DEFAULT = "default";
  
  //JSON KEYS for CHECKBOX QUESTION
  protected static final String JSON_KEY_MAX_SELECTABLE = "maxSelectable";
  
  //JSON KEYS for SLIDER QUESTION
  protected static final String JSON_KEY_MAX_VALUE = "maxValue";
  
  //JSON KEYS for TEXT QUESTION
  protected static final String JSON_KEY_INPUT = "input";
  protected static final String JSON_TYPE_PLACEHOLDER = "placeholder";
  
  
  protected Context context;
  protected AnswerLogging logging;
  
  public QuestionLayout(Context context, AnswerLogging logging) {
    this.context = context;
    this.logging = logging;
  }
  
  protected abstract LinearLayout addQuestionLayout(LinearLayout ll,
                                                    BasisQuestionEntity basis,
                                                    Button next);
  
  public LinearLayout createQuestionLayout(LinearLayout ll, BasisQuestionEntity basis) 
  {
    Button btn = new Button(context);
    btn.setText(context.getString(R.string.question_next_btn));
    btn.setId(Questionnaire.BTN_NEXT_ID);
    
    if (basis.getRequired())
      btn.setVisibility(View.INVISIBLE);
    
    addQuestionLayout(ll, basis, btn);
    ll.addView(btn);
    return ll;
  }
  
  public abstract String getType();
  public abstract String getLastGivenAnswer();
  
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    BasisQuestionEntity basis = new BasisQuestionEntity(json.getString(JSON_KEY_KEY),
                                                        json.getString(JSON_KEY_TYPE),
                                                        json.getString(JSON_KEY_QUESTION),
                                                        json.optString(JSON_KEY_INSTRUCTION, ""),
                                                        json.optBoolean(JSON_KEY_REQUIRED));
    return basis;
  }
  
}
