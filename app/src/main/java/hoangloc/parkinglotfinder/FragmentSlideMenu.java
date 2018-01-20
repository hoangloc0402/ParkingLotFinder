package hoangloc.parkinglotfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

        return myView;
    }
}