package com.jgabrielfreitas.blurimageview;

import butterknife.ButterKnife;
import com.jgabrielfreitas.layoutid.activity.InjectLayoutBaseActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

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

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void toast(String message) {
        makeText(this, message, LENGTH_SHORT).show();
    }
}
