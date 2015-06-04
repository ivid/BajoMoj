package bajomoj.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Matija077 on 5/15/2015.
 */
public class listData implements Parcelable, Serializable {
    private Boolean active;
    private String location;
    private String description;
    private Integer choose;
    private String dateTime;
    private Integer  repeatInterval;
    private Integer radius;




    public listData(Boolean active, String location, String description, Integer choose,String dateTime,  Integer repeatInterval, Integer radius ) {
        super();
        this.active = active;
        this.location = location;
        this.description = description;
        this.choose = choose;
        this.dateTime = dateTime;
        this.repeatInterval = repeatInterval;
        this.radius = radius;
    }



    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getLocation() {
        return location;
    }
    public void   setLocation(String location) { this.location = location; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    public Integer getDepArr() {
        return choose;
    }
    public void setDepArr(Integer choose) { this.choose = choose; }

    public Date getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = null;

        try {
            date = (Date) dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }
    public void setDateTime(String dateTime) { this.dateTime = dateTime;}


    public  Integer getRepeatInterval(Integer repeatInterval) {
        return  repeatInterval;
    }
    public void  setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }


    public Integer getRadius() {
        return radius;
    }
    public void setRadius() {
        this.radius = radius;
    }



     //parcelable part

    public listData(Parcel read) {

        this.active = read.readByte() != 0;
        this.location = read.readString();
        this.description = read.readString();
        this.choose = read.readInt();
        this.dateTime = read.readString();
        this.repeatInterval = read.readInt();
        this.radius = read.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(location);
        dest.writeString(description);
        dest.writeInt(choose);
        dest.writeString(dateTime);
        dest.writeInt(repeatInterval);
        dest.writeInt(radius);





    }


    public static  final Parcelable.Creator<listData> CREATOR = new Parcelable.Creator<listData>() {

        @Override
        public listData createFromParcel(Parcel source) {
            return new listData(source);
        }

        @Override
        public listData[] newArray(int size) {
            return new listData[size];
        }
    };





}

