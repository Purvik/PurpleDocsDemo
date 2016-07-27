package com.purvik.purpledocsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ListView list;
    EditText edtSearch;
    ContactPersonAdapter adapter;
    ArrayList<ContactPerson> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nameList = new ArrayList<ContactPerson>();

        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998970653","Purvik"));


        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998970653","Purvik"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998974444","Rohan"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998972222","Vikash"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998971111","Pratik"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998975555","Jeyur"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998976666","Neha"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998977777","Minal"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998978888","Krunal"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998979999","Jaimin"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998971000","Krutik"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998971100","Raghu"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998971110","Raghav"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998972000","Prithvi"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998972200","Nidhi"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998972220","Kamlesh"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998972233","Naina"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998970333","Nutan"));
        nameList.add(new ContactPerson(R.mipmap.ic_launcher, "9998973300","Purvi"));


        list = (ListView) findViewById(R.id.list);
        edtSearch = (EditText) findViewById(R.id.edtSearch);

        adapter = new ContactPersonAdapter(this, R.layout.single_item, nameList);
        list.setAdapter(adapter);

        list.setTextFilterEnabled(true);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {

                Log.d("TextWatcher", "afterTextChanged: "+ cs.toString());
                MainActivity.this.adapter.getFilter().filter(cs.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
              //  Log.d("TextWatcher", "afterTextChanged: "+ s.toString());
                //MainActivity.this.adapter.getFilter().filter(s);
            }
        });


    }
}
