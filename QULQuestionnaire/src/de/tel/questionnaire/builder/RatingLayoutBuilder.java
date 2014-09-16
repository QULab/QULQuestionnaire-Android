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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class RatingLayoutBuilder extends QuestionLayoutBuilder {

  
  public static final String QUESTION_TYPE_RATING = "smiley";

  public RatingLayoutBuilder(Context context, AnswerLogging logging) {
    super(context, logging);
  }

  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll, final BasisQuestionEntity basis,final Button next) {
    
    if (!basis.getType().equals(QUESTION_TYPE_RATING))
      return ll;
    
    RatingBar ratingbar = new RatingBar(context);
    ratingbar.setNumStars(5);
    ratingbar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                                               LayoutParams.WRAP_CONTENT));
    
    ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

      public void onRatingChanged(RatingBar bar, float rating, boolean fromUser) {
        String rate = Float.toString(rating);
        Log.d(RatingLayoutBuilder.class.getName(), rate);
        logging.addAnswer(basis.getQuestion(), rate);
        next.setVisibility(View.VISIBLE);
      }
    });
    
    ll.addView(ratingbar);
    return ll;
  }

  @Override
  public String getType() {
    return QUESTION_TYPE_RATING;
  }
  
  
  
  @Override
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    return super.getQuestion(json);
  }
  
  
}
