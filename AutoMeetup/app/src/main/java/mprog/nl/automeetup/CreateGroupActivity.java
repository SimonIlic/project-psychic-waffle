package mprog.nl.automeetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreateGroupActivity extends AppCompatActivity {
    private MeetingGroup group;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ConfigureGroupActivity.class);
                intent.putExtra("Group", group);
                startActivity(intent);
                finish();
            }
        });

        // init a new group
        group = new MeetingGroup();
        setupListView();
    }

    /** handle add button click
     * adding the entered email address to the group **/
    public void onAddButtonClick(View view) {
        EditText emailET = (EditText) findViewById(R.id.emailET);
        String email = emailET.getText().toString();

        // validate input
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please provide a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (isDuplicateEmail(email)) {
            Toast.makeText(this, "You allready added this user", Toast.LENGTH_SHORT).show();
            return;
        }

        // add emailadress to group
        group.addMember(email);
        listAdapter.notifyDataSetChanged();

        // empty out edit text
        emailET.setText("");
    }

    /** set up listview and adapter **/
    public void setupListView(){
        // startup listview
        ListView memberListView = (ListView) findViewById(R.id.memberListView);
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, group.getEmailAddresses());
        memberListView.setAdapter(listAdapter);

        // Register ListView for context menu, implicitly defining item longclick listener
        registerForContextMenu(memberListView);
    }

    /** context menu for long clicks **/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    /** handle context menu logic **/
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteEmail(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /** delete an address from the group **/
    private void deleteEmail(int position) {
        group.removeMember(position);
        listAdapter.notifyDataSetChanged();
    }

    /** validates email address format
     * by: @AdamvandenHoven on stackoverflow (https://goo.gl/EIFTHX) **/
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.
                Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /** checks if a users email address is not already added to the group **/
    public final boolean isDuplicateEmail(String target) {
        return group.getEmailAddresses().contains(target);
    }
}
