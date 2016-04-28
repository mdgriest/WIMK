package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class SortingSettingsScreen extends AppCompatActivity implements android.view.View.OnClickListener{
    Button btnDone;
    private RadioGroup sortButtons;
    Database db = new Database(this);
    public static int rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_settings_screen);

        btnDone = (Button) findViewById(R.id.btnSortingSettingsDone);
        btnDone.setOnClickListener(this);

        sortButtons = (RadioGroup)findViewById(R.id.sortingButtons);

        /* By default, sort alphabetically, A to Z */
        RadioButton defaultRule = (RadioButton)findViewById(R.id.rdbtnSortOption0);
        defaultRule.setChecked(true);
    }

    @Override
    public void onClick(View view){
        /* When the user clicks 'DONE' */
        if(view == findViewById(R.id.btnSortingSettingsDone)){

            /* Determine which of the radio buttons is checked */
            int selectedID = sortButtons.getCheckedRadioButtonId();

            /* Get text from that radio button */
            RadioButton selectedButton = (RadioButton) findViewById(selectedID);
            String sortingChoiceText = selectedButton.getText().toString();
            switch(sortingChoiceText){
                case "Sort by Quantity, low to high":
                    rule = 0;
                    db.sort(rule);
                    break;
                case "Sort by Quantity, high to low":
                    rule = 1;
                    db.sort(rule);
                    break;
                case "Sort Alphabetically, A to Z":
                    rule = 2;
                    db.sort(rule);
                    break;
                case "Sort Alphabetically, Z to A":
                    rule = 3;
                    db.sort(rule);
                    break;
                default:
                    rule = 0;
                    db.sort(rule);
            }
            startActivity(new Intent(SortingSettingsScreen.this, SettingsScreen.class));
        }
    }
    public static int getRule() {
        return rule;
    }
}
