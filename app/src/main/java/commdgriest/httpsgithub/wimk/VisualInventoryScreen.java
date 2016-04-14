package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VisualInventoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_inventory_screen);

        /*
            When 'Add Item' is clicked, add a new item to inventory with default values
            and launch the Update Item Properties Screen for that item
        */
        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            //TODO add default item to inventory
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, AddItemScreen.class));
            }
        });

        //TODO Handle 'Search'

         /*
            When 'Back' is clicked, return to Main Menu activity
        */
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
            }
        });

    }

}
