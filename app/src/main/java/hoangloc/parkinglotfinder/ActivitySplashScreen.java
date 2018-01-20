package hoangloc.parkinglotfinder;

/**
 * Created by NguyenHoangLoc on 1/20/2018.
 */


import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ActivitySplashScreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StartAnimations();
    }
    private void StartAnimations() {
        //Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //anim.reset();
        ConstraintLayout l=(ConstraintLayout) findViewById(R.id.lin_lay);
        ImageView iv = (ImageView) findViewById(R.id.splash);

        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 1000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(ActivitySplashScreen.this,
                            ActivityMain.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    ActivitySplashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    ActivitySplashScreen.this.finish();
                }
            }
        };
        splashThread.start();
    }
}
