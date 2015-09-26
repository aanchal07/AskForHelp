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
    EditText password;
    EditText confirm_password;
    EditText name;
    EditText username;
    EditText age;
    EditText address;
    EditText phone_no;
    EditText charges;
    boolean ismale;
    String gender;
    RadioButton male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_sign_up);
        spinner = (Spinner) findViewById(R.id.profession);
        password=(EditText)findViewById(R.id.password);
        confirm_password=(EditText)findViewById(R.id.confirmpassword);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        age=(EditText)findViewById(R.id.age);
        address=(EditText)findViewById(R.id.address);
        phone_no=(EditText)findViewById(R.id.phone_no);
        charges=(EditText)findViewById(R.id.charges);
        male=(RadioButton)findViewById(R.id.male);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.profession ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onClick(View v)
    {
        ismale=male.isChecked();
        gender="Female";
        if(ismale)
            gender="Male";
        if(!password.getText().toString().equals(confirm_password.getText().toString()))
        {

            Toast.makeText(this,"Password not same", Toast.LENGTH_LONG).show();
        }


        String prof = spinner.getSelectedItem().toString();
        HelpSQLiteOpenHelper helper=new HelpSQLiteOpenHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HelpContract.WORKER_TABLE_NAME_COL, name.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_USERNAME_COL, username.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_PASSWORD_COL, password.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_PHONE_NO_COL, phone_no.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_ADDRESS_COL, address.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_CHARGES_HOUR_COL, charges.getText().toString());
        cv.put(HelpContract.WORKER_TABLE_AGE_COL, age.getText().toString());
        db.insert(HelpContract.WORKER_TABLE, null, cv);
        Intent data = new Intent();
        data.putExtra("username", username.getText().toString());
        setResult(1, data);
        finish();

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
