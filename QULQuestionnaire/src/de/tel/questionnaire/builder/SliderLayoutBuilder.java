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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import de.tel.questionnaire.entities.BasisQuestionEntity;
import de.tel.questionnaire.entities.SliderQuestionEntity;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class SliderLayoutBuilder extends QuestionLayoutBuilder {

  public static final String QUESTION_TYPE_SLIDER = "slider";
  
  
  private int sliderValue;
  
  public SliderLayoutBuilder(Context context) {
    super(context);
  }
  
  @Override
  public LinearLayout addQuestionLayout(LinearLayout ll, BasisQuestionEntity basis) {
    
    if (!basis.getType().equals(QUESTION_TYPE_SLIDER))
      return ll;
    
    SliderQuestionEntity sliderQuestion = (SliderQuestionEntity) basis;
    SeekBar bar = new SeekBar(context);
    bar.setMax((int) Math.round(sliderQuestion.getMaxValue()));
    bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

      public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
      }

      public void onStartTrackingTouch(SeekBar bar) {
      }

      public void onStopTrackingTouch(SeekBar bar) {
        int progress = bar.getProgress();
        Toast.makeText(context, Integer.toString(progress), Toast.LENGTH_SHORT).show();
        sliderValue = progress;
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
