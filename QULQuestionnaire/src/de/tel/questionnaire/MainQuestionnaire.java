package de.tel.questionnaire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.tel.questionnaire.builder.CheckboxLayoutBuilder;
import de.tel.questionnaire.builder.QuestionLayoutBuilder;
import de.tel.questionnaire.builder.QuestionnaireBuilder;
import de.tel.questionnaire.builder.RadioLayoutBuilder;
import de.tel.questionnaire.builder.RatingLayoutBuilder;
import de.tel.questionnaire.builder.SliderLayoutBuilder;
import de.tel.questionnaire.builder.TextLayoutBuilder;
import de.tel.questionnaire.util.AnswerLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

public class MainQuestionnaire extends Activity {

  
  AnswerLogger logger = new AnswerLogger();
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    logger.setStart();
    Map<String, QuestionLayoutBuilder> layoutBuilders = new HashMap<String, QuestionLayoutBuilder>();
    RadioLayoutBuilder r = new RadioLayoutBuilder(this, logger);
    layoutBuilders.put(r.getType(), r);
    RatingLayoutBuilder rating = new RatingLayoutBuilder(this, logger);
    layoutBuilders.put(rating.getType(), rating);
    CheckboxLayoutBuilder checkbox = new CheckboxLayoutBuilder(this, logger);
    layoutBuilders.put(checkbox.getType(), checkbox);
    SliderLayoutBuilder slider = new SliderLayoutBuilder(this, logger);
    layoutBuilders.put(slider.getType(), slider);
    TextLayoutBuilder text = new TextLayoutBuilder(this, logger);
    layoutBuilders.put(text.getType(), text);
    
    
    QuestionnaireBuilder builder = new QuestionnaireBuilder(logger, this, layoutBuilders);
    try {
      super.onCreate(savedInstanceState);
      InputStream stream = getResources().openRawResource(R.raw.questionnaire);
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      StringBuilder strBuilder = new StringBuilder();
      String line = "";
      while ((line = reader.readLine()) != null) {
        strBuilder.append(line);
      }

      JSONArray array = new JSONArray(strBuilder.toString());
      setContentView(builder.createQuestion(array));
    } catch (JSONException ex) {
      Logger.getLogger(MainQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(MainQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  protected void onDestroy() {
    logger.setEnd();
    Log.d(MainQuestionnaire.class.getName(), logger.getAnswerLog());
    super.onDestroy();
  }
  
  
}
