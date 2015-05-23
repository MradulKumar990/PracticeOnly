package com.example.entertainment_vally.entertainmentvally;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * Created by Mradul on 3/17/2015.
 */
public class GuiUtils {

    public static void startScaleAnimationFromPivot (
            int pivotX, int pivotY, final View v,
            final Animator.AnimatorListener animatorListener) {

        final AccelerateDecelerateInterpolator interpolator =
                new AccelerateDecelerateInterpolator();

        v.setScaleY(pivotY);
        v.setPivotX(pivotX);
        v.setPivotY(pivotY);

        v.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {

                        v.getViewTreeObserver().removeOnPreDrawListener(this);

                        ViewPropertyAnimator viewPropertyAnimator =
                                v.animate()
                                        .setInterpolator(interpolator)
                                        .scaleY(1)
                                        .setDuration(20);

                        if (animatorListener != null)
                            viewPropertyAnimator.setListener(
                                    animatorListener);

                        viewPropertyAnimator.start();
                        return true;
                    }
                });
    }
}
