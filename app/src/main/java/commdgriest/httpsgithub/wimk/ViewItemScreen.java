package commdgriest.httpsgithub.wimk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewItemScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_screen);

           /* When Back button is clicked, go back to Visual Inventory activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewItemScreen.this, VisualInventoryScreen.class));
                //display all items
                VisualInventoryScreen.showAllItems();
            }
        });

         /* When Edit button is clicked, go back to Update Item Properties activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewItemScreen.this, UpdateItemPropertiesScreen.class));
            }
        });


        //This sets up the alert to user to confirm they want to delete an item
        final AlertDialog.Builder delete = new AlertDialog.Builder(this);
        delete.setTitle("Delete Entry");
        delete.setMessage("Are you sure you want to delete this item?");
        delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // whatever item should be deleted
               // Inventory.deleteItem();
                //go back to visual inventory screen
                startActivity(new Intent(ViewItemScreen.this, VisualInventoryScreen.class));
            }

        });
        delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                //do nothing, stay on View Item screen
            }
        });

        /* When Delete button is clicked, need to verify  */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete.create();
            }
        });


    }

}
