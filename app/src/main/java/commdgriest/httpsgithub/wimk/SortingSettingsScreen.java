package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class SortingSettingsScreen extends AppCompatActivity {


        private static int tempSortingRule;
        private static final int radioButton1 = 0;//first radio button id
        private static final int radioButton2 = 1;//second radio button id
        private static final int radioButton3 = 2;//third radio button id
        private static final int radioButton4 = 3;//fourth radio button id


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sorting_settings_screen);

            Button btnVerifSortRule = (Button) findViewById(R.id.btnVerifSortRule);
            RadioButton rb1 = (RadioButton) findViewById(R.id.rdbtnSortOption0);
            rb1.setId(radioButton1);

            RadioButton rb2 = (RadioButton) findViewById(R.id.rdbtnSortOption1);
            rb2.setId(radioButton2);

            RadioButton rb3 = (RadioButton) findViewById(R.id.rdbtnSortOption2);
            rb3.setId(radioButton3);

            RadioButton rb4 = (RadioButton) findViewById(R.id.rdbtnSortOption3);
            rb4.setId(radioButton4);

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
