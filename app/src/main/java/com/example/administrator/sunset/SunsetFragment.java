package com.example.administrator.sunset;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

/**
 * Created by Administrator on 2016/8/26 0026.
 */

public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    public static SunsetFragment newInstance() {


        SunsetFragment fragment = new SunsetFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sunset,container,false);
        mSceneView=view;
        mSunView=view.findViewById(R.id.sun);
        mSkyView=view.findViewById(R.id.sky);
        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        Resources resource=getResources();
        mBlueSkyColor=resource.getColor(R.color.blue_sky);
        mSunsetSkyColor=resource.getColor(R.color.sunset_sky);
        mNightSkyColor=resource.getColor(R.color.night_sky);
        return view;
    }
    private void startAnimation(){
        float sunYStart=mSunView.getTop();
        float sunYEnd=mSkyView.getHeight();
        ObjectAnimator heightAnimator=ObjectAnimator.ofFloat(mSunView,"y",sunYStart,sunYEnd).setDuration(3000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator sunsetSkyAnimator=ObjectAnimator.ofInt(mSkyView,"backgroundColor",mBlueSkyColor,mSunsetSkyColor)
                .setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());
        ObjectAnimator nightSkyAnimator=ObjectAnimator.ofInt(mSkyView,"backgroundColor",mSunsetSkyColor,mNightSkyColor)
                .setDuration(1500);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());
        AnimatorSet animationSet=new AnimatorSet();
        animationSet.play(heightAnimator)
                .with(sunsetSkyAnimator)
                .before(nightSkyAnimator);

        animationSet.start();
    }
}
