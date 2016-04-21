package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class VisualInventoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_inventory_screen);

          /* When Add Item button is clicked, launch Update Item Properties activity with default values */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add item to inventory
                Inventory newInventory = new Inventory();
                newInventory.addItem();
                startActivity(new Intent(VisualInventoryScreen.this, UpdateItemPropertiesScreen.class));
            }
        });

        /* When Search button is clicked, need to be able to launch keyboard to type*/
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchText = (EditText) findViewById(R.id.searchText);
                /* Make the textView visible so the user can enter a query */
                searchText.setVisibility(View.VISIBLE);
                /* Shift focus to the textView to avoid making the user manually click on it */
                searchText.requestFocus();
                /* Launch the keyboard for user input */
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchText, InputMethodManager.SHOW_IMPLICIT);

                //get query from search bar
                String query = searchText.getText().toString();
                //pass query to searchByName method in inventory class
                Inventory in = new Inventory();
                in.searchByName(query);
            }
        });

        /* When Back button is clicked, launch Main Menu activity */
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
            }
        });

        /* TODO When An items icon is clicked, launch that items View Item Screen
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnViewItem = (Button) findViewById(R.id.btnViewItem);
        btnViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualInventoryScreen.this, ViewItemScreen.class));
            }
        }); */

    }

        //this method will be used to figure out which item to launch in the ViewItem screen
        public void determineWhichItemWasClicked(){
        }

        //display items from search
        public void showItemsFromSearch(){
            Inventory newInventory = new Inventory();
           for(int i = 0; i < newInventory.itemsList.size(); i++){
               if(newInventory.itemsList.get(i).shouldShow == true){
                   //DISPLAY
               }
               else{
                   //DON'T DISPLAY
               }
           }

        }
}
