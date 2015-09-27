package com.droid.floatboat.collabcart.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.droid.floatboat.collabcart.R;


/**
 * @author Pallavi
 */

public class CartUtils {

    private static ProgressDialog progress;
    private static AnimatorSet animatorSet;

    public static void showProgress(Context context) {
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.show();
        progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.progress, null);
        progress.setContentView(view);
        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.rotate);
        animatorSet.setTarget(view);
        animatorSet.start();

    }

    public static void hideProgress() {
        if (progress != null && progress.isShowing()) {
            animatorSet.removeAllListeners();
            animatorSet.end();
            animatorSet.cancel();
            progress.cancel();

        }

    }

}
