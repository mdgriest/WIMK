package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SortingSettingsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_settings_screen);

        //functionality needed for when checkbox is toggled, to sort in chosen way

        Button btnVerifSortRule = (Button) findViewById(R.id.btnVerifSortRule);
        //need functionality to actually update the item selected!
        btnVerifSortRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortingSettingsScreen.this, SettingsScreen.class));
            }
        });
    }

}
