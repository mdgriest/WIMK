package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);

        /*
            Let's use a better looking font for the main title
        */
//        TextView txtAppName = (TextView) findViewById(R.id.txtAppName);
//        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/clemente_p_daa_hairline");
//        txtAppName.setTypeface(type);
        //TODO why does this not work?

        /*
            When 'Settings' is clicked, launch Settings Screen activity
        */
        Button btnMainSettings = (Button) findViewById(R.id.btnMainSettings);
        btnMainSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainMenuScreen.this, SettingsScreen.class));
            }
        });

        /*
            When 'Visual Inventory' is clicked, launch Visual Inventory activity
        */
        Button btnVisualInventory = (Button) findViewById(R.id.btnVisualInventory);
        btnVisualInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuScreen.this, VisualInventoryScreen.class));
//                VisualInventoryScreen vis = new VisualInventoryScreen();
//                display all items
//                vis.showAllItems();
            }
        });


    }
}
