package com.shikhar.helpme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class WorkerSignUp extends ActionBarActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_sign_up);
        spinner = (Spinner) findViewById(R.id.profession);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.profession ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onClick(View v)
    {
        EditText password=(EditText)findViewById(R.id.password);
        EditText confirm_password=(EditText)findViewById(R.id.confirmpassword);
        if(password.getText().toString()!=confirm_password.getText().toString())
        {
            Toast.makeText(this,"PAssword not same", Toast.LENGTH_LONG).show();
        }
        EditText name=(EditText)findViewById(R.id.name);
        EditText username=(EditText)findViewById(R.id.username);
        EditText age=(EditText)findViewById(R.id.age);
        EditText address=(EditText)findViewById(R.id.address);
        EditText phone_no=(EditText)findViewById(R.id.phone_no);
        EditText charges=(EditText)findViewById(R.id.charges);
        RadioButton male=(RadioButton)findViewById(R.id.male);
        boolean ismale=male.isChecked();
        String gender="Female";
        if(ismale)
            gender="Male";
        String prof = spinner.getSelectedItem().toString();
        HelpSQLiteOpenHelper helper=new HelpSQLiteOpenHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(HelpContract.WORKER_TABLE_NAME_COL,name.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_ADDRESS_COL,address.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_USERNAME_COL,username.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_AGE_COL,age.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_PHONE_NO_COL,phone_no.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_CHARGES_HOUR_COL,charges.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_GENDER_COL,gender);
        cv.put(HelpContract.WORKER_TABLE_PROFESSION_COL,prof);
        db.insert(HelpContract.WORKER_TABLE,null,cv);
        Intent data = new Intent();
        data.putExtra("username", username.getText().toString());
        setResult(1, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_worker_sign_up, menu);
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
