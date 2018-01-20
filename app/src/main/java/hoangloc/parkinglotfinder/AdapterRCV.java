package hoangloc.parkinglotfinder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

public class AdapterRCV extends RecyclerView.Adapter<AdapterRCV.ViewHolder> {

    private ArrayList<ParkingLotInfo> parkingLotInfoList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView address, price, availableTime, remainingSlot;

        public ViewHolder(View view) {
            super(view);
            address = (TextView) view.findViewById(R.id.address);
            price = (TextView) view.findViewById(R.id.prices);
            availableTime = (TextView) view.findViewById(R.id.available);
            remainingSlot = (TextView) view.findViewById(R.id.remainingSlot);
        }
    }

    public AdapterRCV(ArrayList<ParkingLotInfo> InfoList) {
        this.parkingLotInfoList = InfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_view, parent, false);

        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParkingLotInfo Info = parkingLotInfoList.get(position);
        holder.address.setText(Info.getAddress());
        holder.price.setText(Info.getPrice());
        holder.availableTime.setText(Info.getAvailableTime());
        holder.remainingSlot.setText(Info.getRemainingSlot());

    }

    @Override
    public int getItemCount() {
        return parkingLotInfoList.size();
    }
}



