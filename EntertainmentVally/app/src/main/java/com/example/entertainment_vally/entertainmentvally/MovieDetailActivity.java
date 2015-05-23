package com.example.entertainment_vally.entertainmentvally;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;


public class MovieDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            configureEnterTransition ();

        else {

            mViewLastLocation = getIntent()
                    .getIntArrayExtra("view_location");

            configureEnterAnimation ();
        }
    }

    private void configureEnterAnimation() {

        GUIUtils.startScaleAnimationFromPivot(
                mViewLastLocation[0], mViewLastLocation[1],
                mObservableScrollView, new AnimatorAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        super.onAnimationEnd(animation);
                        GUIUtils.showViewByScale(mFabButton);
                    }
                }
        );

        animateElementsByScale();
    }

    @Override
    public void showConfirmationView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            GUIUtils.showViewByRevealEffect(mConfirmationContainer,
                    mFabButton, GUIUtils.getWindowWidth(this));

        else
            GUIUtils.startScaleAnimationFromPivot(
                    (int) mFabButton.getX(),(int) mFabButton.getY(),
                    mConfirmationContainer, null);

        animateConfirmationView();
        startClosingConfirmationView();
    }

    @Override
    public void animateConfirmationView() {

        Drawable drawable = mConfirmationView.getDrawable();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            if (drawable instanceof Animatable)
                ((Animatable) drawable).start();

            else
                mConfirmationView.startAnimation(
                        AnimationUtils.loadAnimation(this,
                                R.anim.appear_rotate));
    }
}


}
