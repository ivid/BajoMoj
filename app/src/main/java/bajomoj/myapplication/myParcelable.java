package bajomoj.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matija077 on 5/20/2015.
 */
public class myParcelable implements Parcelable {

    //private List<listData> myListdata = new ArrayList<listData>();
    private ArrayList<listData> myListdata;

    myParcelable() {
        myListdata = new ArrayList<listData>();
    }

    public ArrayList<listData> getMyListdata() {
        return myListdata;
    }

    public void setMyListdata(ArrayList<listData> myListdata) {
        this.myListdata = myListdata;
    }




    public myParcelable(Parcel in) {
        this();
        //myListdata = new ArrayList<listData>();
        in.readTypedList(myListdata, listData.CREATOR);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeInt(myListdata.size());
        dest.writeTypedList(myListdata);
    }


    public static final Parcelable.Creator<myParcelable> CREATOR = new Parcelable.Creator<myParcelable>() {
        @Override
        public myParcelable createFromParcel(Parcel in) {
            return new myParcelable(in);
        }

        @Override
        public myParcelable[] newArray(int size) {
            return new myParcelable[size];
        }
    };


}

