package hoangloc.parkinglotfinder;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentAbout extends Fragment {

    private View myView;
    ImageButton butFB, butGG, butGH, butEM;

    public FragmentAbout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_about, container, false);
        butFB = (ImageButton) myView.findViewById(R.id.buttonfacebook);
        butGG = (ImageButton) myView.findViewById(R.id.buttongoogleplus);
        butGH = (ImageButton) myView.findViewById(R.id.buttongithub);
        butEM = (ImageButton) myView.findViewById(R.id.buttonmail);
        int themeColor = ActivityMain.sharedPref.getInt("currentColor",R.color.colorPrimary);
        ConstraintLayout backlayout = (ConstraintLayout) myView.findViewById(R.id.back_layout);
        backlayout.setBackgroundResource(themeColor);
        butFB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://facebook.com.vn"));
                startActivity(intent);
            }
        });

        butGG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://google.com.vn"));
                startActivity(intent);
            }
        });

        butGH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/quocbao100/ShellWallet"));
                startActivity(intent);
            }
        });

        butEM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://mail.google.com.vn"));
                startActivity(intent);
            }
        });

        return myView;
    }
}
