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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_settings_screen);

        btnDone = (Button) findViewById(R.id.btnSortingSettingsDone);
        btnDone.setOnClickListener(this);

        sortButtons = (RadioGroup)findViewById(R.id.sortingButtons);
    }

    @Override
    public void onClick(View view){
        /* Add Item */
        if(view == findViewById(R.id.btnSortingSettingsDone)){
            int selectedID = sortButtons.getCheckedRadioButtonId();
            RadioButton selectedButton = (RadioButton) findViewById(selectedID);
            Toast.makeText(this, "SelectedID: " + selectedButton.getText(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SortingSettingsScreen.this, MainMenuScreen.class));
        }
    }
}
