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


public class VisualInventoryScreen extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAddItem;
    Button btnBack;
    Button btnSearch;
    TextView itemView;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAddItem)){

            Intent intent = new Intent(this,UpdateItemPropertiesScreen.class);
            intent.putExtra("item_Id",0);
            startActivity(intent);

        }
        else if(view== findViewById(R.id.btnBack)){
            startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));

        } else if(view== findViewById(R.id.btnSearch)){
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

        DatabaseDML db = new DatabaseDML(this);

        ArrayList<HashMap<String, String>> itemsList = db.getItemsList();
        if (itemsList.size() != 0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO make first TextView id in xml should be item_ID
                    /*
                    itemView = (TextView) view.findViewById(R.id.item_ID);
                    String item_Identification = itemView.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), UpdateItemPropertiesScreen.class);
                    objIndent.putExtra("itemView", Integer.parseInt(item_Identification));
                    startActivity(objIndent);*/
                }
            });
            //TODO make xml view_item_entry for ListView
            //view_item_entry is XML class, item_ID is first text view id, item_name is the 2nd text view ID
           // ListAdapter adapter = new SimpleAdapter(VisualInventoryScreen.this, itemsList, R.layout.view_item_entry, new String[]{"id", "name"}, new int[]{R.id.item_ID, R.id.item_name});
            //setListAdapter(adapter);
        } else {
            Toast.makeText(this, "No items!", Toast.LENGTH_SHORT).show();
        }




//        /* When Add Item button is clicked, launch Update Item Properties activity with default values */
//        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
//        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
//        btnAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                /* Notify the user that a new item has been added to inventory */
//                Toast itemAddedToast = Toast.makeText(getApplicationContext(), "New Item Added!",
//                        Toast.LENGTH_SHORT);
//                /* This toast should appear center screen */
//                itemAddedToast.setGravity(Gravity.CENTER, 0, 0);
//                itemAddedToast.show();
//
////                /* Add a new item to inventory */
////
////                //DatabaseDML db = new DatabaseDML(this);
////
////
////                Item item = new Item();
////                item.name = "itemNAME";
////                item.quantity = 0;
////                item.iconId = 1;
////                //item.id = id;
////                item.shouldShow = true;
////                item.color = 0;
////
////                db.insert(item);
////
//
//
//
//                //TODO we should NOT be creating new inventories! The user has exactly ONE.
//                //Inventory newInventory = new Inventory();
//                //newInventory.addItem();
//                startActivity(new Intent(VisualInventoryScreen.this, UpdateItemPropertiesScreen.class));
//            }
//        });
//
//        /* When Search button is clicked, need to be able to launch keyboard to type*/
//        Button btnSearch = (Button) findViewById(R.id.btnSearch);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText searchText = (EditText) findViewById(R.id.searchText);
//                /* Make the textView visible so the user can enter a query */
//                searchText.setVisibility(View.VISIBLE);
//                /* Shift focus to the textView to avoid making the user manually click on it */
//                searchText.requestFocus();
//                /* Launch the keyboard for user input */
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(searchText, InputMethodManager.SHOW_IMPLICIT);
//
//                //get query from search bar
//                String query = searchText.getText().toString();
//                //pass query to searchByName method in inventory class
//                Inventory in = new Inventory();
//                in.searchByName(query);
//            }
//        });
//
//        /* When Back button is clicked, launch Main Menu activity */
//        Button btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(VisualInventoryScreen.this, MainMenuScreen.class));
//            }
//        });
//
//        /* TODO When An items icon is clicked, launch that items View Item Screen
//        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
//        Button btnViewItem = (Button) findViewById(R.id.btnViewItem);
//        btnViewItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(VisualInventoryScreen.this, ViewItemScreen.class));
//            }
//        }); */
//
//        /*
//        This method is to see if we want to use Grid View to show our items
//        see: http://developer.android.com/guide/topics/ui/layout/gridview.html
//            http://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
//            http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
//            http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
//         */
//
////        GridView gridview = (GridView) findViewById(R.id.gridview);
////        gridview.setAdapter(new ImageAdapter(this));
////
////        gridview.setOnItemClickListener(new OnItemClickListener() {
////            public void onItemClick(AdapterView<?> parent, View v,
////                                    int position, long id) {
////                Toast.makeText(VisualInventoryScreen.this, "" + position,
////                        Toast.LENGTH_SHORT).show();
////            }
////        });
//
//    }
//
//        //this method will be used to figure out which item to launch in the ViewItem screen
//        public void determineWhichItemWasClicked(){
//        }
//
//        //display items from search
//        public void showItemsFromSearch(){
//            Inventory newInventory = new Inventory();
//           for(int i = 0; i < newInventory.itemsList.size(); i++){
//               if(newInventory.itemsList.get(i).shouldShow == true){
//                   //DISPLAY
//               }
//               else{
//                   //DON'T DISPLAY
//               }
//           }

        }
}
