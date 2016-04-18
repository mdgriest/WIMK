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
                    //now call sort (which will check if sorting rule has changed)
                    Inventory.sort();


                    startActivity(new Intent(SortingSettingsScreen.this, SettingsScreen.class));

                }
            });



        }

        //check to see if sorting rule has changed
        public static boolean hasChanged(int oldSortingRule){
            //meaning no button was selected
            if(tempSortingRule == -1){
                return false;
            }
            //meaning sorting rule has not changed
            else if(tempSortingRule == oldSortingRule)
                return false;
            //sorting rule has changed
            else {
                //store the new sorting rule as the inventory's sorting rule
                Inventory.sortingRule = tempSortingRule;
                return true;
            }
        }

    }
