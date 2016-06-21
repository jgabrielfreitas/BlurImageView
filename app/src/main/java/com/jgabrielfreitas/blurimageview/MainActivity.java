package com.jgabrielfreitas.blurimageview;

import android.util.Log;
import android.widget.SeekBar;

import com.jgabrielfreitas.core.BlurImageView;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import butterknife.Bind;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    @Bind(R.id.dogBlurImageView) BlurImageView dogBlurImageView;
    @Bind(R.id.blurSeekBar)      SeekBar       blurSeekBar;

    protected void modifyViews() {
        blurSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("seekbar", "Progress --> " + progress);
        dogBlurImageView.setBlur(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

}
