package hoangloc.parkinglotfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ActivityInputRequestInfo extends AppCompatActivity {
    RadioButton raCar;
    RadioButton raMotor;
    EditText editTextRadius;
    EditText editTextQuantity;
    Button butSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_request_info);
        raCar = findViewById(R.id.radioCar);
        raMotor = findViewById(R.id.radioMotor);
        editTextRadius = findViewById(R.id.radius);
        editTextQuantity = findViewById(R.id.quantity);
        butSend = findViewById(R.id.buttonSendRequest);

        readPref();
        raCar.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                raCar.setChecked(true);
                raMotor.setChecked(false);
            }
        });
        raMotor.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                raCar.setChecked(false);
                raMotor.setChecked(true);
            }
        });

        butSend.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String v;
                if (raCar.isChecked()) v= "0"; else v= "1";
                ActivityMain.prefEditor.putString("vehicle", v);
                ActivityMain.prefEditor.putString("radius", editTextRadius.getText().toString());
                ActivityMain.prefEditor.putString("quantity", editTextQuantity.getText().toString());
                ActivityMain.prefEditor.commit();




            }
        });
    }

    public void readPref(){
        if(ActivityMain.sharedPref.getString("vehicle","0")=="0"){
            raCar.setChecked(true);
        } else raMotor.setChecked(true);

        String radius = ActivityMain.sharedPref.getString("radius","1.0");
        editTextRadius.setText(radius.toCharArray(),0,radius.length());

        String quantity = ActivityMain.sharedPref.getString("quantity","5");
        editTextQuantity.setText(quantity.toCharArray(),0,quantity.length());

    }
}
