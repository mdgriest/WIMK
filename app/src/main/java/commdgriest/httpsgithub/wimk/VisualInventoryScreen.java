package commdgriest.httpsgithub.wimk;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.content.Context;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleAdapter;
import android.view.ViewGroup;


public class VisualInventoryScreen extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnAddItem;
    Button btnBack;
    Button btnSearch;
    TextView itemID;
    Button btnShowAll;
    DatabaseDML db_dml = new DatabaseDML(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_inventory_screen);

        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        btnShowAll = (Button) findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(this);
    }

    /* OnClick Listeners */
    @Override
    public void onClick(View view) {
        /* Add Item */
        if (view == findViewById(R.id.btnAddItem)){
            Intent intent = new Intent(VisualInventoryScreen.this, UpdateItemPropertiesScreen.class);
            intent.putExtra("item_Id",0);
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

            //TODO need to query into DB with String query
        }

        else if(view == findViewById(R.id.btnShowAll)){

            ArrayList< HashMap<String, String> > itemList = db_dml.getItemsList();

            /* If there are items to display */
            if(itemList.size() != 0){
                Toast.makeText(this, itemList.size() + " items in inventory", Toast.LENGTH_SHORT).show();
//                /* Locate the listVeiw */
//                ListView lv = (ListView) findViewById(R.id.vi_listView);
//                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        itemID = (TextView) findViewById(R.id.itemID);
//                        String itemIDstring = itemID.getText().toString();
//                        Intent objIntent = new Intent(getApplicationContext(), UpdateItemPropertiesScreen.class);
//                        objIntent.putExtra("itemID", Integer.parseInt(itemIDstring));
//                        startActivity(objIntent);
//                    }
//                });
//                ListAdapter adapter = new SimpleAdapter(VisualInventoryScreen.this, itemList,
//                        R.layout.view_item_entry, new String[]{"id", "name"},
//                        new int[]{R.id.itemID, R.id.itemName});
//                lv.setAdapter(adapter);
            }
            /* If the inventory is empty */
            else{
                Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
