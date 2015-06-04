package bajomoj.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends Activity {
    ArrayList<listData> myListdata = new ArrayList<listData>();
    String FILENAME = "GPS_reminder_sort_vo21";
    static final int EXPECTED_CODE = 1;
    static boolean readFromFileCheck = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerClickCallBack();

        Button addLayout = (Button) findViewById(R.id.addButton);
        addLayout.setOnClickListener(addLayoutHandler);

        readFromFileCheck = true;


            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream = null;
            try {
                Integer size;
                fileInputStream = getApplicationContext().openFileInput(FILENAME);
                objectInputStream = new ObjectInputStream(fileInputStream);
                size = (int) objectInputStream.readInt();
                for (int counter = 0; counter < size; counter++) {
                    myListdata.add((listData) objectInputStream.readObject());
                }
                objectInputStream.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (StreamCorruptedException e1) {
                e1.printStackTrace();
            } catch (NotSerializableException e1) {
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

    }

    /*@Override
    protected void onStart() {
        super.onStart();

    }*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1) {
            if (resultCode == RESULT_OK) {
                Bundle b = data.getExtras();
                /*myParcelable object = b.getParcelable("parcel");  KAKO DOBITI CIJELU LISTU
                myListdata = object.getMyListdata();*/
                listData object = b.getParcelable("parcel");
                myListdata.add(object);
                readFromFileCheck = false;
                Toast.makeText(getApplicationContext(), "onactivityresult", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        populateListView();
    }

    /*@Override
    protected void onStop() {

    }*/

    @Override
    protected void onPause() {
        super.onPause();

        FileOutputStream outStream;
        ObjectOutputStream objectOutputStream = null;
        try {
                outStream = getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                objectOutputStream = new ObjectOutputStream(outStream);
                objectOutputStream.writeInt(myListdata.size());
                objectOutputStream.flush();
                for (listData oneData : myListdata) {
                    objectOutputStream.writeObject(oneData);
                    objectOutputStream.flush();
                }
                objectOutputStream.flush();
                objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (objectOutputStream!=null)
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    /*listData.DepArr choice1 = listData.DepArr.Arrival;
                        listData.DepArr choice2 = listData.DepArr.Departure;*/

        /*private void populatelistData() {
            myListdata.add(new listData(true, "Vukovarska 58", "Predati izvješće", choice1, 5, 100));
            myListdata.add(new listData(false,"Krešimirova 18", "Kupiti  pokaznu", choice1, 10, 30));
            myListdata.add(new listData(true, "Bartola Kašića 5/4", "Vratiti  knjigu", choice2, 1, 60));
        } INITAL DATA TEST */

        private void populateListView() {
            Collections.sort(myListdata, new listComparator());
            ArrayAdapter<listData> adapter = new MyListAdapter();

            ListView list = (ListView) findViewById(R.id.reminderListView);
            list.setAdapter(adapter);
        }



        private void registerClickCallBack() {
            ListView list = (ListView) findViewById(R.id.reminderListView);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listData clickedItem = myListdata.get(position);

                    if (view.getId() == R.id.checkBox) {
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

                        clickedItem.setActive(!clickedItem.getActive());
                        populateListView();
                    }

                   //CHECKBOX DIO KOJI TREBA DOVRSITI
                   /*if (view.getId() == R.id.checkBox) {
                        Toast.makeText(getApplicationContext(), clickedItem.getActive().toString(), Toast.LENGTH_SHORT).show();
                        if (clickedItem.getActive()) {
                            clickedItem.setActive(false);
                        } else {
                            clickedItem.setActive(true);
                        }

                        populateListView();
                    }*/

                }
            });
        }



        private  class MyListAdapter extends ArrayAdapter {
            public MyListAdapter() {
                super(MainActivity.this, R.layout.item_view, myListdata);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = convertView;
                //make sure we have a view to work with
                if (itemView == null) {
                    itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
                }
                //find the item to work with
                 listData currentData = myListdata.get(position);

                //fill the view

                CheckBox activeCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
                activeCheckBox.setChecked(currentData.getActive());
                //activeCheckBox.setOnClickListener((OnClickListener) this); CHEKBOX IO KOJI TREBA DOVRSIIT


               /* if (currentData.getDepArr() == 0) {
                    Toast.makeText(getApplicationContext(),"Departure", Toast.LENGTH_SHORT).show();
                } else if (currentData.getDepArr()== 1) {
                    Toast.makeText(getApplicationContext(),"Arrival", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"ne", Toast.LENGTH_SHORT).show();
                }*/

                    //Toast.makeText(getApplicationContext(),currentData.getRepeatInterval().toString(), Toast.LENGTH_SHORT).show();



                TextView locationText = (TextView) itemView.findViewById(R.id.item_Location);
                locationText.setText(currentData.getLocation());

                TextView descriptionText = (TextView) itemView.findViewById(R.id.item_Description);
                descriptionText.setText(currentData.getDescription());


                return itemView;
            }
        }


        OnClickListener addLayoutHandler = new OnClickListener() {
            public void onClick(View v) {
                //ZA POSLATI CIJELU LISTU
               /* myParcelable object = new myParcelable();
                object.setMyListdata(myListdata);

                Intent addLayoutIntent = new Intent(MainActivity.this, AddActivity.class);
                addLayoutIntent.putExtra("parcel", object);

                startActivityForResult(addLayoutIntent, EXPECTED_CODE);*/

                Intent addLayoutIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(addLayoutIntent, EXPECTED_CODE);

            }
        };








    public Boolean deleteItem(Integer index) {
        try {
            myListdata.remove(index);
        }
        catch(Exception e) {
            return false;
        }
        Collections.sort(myListdata, new listComparator());
        return true;
    };


}

class listComparator implements Comparator<listData> {


    @Override
    public int compare(listData lhs, listData rhs) {
        int activeCompare = lhs.getActive().compareTo(rhs.getActive());
        if (activeCompare > 0) {
            return  -activeCompare;
        } else if (activeCompare == 0) {
            return lhs.getDateTime().compareTo(rhs.getDateTime());
        }
        else return 0;
    };



    }

