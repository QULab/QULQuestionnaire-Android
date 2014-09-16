package de.tel.questionnaire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.tel.questionnaire.layout.CheckboxLayout;
import de.tel.questionnaire.layout.QuestionLayout;
import de.tel.questionnaire.layout.Questionnaire;
import de.tel.questionnaire.layout.RadioLayout;
import de.tel.questionnaire.layout.RatingLayout;
import de.tel.questionnaire.layout.SliderLayout;
import de.tel.questionnaire.layout.TextLayout;
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
    Map<String, QuestionLayout> layoutBuilders = new HashMap<String, QuestionLayout>();
    RadioLayout r = new RadioLayout(this, logger);
    layoutBuilders.put(r.getType(), r);
    RatingLayout rating = new RatingLayout(this, logger);
    layoutBuilders.put(rating.getType(), rating);
    CheckboxLayout checkbox = new CheckboxLayout(this, logger);
    layoutBuilders.put(checkbox.getType(), checkbox);
    SliderLayout slider = new SliderLayout(this, logger);
    layoutBuilders.put(slider.getType(), slider);
    TextLayout text = new TextLayout(this, logger);
    layoutBuilders.put(text.getType(), text);
    
    
    Questionnaire builder = new Questionnaire(logger, this, layoutBuilders);
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
