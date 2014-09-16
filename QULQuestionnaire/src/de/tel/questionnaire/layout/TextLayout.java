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
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.entities.TextQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class TextLayout extends QuestionLayout {

  public static final String QUESTION_TYPE_TEXT = "text";
  protected static final String INPUT_TYPE_TEXT = "text";
  protected static final String INPUT_TYPE_EMAIL = "email";
  protected static final String INPUT_TYPE_NUMBER = "number";
  private static final HashMap<String, Integer> INPUT_TYPES = new HashMap<String, Integer>();
  private String answer;

  static {
    INPUT_TYPES.put(INPUT_TYPE_TEXT, InputType.TYPE_CLASS_TEXT);
    INPUT_TYPES.put(INPUT_TYPE_EMAIL, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    INPUT_TYPES.put(INPUT_TYPE_NUMBER, InputType.TYPE_CLASS_NUMBER);
  }

  public TextLayout(Context context, AnswerLogging logging) {
    super(context, logging);
  }

  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll,
          BasisQuestionEntity basis,
          final Button next) {


    if (!basis.getType().equals(QUESTION_TYPE_TEXT)) {
      return ll;
    }

    final TextQuestionEntity textEntity = (TextQuestionEntity) basis;
    EditText editText = new EditText(context);

    editText.setHint(textEntity.getPlaceHolder());
    editText.setInputType(INPUT_TYPES.get(textEntity.getInput()));
    editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
        if (arg1 == EditorInfo.IME_ACTION_DONE) {
          String text = arg0.getText().toString();
          submitText(textEntity.getKey(), text, next);
          next.callOnClick();
          return true;
        }
        return false;
      }
    });

    editText.addTextChangedListener(new TextWatcher() {
      public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      public void afterTextChanged(Editable arg0) {
        submitText(textEntity.getKey(), arg0.toString(), next);
      }
    });

    ll.addView(editText);
    return ll;
  }

  private void submitText(String question, String value, final Button next) {
    Log.d(TextLayout.class.getName(), value);
//    logging.addAnswer(question, value);
    answer = value;
    next.setVisibility(View.VISIBLE);
  }

  @Override
  public String getType() {
    return QUESTION_TYPE_TEXT;
  }

  @Override
  public String getLastGivenAnswer() {
    return answer;
  }

  @Override
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    BasisQuestionEntity basis = super.getQuestion(json);
    TextQuestionEntity text = new TextQuestionEntity(json.optString(JSON_KEY_INPUT, INPUT_TYPE_TEXT),
            json.getString(JSON_TYPE_PLACEHOLDER),
            basis);

    return text;
  }
}
