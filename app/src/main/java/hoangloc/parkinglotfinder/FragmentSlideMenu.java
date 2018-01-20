package hoangloc.parkinglotfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

public class FragmentSlideMenu extends Fragment {

    private View myView;
    
    public FragmentSlideMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_slide_menu, container, false);
        RecyclerView RCV = (RecyclerView) myView.findViewById(R.id.rv);
        AdapterRCV arcv = new AdapterRCV(ActivityMain.listOfParkingLotInfo, getContext(), myView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RCV.setLayoutManager(mLayoutManager);
        RCV.setItemAnimator(new DefaultItemAnimator());
        RCV.setAdapter(arcv);

        return myView;
    }
}