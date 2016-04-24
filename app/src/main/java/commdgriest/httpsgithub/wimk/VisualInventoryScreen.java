package commdgriest.httpsgithub.wimk;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Context;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleAdapter;
import android.view.ViewGroup;


public class VisualInventoryScreen extends MainMenuScreen  implements android.view.View.OnClickListener{
//public class VisualInventoryScreen extends MainMenuScreen {

    Button btnAddItem;
    Button btnBack;
    Button btnSearch;
    TextView itemID;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAddItem)){
            Intent intent = new Intent(VisualInventoryScreen.this, UpdateItemPropertiesScreen.class);
            intent.putExtra("item_Id",0);
            startActivity(intent);
        }
        else if(view == findViewById(R.id.btnBack)){
            startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
        }
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
    }

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

//        DatabaseDML db = new DatabaseDML(this);

//        ArrayList<HashMap<String, String>> itemsList = db.getItemsList();
//        if (itemsList.size() != 0) {
//            ListView vi_listView = (ListView) findViewById(R.id.vi_listView);
//            vi_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    //TODO make first TextView id in xml should be item_ID
//
//                    /* Determine which item from inventory was clicked */
//                    itemID = (TextView) view.findViewById(R.id.itemID);
//                    String item_Identification = itemID.getText().toString();
//
//                    /* For now, open the Update Item Properties screen for the selected item */
//                    Intent objIndent = new Intent(getApplicationContext(), UpdateItemPropertiesScreen.class);
//                    objIndent.putExtra("itemView", Integer.parseInt(item_Identification));
//                    startActivity(objIndent);
//                }
//            });
//            //TODO make xml view_item_entry for ListView
//            /* view_item_entry is XML class, item_ID is first text view id, item_name is the 2nd text view ID */
//            ListAdapter adapter = new SimpleAdapter(VisualInventoryScreen.this, itemsList,
//                    R.layout.view_item_entry, new String[]{"id", "name"},
//                    new int[]{R.id.itemID, R.id.itemName});
//            setListAdapter(adapter);
//        }
//        else {
//            Toast.makeText(this, "No items!", Toast.LENGTH_SHORT).show();
//        }
    }
}
