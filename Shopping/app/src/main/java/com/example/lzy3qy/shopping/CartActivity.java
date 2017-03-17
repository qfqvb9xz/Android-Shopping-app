package com.example.lzy3qy.shopping;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lzy3qy.shopping.data.*;

public class CartActivity extends AppCompatActivity {

    private ProdListAdaptor mAdapter;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView waitlistRecyclerView;

        // Set local attributes to corresponding views
        waitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);

        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a DB helper (this will create the DB if run for the first time)
        ProdlistDbHelper dbHelper = new ProdlistDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        // Get all guest info from the database and save in a cursor
        Cursor cursor = getAllGuests();

        // Create an adapter for that cursor to display the data
        mAdapter = new ProdListAdaptor(this, cursor);

        // Link the adapter to the RecyclerView
        waitlistRecyclerView.setAdapter(mAdapter);

        // Create an item touch helper to handle swiping items off the list
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //do nothing, we only care about swiping
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //get the id of the item being swiped
                long id = (long) viewHolder.itemView.getTag();
                //remove from DB
                removeGuest(id);
                //update the list
                mAdapter.swapCursor(getAllGuests());
            }

            //attach the ItemTouchHelper to the waitlistRecyclerView
        }).attachToRecyclerView(waitlistRecyclerView);

        Intent intentThatStartedThisActivity = getIntent();

        mAdapter.swapCursor(getAllGuests());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when user clicks on the Add to waitlist button
    */
    public void addToWaitlist(String name, int num, String url) {

        // Add guest info to mDb
        addNewGuest(name, num, url);

        // Update the cursor in the adapter to trigger UI to display the new list
        mAdapter.swapCursor(getAllGuests());
    }



    /**
     * Query the mDb and get all guests from the waitlist table
     *
     * @return Cursor containing the list of guests
     */
    private Cursor getAllGuests() {
        return mDb.query(
                ProductListContract.ProdlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private long addNewGuest(String name, int partySize, String url) {
        ContentValues cv = new ContentValues();
        cv.put(ProductListContract.ProdlistEntry.COLUMN_PRODUCT_NAME, name);
        cv.put(ProductListContract.ProdlistEntry.COLUMN_PRODUCT_NUMBER, partySize);
        cv.put(ProductListContract.ProdlistEntry.COLUMN_URL, url);
        return mDb.insert(ProductListContract.ProdlistEntry.TABLE_NAME, null, cv);
    }

    private boolean removeGuest(long id) {
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return mDb.delete(ProductListContract.ProdlistEntry.TABLE_NAME, ProductListContract.ProdlistEntry._ID + "=" + id, null) > 0;
    }

}