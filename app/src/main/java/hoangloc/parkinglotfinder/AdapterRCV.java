package hoangloc.parkinglotfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

public class AdapterRCV extends RecyclerView.Adapter<AdapterRCV.ViewHolder> {
    private Context context;
    private ArrayList<ParkingLotInfo> parkingLotInfoList;
    private View parentView;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView address, price, availableTime, remainingSlot;
        public View v;

        public ViewHolder(View view) {
            super(view);
            v = view;
            address = (TextView) view.findViewById(R.id.address);
            price = (TextView) view.findViewById(R.id.prices);
            availableTime = (TextView) view.findViewById(R.id.available);
            remainingSlot = (TextView) view.findViewById(R.id.remainingSlot);

        }
    }

    public AdapterRCV(ArrayList<ParkingLotInfo> InfoList, Context con, View v) {
        this.parkingLotInfoList = InfoList;
        context = con;
        parentView = v;
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

        //final SlidingUpPanelLayout slidingUpPanelLayout = (SlidingUpPanelLayout) parentView.findViewById(R.id.sliding_layout);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                Intent myIntent = new Intent(context, ActivityMain.class);
                //myIntent.putExtra("key", "haha"); //Optional parameters
                context.startActivity(myIntent);
            } });

    }

    @Override
    public int getItemCount() {
        return parkingLotInfoList.size();
    }
}



