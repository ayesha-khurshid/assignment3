package com.example.assignment3;


import java.util.ArrayList;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.ContentProviderOperation;
import android.widget.Toast;
public class home extends Activity {
    ArrayList<String> alist=new ArrayList<String>();
    ListView lv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id =
                        cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name =
                        cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        alist.add("Name: " + name + "\nPhone No: " +
                                phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        lv=(ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> adt=new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alist);
        lv.setAdapter(adt);
    }

}