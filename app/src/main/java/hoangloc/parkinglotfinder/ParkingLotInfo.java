package hoangloc.parkinglotfinder;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

public class ParkingLotInfo {
    private String addressNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String price;
    private String availableTime;
    private String remainingSlot;

    ParkingLotInfo(String addr, String st, String w, String dist, String c, String p, String aTime, String rSlot) {
        addressNumber = addr;
        street = st;
        ward = w;
        district = dist;
        city = c;
        price = p;
        availableTime = aTime;
        remainingSlot = rSlot;
    }

    String getAddress(){
        return addressNumber + " "
                + street + " " + R.string.street
                + ", " + R.string.ward + ward
                + ", " + R.string.district + district
                + ", " + R.string.city;
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
