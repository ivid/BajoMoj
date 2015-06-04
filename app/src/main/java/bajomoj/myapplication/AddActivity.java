package bajomoj.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddActivity extends Activity {

    private Boolean active = false;
    private String location = "swag";
    private String description;
    public enum DepArr {Departure, Arrival}
    DepArr depArr;
    Integer choose;
    String dateTime = "01/23/1990 12:12:12";
    private int  repeatInterval = 40; //samo za probu
    private int radius = 100; //samo za probu
    ArrayList<listData> myListdata = new ArrayList<listData>();
    Spinner choseDepArr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent addLayoutIntent = getIntent();
        setContentView(R.layout.add_item_view);

        Bundle b = getIntent().getExtras();
        /*myParcelable object = b.getParcelable("parcel");
        myListdata = object.getMyListdata();*/


        CheckBox addCheckBox = (CheckBox) findViewById(R.id.activeCheckBox);
        addCheckBox.setOnCheckedChangeListener(addCheckBoxHandler);

        EditText addDescription = (EditText) findViewById(R.id.addDescription);
        addDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                description = s.toString();

            }
        });



        choseDepArr = (Spinner) findViewById(R.id.choseDepArr);
        choseDepArr.setAdapter(new ArrayAdapter<DepArr>(this,
                R.layout.support_simple_spinner_dropdown_item, depArr.values()));
        choseDepArr.setOnItemSelectedListener(choseDepArrHanlder);





        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(allButtonsHandler);

        Button discardButton = (Button) findViewById(R.id.discardButton);
        discardButton.setOnClickListener(allButtonsHandler);

        Button locationButton = (Button) findViewById(R.id.locationButton);
        locationButton.setOnClickListener(allButtonsHandler);

    }

    CompoundButton.OnCheckedChangeListener addCheckBoxHandler = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                active = true;
            } else {
                active = false;
            }
        }
    };


    AdapterView.OnItemSelectedListener choseDepArrHanlder = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            if (parent.getItemAtPosition(position).toString() == "Departure") {
                choose = 0;
                //Toast.makeText(getApplicationContext(), "Departure prvi", Toast.LENGTH_SHORT).show();
            } else if (parent.getItemAtPosition(position).toString() == "Arrival") {
               choose = 1;
                //Toast.makeText(getApplicationContext(), "Arrival drugi", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() +"treci", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener allButtonsHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveButton: {
                    /*myListdata.add(new listData(active, location, description, choose, repeatInterval, radius));

                    myParcelable object = new myParcelable();
                    object.setMyListdata(myListdata);
                    Intent mainActivityIntent = new Intent(AddActivity.this, MainActivity.class);
                    /*mainActivityIntent.putExtra("list", new listData
                            (active,location,descrption,choice1,repeatInterval,radius ));*/
                    /*mainActivityIntent.putExtra("parcel", object);
                    setResult(RESULT_OK,mainActivityIntent);
                    finish();*/

                    listData object = new listData(active, location, description, choose,dateTime, repeatInterval, radius);
                    Intent mainActivityIntent = new Intent(AddActivity.this, MainActivity.class);
                    mainActivityIntent.putExtra("parcel", (android.os.Parcelable) object);
                    setResult(RESULT_OK, mainActivityIntent);
                    finish();

                }
                case R.id.discardButton: {


                }

                case R.id.locationButton: {

                }
            }
        }
    };




}
