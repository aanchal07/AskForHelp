package com.shikhar.helpme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserProfileEdit extends ActionBarActivity {

    EditText name;
    EditText user;
    EditText pass;
    EditText phone;
    String uname;
    EditText address;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);
        name = (EditText) findViewById(R.id.editname);
        pass = (EditText) findViewById(R.id.editnewpassword);
        phone = (EditText) findViewById(R.id.editphone);
        user = (EditText) findViewById(R.id.editnewusername);
        address = (EditText) findViewById(R.id.address);
        uname=user.getText().toString();

        Intent i= getIntent();
        Bundle b=i.getExtras();
        phone.setText(b.getString("phone"));
        user.setText(b.getString("username"));
        name.setText(b.getString("name"));
        address.setText(b.getString("address"));
    }

    public void onClickChanges(View v){
        HelpSQLiteOpenHelper helper=new HelpSQLiteOpenHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HelpContract.USER_TABLE_NAME_COL, name.getText().toString());
        cv.put(HelpContract.USER_TABLE_USERNAME_COL, user.getText().toString());
        cv.put(HelpContract.USER_TABLE_PASSWORD_COL, pass.getText().toString());
        cv.put(HelpContract.USER_TABLE_PHONE_NO_COL, phone.getText().toString());
        String where=HelpContract.USER_TABLE_USERNAME_COL + "=" + "'" + uname + "'";
        db.update(HelpContract.USER_TABLE,cv,where,null);
        Intent data = new Intent();
        data.putExtra("username", user.getText().toString());
        setResult(1, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
