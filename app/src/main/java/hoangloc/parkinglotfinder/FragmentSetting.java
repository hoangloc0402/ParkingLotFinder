package hoangloc.parkinglotfinder;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentSetting extends Fragment {

    private View myView;
    private Spinner spinnerLang, spinnerTheme;
    public FragmentSetting() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_setting, container, false);
        int themeColor = ActivityMain.sharedPref.getInt("currentColor",R.color.colorPrimary);
        TextView chooselang = (TextView)myView.findViewById(R.id.chooselang);
        chooselang.setTextColor(getResources().getColor(themeColor));
        //TextView textTheme = (TextView)myView.findViewById(R.id.textTheme);
        //textTheme.setTextColor(getResources().getColor(themeColor));

        int flags[] = {R.drawable.flag_uk, R.drawable.flag_vn};
        String[] languageNames= {"English", "Tiếng Việt"};
        spinnerLang = (Spinner)myView.findViewById(R.id.spinnerLanguage);
        spinnerLang.setPrompt(getString(R.string.selectlanguage));
        AdapterSpinner customAdapter = new AdapterSpinner(getContext(),flags,languageNames);
        spinnerLang.setAdapter(customAdapter);
        spinnerLang.setSelection(ActivityMain.currentLang);
        spinnerLang.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0 && ActivityMain.currentLang != 0 ) {
                    Toast.makeText(parent.getContext(), "You have selected English", Toast.LENGTH_SHORT).show();
                    ActivityMain.prefEditor.putInt("currentLang", 0);
                    ActivityMain.prefEditor.commit();
                    refresh();

                } else if (pos == 1 && ActivityMain.currentLang != 1) {
                    Toast.makeText(parent.getContext(), "Bạn đã chọn Tiếng Việt", Toast.LENGTH_SHORT).show();
                    ActivityMain.prefEditor.putInt("currentLang", 1);
                    ActivityMain.prefEditor.commit();
                    refresh();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        final ConstraintLayout languageLayout = (ConstraintLayout)myView.findViewById(R.id.languageLayout);
        languageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageLayout.getChildAt(0).setClickable(true);
                languageLayout.getChildAt(0).performClick();
                languageLayout.getChildAt(0).setClickable(false);
            }
        });

/*
        final List<MyTheme> themes = new ArrayList<>();
        themes.add(new MyTheme(R.style.AppTheme,getString(R.string.them1),
                R.color.colorPrimary,R.drawable.custom_ripple_blue,R.drawable.header));
        themes.add(new MyTheme(R.style.AppTheme_Orange,getString(R.string.theme2),
                R.color.colorPrimary3,R.drawable.custom_ripple_orange,R.drawable.header3));
        themes.add(new MyTheme(R.style.AppTheme_Green,getString(R.string.theme3),
                R.color.colorPrimary4,R.drawable.custom_ripple_green,R.drawable.header4));
        themes.add(new MyTheme(R.style.AppTheme_Midnight,getString(R.string.them4),
                R.color.colorPrimary5,R.drawable.custom_ripple_midnight,R.drawable.header5));

        spinnerTheme = (Spinner)myView.findViewById(R.id.spinnerChangeTheme);
        spinnerTheme.setPrompt(getString(R.string.selecttheme));
        AdapterSpinner customAdapter3 = new AdapterSpinner(getContext(),themes);
        spinnerTheme.setAdapter(customAdapter3);
        for(int i=0;i<themes.size();i++){
            if(ActivityMain.currentTheme == themes.get(i).id){
                spinnerTheme.setSelection(i);
                break;
            }
        }
        spinnerTheme.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(id != ActivityMain.currentTheme){
                    ActivityMain.prefEditor.putInt("currentTheme", (int)id);
                    ActivityMain.prefEditor.putInt("currentColor",themes.get(pos).color);
                    ActivityMain.prefEditor.putInt("currentButton",themes.get(pos).button);
                    ActivityMain.prefEditor.putInt("currentHeader",themes.get(pos).header);
                    ActivityMain.prefEditor.commit();
                    refresh();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        final ConstraintLayout themeLayout = (ConstraintLayout)myView.findViewById(R.id.themeLayout);
        themeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeLayout.getChildAt(0).setClickable(true);
                themeLayout.getChildAt(0).performClick();
                themeLayout.getChildAt(0).setClickable(false);
            }
        });
*/

        return myView;
    }

    public void refresh(){
        Intent refresh = new Intent(this.getActivity(), ActivityMain.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(refresh);
    }

}
