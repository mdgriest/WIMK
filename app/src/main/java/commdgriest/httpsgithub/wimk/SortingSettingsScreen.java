package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SortingSettingsScreen extends AppCompatActivity {


        private static int tempSortingRule;
        private static final int RB1_ID = 1000;//first radio button id

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sorting_settings_screen);

            Button btnVerifSortRule = (Button) findViewById(R.id.btnVerifSortRule);


            btnVerifSortRule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //before going back to settings screen, save new sorting rule as temp sorting rule
                    RadioGroup sortingButtons = (RadioGroup) findViewById(R.id.sortingButtons);

                    //UNSURE OF WHAT INTS ARE BEING RETURNED** (R.id.btnQhightolow)??
                    tempSortingRule = sortingButtons.getCheckedRadioButtonId();

                    startActivity(new Intent(SortingSettingsScreen.this, SettingsScreen.class));

                }
            });



        }

        //check to see if sorting rule has changed
        public static boolean hasChanged(int newSortingRule){
            //meaning no button was selected
            if(tempSortingRule == -1){
                return false;
            }
            else if(tempSortingRule == newSortingRule)
                return false;
            else return true;
        }

    }
