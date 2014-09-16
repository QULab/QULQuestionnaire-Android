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

import android.app.Activity;
import android.content.Context;
import android.database.CursorJoiner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.tel.questionnaire.R;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class QuestionnaireBuilder {
  
  public static final Integer BTN_NEXT_ID = 0xFF231;

  private Context context;
  private Map<String, QuestionLayoutBuilder> layoutBuilders;
  private AnswerLogging logging;

  public QuestionnaireBuilder(AnswerLogging logging, Context context, Map<String, QuestionLayoutBuilder> layoutBuilders) {
    this.context = context;
    this.layoutBuilders = layoutBuilders;
    this.logging = logging;
  }

  public View createQuestion(JSONArray array) throws JSONException {
    final LinearLayout ll = new LinearLayout(context);
    ll.setOrientation(LinearLayout.VERTICAL);
    if (array.length() == 0) {
      return ll;
    }

    return createQuestion(ll, array, 0);
  }

  private View createQuestion(final LinearLayout ll, final JSONArray array, final int step) throws JSONException {
    if (array.length() == step) { //anchor
      //finish activity
      ((Activity) context).finish();
      return ll;
    }

    JSONObject json = (JSONObject) array.getJSONObject(step);
    QuestionLayoutBuilder qlayoutBuilder = layoutBuilders.get(json.getString(QuestionLayoutBuilder.JSON_KEY_TYPE));
    if (qlayoutBuilder == null) {
      qlayoutBuilder = new QuestionLayoutBuilder(context, logging) {
        @Override
        public LinearLayout addQuestionLayout(LinearLayout ll,
                                              BasisQuestionEntity basis,
                                              final Button next) {
          next.setVisibility(View.VISIBLE);
          return ll;
        }

        @Override
        public String getType() {
          return "";
        }
      };
    }
    BasisQuestionEntity questionEntity = qlayoutBuilder.getQuestion(json);
    addTextView(ll, questionEntity.getQuestion(), 18);
    addTextView(ll, questionEntity.getInstruction(), 12);
    qlayoutBuilder.createQuestionLayout(ll, questionEntity);
    addButton(ll, array, step);
    return ll;
  }
  
  
  private void addButton(final LinearLayout ll, final JSONArray array, final int step) {
//    Button btn = new Button(context);
//    btn.setText(context.getString(R.string.question_next_btn));
    Button btn = (Button) ll.findViewById(BTN_NEXT_ID);
    btn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View arg0) {
        ll.removeAllViews();
        try {
            createQuestion(ll, array, step + 1); //recursion
        } catch (JSONException ex) {
          Log.e(QuestionnaireBuilder.class.getName(), "Next click JSON exception", ex);
        }
      }
    });
    //ll.addView(btn);
  }
  
  
  private void addTextView(LinearLayout ll, String content, int textSize) {
    TextView view = new TextView(context);
    view.setText(content);
    view.setTextSize(textSize);
    ll.addView(view);
  }
}
