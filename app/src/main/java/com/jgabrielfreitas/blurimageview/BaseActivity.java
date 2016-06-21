package com.jgabrielfreitas.blurimageview;

import com.jgabrielfreitas.layoutid.activity.InjectLayoutBaseActivity;

import butterknife.ButterKnife;

/**
 * Created by JGabrielFreitas on 20/06/16.
 */
public abstract class BaseActivity extends InjectLayoutBaseActivity {

    protected abstract void modifyViews();

    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        modifyViews();
    }

    protected void onStop() {
        super.onStop();
        ButterKnife.unbind(this);
    }
}
