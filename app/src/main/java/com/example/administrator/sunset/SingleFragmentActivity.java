package com.example.administrator.sunset;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dlw on 2016/7/6.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract android.support.v4.app.Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.Fragment fragment=fm.findFragmentById(R.id.fragment_container);
        if (fragment==null){
            fragment=createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

    }
    @LayoutRes
    protected int getLayoutId(){
        return R.layout.activity_fragment;
    }
}
