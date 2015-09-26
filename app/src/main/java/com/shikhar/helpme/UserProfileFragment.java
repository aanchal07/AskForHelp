package com.shikhar.helpme;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    TextView tname;
    TextView tusername;
    TextView tphone;
    TextView taddress;
    String name=null;
    String phone=null;
    String address=null;
    String username=null;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_user_profile, container, false);
        HelpSQLiteOpenHelper helper= new HelpSQLiteOpenHelper(getActivity());
        Bundle b=getArguments();

        if(b!=null)
        {
            username=b.getString("username");
        }
        SQLiteDatabase db=helper.getReadableDatabase();
        String where=HelpContract.USER_TABLE_USERNAME_COL + " = " +username;
        String[] col=new String[3];
        col[0]=HelpContract.USER_TABLE_NAME_COL;
        col[1]=HelpContract.USER_TABLE_PHONE_NO_COL;
        col[2]=HelpContract.USER_TABLE_ADDRESS_COL;
        Cursor c= db.query(HelpContract.USER_TABLE,col,where,null,null,null,null);

        if(c.moveToNext())
        {
            name=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_NAME_COL));
            phone=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_PHONE_NO_COL));
            address=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_ADDRESS_COL));
        }
        tname=(TextView)v.findViewById(R.id.name);
        tname.setText(name);
        tphone=(TextView)v.findViewById(R.id.phone);
        tname.setText(phone);
        taddress=(TextView)v.findViewById(R.id.address);
        tname.setText(address);
        tusername=(TextView)v.findViewById(R.id.username);
        tname.setText(username);
        return v;
    }

    public void onClickEdit(View v){
        Intent i=new Intent();
        i.putExtra("name", name);
        i.putExtra("username", username);
        i.putExtra("address", address);
        i.putExtra("phone", phone);
        i.setClass(getActivity(),UserProfileEdit.class);
        startActivityForResult(i, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==1)
        {
            HelpSQLiteOpenHelper helper= new HelpSQLiteOpenHelper(getActivity());
            Bundle b=data.getExtras();

            if(b!=null)
            {
                username=b.getString("username");
            }
            SQLiteDatabase db=helper.getReadableDatabase();
            String where=HelpContract.USER_TABLE_USERNAME_COL + " = " +username;
            String[] col=new String[3];
            col[0]=HelpContract.USER_TABLE_NAME_COL;
            col[1]=HelpContract.USER_TABLE_PHONE_NO_COL;
            col[2]=HelpContract.USER_TABLE_ADDRESS_COL;
            Cursor c= db.query(HelpContract.USER_TABLE,col,where,null,null,null,null);

            if(c.moveToNext())
            {
                name=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_NAME_COL));
                phone=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_PHONE_NO_COL));
                address=c.getString(c.getColumnIndex(HelpContract.USER_TABLE_ADDRESS_COL));
            }
            tname.setText(name);
            tname.setText(phone);
            tname.setText(address);
            tname.setText(username);

        }
    }
}
