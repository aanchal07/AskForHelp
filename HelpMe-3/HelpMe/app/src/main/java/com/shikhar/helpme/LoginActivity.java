package com.shikhar.helpme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button signIn;
    Button signUp;
    Button workerSignUp;
    Button workerSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp = getSharedPreferences("loginID", Context.MODE_PRIVATE);
        sp.getString("loginID", null);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signin);
        signIn.setOnClickListener(this);
        signUp = (Button) findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        workerSignIn = (Button) findViewById(R.id.helpersignin);
        workerSignIn.setOnClickListener(this);
        workerSignUp = (Button) findViewById(R.id.signupasworker);
        workerSignUp.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signin)    {
            String user = null;
            String pass = null;
            if(username.getText() != null && password.getText() != null) {
                user = username.getText().toString();
                pass = password.getText().toString();
            }
            if(user == null || pass == null )   {
                Toast.makeText(this, " Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                return;
            }
            HelpSQLiteOpenHelper helper = new HelpSQLiteOpenHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
              Cursor  c = db.query(HelpContract.USER_TABLE, new String[]{HelpContract.USER_TABLE_ID, HelpContract.USER_TABLE_NAME_COL},

                        HelpContract.USER_TABLE_USERNAME_COL + " = " + "'" + user + "'"
                                + " and " + HelpContract.USER_TABLE_PASSWORD_COL + " = "
                                +"'" + pass + "'", null, null, null, null, null);





            if(!c.moveToFirst()) {
                Toast.makeText(this, " Invalid UserName/Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, ""+ c.getString(c.getColumnIndex(HelpContract.USER_TABLE_NAME_COL)), Toast.LENGTH_SHORT).show();

                SharedPreferences sp = getSharedPreferences("loginID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("loginID", c.getString(c.getColumnIndex(HelpContract.USER_TABLE_ID)));
                Log.i("c", c.getColumnIndex(HelpContract.USER_TABLE_ID)+"");
                editor.commit();
                Intent i = new Intent();
                i.setClass(this, MainActivity.class);
                startActivity(i);
            }
        }

        else if(v.getId() == R.id.helpersignin) {
            String user = null;
            String pass = null;
            if(username.getText() != null && password.getText() != null) {
                user = username.getText().toString();
                pass = password.getText().toString();
            }
            if(user == null || pass == null )   {
                Toast.makeText(this, " Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                return;
            }
            HelpSQLiteOpenHelper helper = new HelpSQLiteOpenHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor  c1 = db.query(HelpContract.WORKER_TABLE, new String[]{HelpContract.WORKER_TABLE_ID, HelpContract.WORKER_TABLE_NAME_COL},

                    HelpContract.WORKER_TABLE_USERNAME_COL + " = " + "'" + user + "'"
                            + " and " + HelpContract.WORKER_TABLE_PASSWORD_COL + " = "
                            +"'" + pass + "'", null, null, null, null, null);

           // String[] col={HelpContract.WORKER_TABLE_NAME_COL};
           // String where=HelpContract.WORKER_TABLE_USERNAME_COL +"=" + "'" + user+ "'"+" and "+ HelpContract.WORKER_TABLE_PASSWORD_COL+ " = " + "'"+ pass + "'";
             //c1= db.query(HelpContract.WORKER_TABLE,col,null,null,null,null,null,null);
            if(!c1.moveToFirst()) {
                Toast.makeText(this, " Invalid UserName/Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, ""+ c1.getString(c1.getColumnIndex(HelpContract.WORKER_TABLE_NAME_COL)), Toast.LENGTH_SHORT).show();

                SharedPreferences sp = getSharedPreferences("loginID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("loginID", c1.getString(c1.getColumnIndex(HelpContract.WORKER_TABLE_NAME_COL)));
                Log.i("c", c1.getColumnIndex(HelpContract.WORKER_TABLE_NAME_COL)+"");
                editor.commit();
                Intent i = new Intent();
                i.setClass(this, MainActivity.class);
                startActivity(i);
            }
  //          Cursor c = db.query(HelpContract.WORKER_TABLE, new String[]{HelpContract.WORKER_TABLE_ID},
//
//                    HelpContract.WORKER_TABLE_USERNAME_COL + " = " + "'" + user + "'"
//                            + " and " + HelpContract.WORKER_TABLE_PASSWORD_COL + " = "
//                            +"'" + pass + "'", null, null, null, null, null);

        }
        else if(v.getId() == R.id.signup)   {
            Intent i = new Intent();
            i.setClass(this, SignUpActivity.class);
            startActivityForResult(i, 1);


        }
        else
        {
            Intent i = new Intent();
            i.setClass(this, WorkerSignUp.class);
            startActivityForResult(i, 2);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        username.setText(b.getString("username"));

    }
}
