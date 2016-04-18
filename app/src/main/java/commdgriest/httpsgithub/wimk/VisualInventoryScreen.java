package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VisualInventoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_inventory_screen);

        //Based on:
        // http://developer.android.com/guide/topics/ui/declaring-layout.html#attributes
        // http://stackoverflow.com/questions/34176722/android-how-to-add-a-button-with-text-inside-collapsing-toolbar 

        // /* When Add Item button is clicked, launch Add Item activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, AddItemScreen.class));
            }
        });


        /* When Search is clicked, need to be able to launch keyboard to type*/
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch Text Edit to narrow search 
                // Based on: http://stackoverflow.com/questions/9579383/display-an-edit-text-box-on-click-of-a-button
                // http://developer.android.com/guide/topics/ui/controls/text.html#Keyboard
                EditText searchText = (EditText) findViewById(R.id.searchText);
                searchText.setVisibility(View.VISIBLE);
            }
        });

        /* When Back button is clicked, launch Main Menu activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
            }
        });
    }
}
