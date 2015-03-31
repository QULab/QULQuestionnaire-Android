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
import android.widget.SeekBar;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.entities.SliderQuestionEntity;
import de.tel.questionnaire.util.AnswerLogging;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class SliderLayout extends QuestionLayout {

  public static final String QUESTION_TYPE_SLIDER = "slider";
  
  public SliderLayout(Context context, AnswerLogging logging) {
    super(context, logging);
  }
  
  
  
  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll,
                                        BasisQuestionEntity basis,
                                        final Button next) {
    
    if (!basis.getType().equals(QUESTION_TYPE_SLIDER))
      return ll;
    
    final SliderQuestionEntity sliderQuestion = (SliderQuestionEntity) basis;
    SeekBar bar = new SeekBar(context);
    bar.setMax((int) Math.round(sliderQuestion.getMaxValue()));
    bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

      public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
      }

      public void onStartTrackingTouch(SeekBar bar) {
      }

      public void onStopTrackingTouch(SeekBar bar) {
        answer = bar.getProgress();
        next.setVisibility(View.VISIBLE);
      }
    });
    ll.addView(bar);
    return ll;
  }

  @Override
  public String getType() {
    return QUESTION_TYPE_SLIDER;
  }
  
  @Override
  protected BasisQuestionEntity getQuestion(JSONObject json) throws JSONException {
    BasisQuestionEntity basis = super.getQuestion(json);
    SliderQuestionEntity sliderEntity = new SliderQuestionEntity((float) json.getDouble(JSON_KEY_MAX_VALUE),
                                                                basis);
    return sliderEntity;
  }
  
  
  
}
