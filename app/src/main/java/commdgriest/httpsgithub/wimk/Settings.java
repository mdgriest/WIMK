package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alexandria on 3/21/16.
 */
public class Settings extends MainMenuScreen {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        /* When Updating Sorting Preferences button is clicked, launch Sorting Settings activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnUpdateSorting = (Button) findViewById(R.id.btnUpdateSorting);
        btnUpdateSorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, SortingSettings.class));
            }
        });


         /* When About WIMK button is clicked, launch About activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnAboutWIMK = (Button) findViewById(R.id.btnAboutWIMK);
        btnAboutWIMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Settings.this, About.class));
            }
        });


         /* When Icon Credit button is clicked, launch NounProj activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnIconCredit = (Button) findViewById(R.id.btnIconCredit);
        btnIconCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Settings.this, NounProj.class));
            }
        });
    }


}
