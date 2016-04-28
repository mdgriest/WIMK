package commdgriest.httpsgithub.wimk;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
    General Settings screen
*/

public class SettingsScreen extends MainMenuScreen {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        /* When Updating Sorting Preferences button is clicked, launch Sorting Settings activity */
        Button btnUpdateSorting = (Button) findViewById(R.id.btnUpdateSorting);
        btnUpdateSorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsScreen.this, SortingSettingsScreen.class));
            }
        });

         /* When About WIMK button is clicked, launch About activity */
        Button btnAboutWIMK = (Button) findViewById(R.id.btnAboutWIMK);
        btnAboutWIMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(SettingsScreen.this, AboutScreen.class));
            }
        });

         /* When Icon Credit button is clicked, launch NounProj activity */
        Button btnIconCredit = (Button) findViewById(R.id.btnIconCredit);
        btnIconCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(SettingsScreen.this, NounProjCreditScreen.class));
            }
        });

         /* When Back button is clicked, launch MainMenu Screen activity */
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(SettingsScreen.this, MainMenuScreen.class));
            }
        });
    }
}