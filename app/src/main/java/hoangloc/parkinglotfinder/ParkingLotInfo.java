package hoangloc.parkinglotfinder;

import android.content.Context;
import android.content.res.Resources;
import hoangloc.parkinglotfinder.R;
/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

public class ParkingLotInfo {
    private Context context;
    private String addressNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String price;
    private String availableTime;
    private String remainingSlot;
    private String longitude;
    private String latitude;

    ParkingLotInfo(Context cont, String addr, String st, String w, String dist, String c, String p, String aTime, String rSlot, String lo, String la) {
        context = cont;
        addressNumber = addr;
        street = st;
        ward = w;
        district = dist;
        city = c;
        price = p;
        availableTime = aTime;
        remainingSlot = rSlot;
        longitude = lo;
        latitude = la;
    }

    String getAddress(){
        return addressNumber + " "
                + street + " " + context.getString(R.string.street)
                + ", " + context.getString(R.string.ward) + ward
                + ", " + context.getString(R.string.district) + district
                + ", " + context.getString(R.string.city);
}
    String getPrice(){
        return price;
    }
    String getAvailableTime(){
        return availableTime;
    }
    String getRemainingSlot(){
        return remainingSlot;
    }

}
