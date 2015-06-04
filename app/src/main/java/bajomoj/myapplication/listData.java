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
    /*public String getRepeatIntervalToString() {
        return repeatInterval.toString();
    }*/
    public void  setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }


    public Integer getRadius() {
        return radius;
    }
    /*public String getRadiusToString() {
        return radius.toString();
    }*/
    public void setRadius() {
        this.radius = radius;
    }



     //parcelable part

    public listData(Parcel read) {
       /* String [] data = new String[6];
        in.readStringArray(data);

        this.active = Boolean.valueOf(data[0]);
        this.location = data[1];
        this.description = data[2];
        this.depArr = DepArr.valueOf(data[3]);
        this.repeatInterval = Integer.parseInt(data[4]);
        this.radius = Integer.parseInt(data[5]);*/


        this.active = read.readByte() != 0;
        this.location = read.readString();
        this.description = read.readString();
        /*if (read.readString() == "Departure") {
            this.depArr = DepArr.Departure;
        }else if (read.readString() == "Arrival") {
            this.depArr = DepArr.Arrival;
        } else {
            this.depArr = DepArr.gay;
        }*/
        this.choose = read.readInt();
        this.dateTime = read.readString();
        /*this.repeatInterval = Integer.parseInt(read.readString());//parse radi vracanja u primitivni int
        this.radius = Integer.parseInt(read.readString());//parse radi vracanja u primitivni int*/
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
        /*dest.writeString(getRepeatIntervalToString());
        dest.writeString(getRadiusToString());*/
        dest.writeInt(repeatInterval);
        dest.writeInt(radius);





    }

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.active.toString(),
                this.location,
                this.descrption,
                this.depArr.toString(),
                String.valueOf(this.repeatInterval),
                String.valueOf(this.radius)
        });

    }*/

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

