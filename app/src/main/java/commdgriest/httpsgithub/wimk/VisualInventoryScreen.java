package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class VisualInventoryScreen extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnAddItem;
    Button btnBack;
    Button btnSearch;
    TextView itemID;
    Button btnShowAll;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_inventory_screen);


        //Need to sort before displaying items

        /* Add a dummy item to the DB */
        Item testItem0 = new Item();
        db.addItem(testItem0);

        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        List<Item> allItems = db.getAllItems();

        String[] values = new String[allItems.size()];

        for(int i = 0; i<allItems.size(); i++){
            values[i] = allItems.get(i).getName().toString();
        }

            /* If there are items to display */
        if( allItems.size() != 0 ) {
            Toast.makeText(this, allItems.size() + " items in inventory!", Toast.LENGTH_SHORT).show();

            ListView lv = (ListView) findViewById(R.id.vi_listView);

            //TODO change list textColor
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);
            lv.setAdapter(adapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                }
            });


        }
            /* If the inventory is empty */
        else{
            Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
        }
    }

    /* OnClick Listeners */
    @Override
    public void onClick(View view) {
        /* Add Item */
        if (view == findViewById(R.id.btnAddItem)){
            Intent intent = new Intent(VisualInventoryScreen.this, UpdateItemPropertiesScreen.class);
            intent.putExtra("item_Id", 0);
            startActivity(intent);
        }

        /* Back */
        else if(view == findViewById(R.id.btnBack)){
            startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
        }

        /* Search */
        else if(view == findViewById(R.id.btnSearch)){
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

            List<Item> items = db.search(query);

            Toast.makeText(this, items.size() + " items in the inventory", Toast.LENGTH_SHORT).show();


            //TODO need to query into DB with String query
        }
    }
}
