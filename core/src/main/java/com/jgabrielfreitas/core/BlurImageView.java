package com.jgabrielfreitas.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by JGabrielFreitas on 20/06/16.
 */
public class BlurImageView extends ImageView {


    private float defaultBitmapScale = 0.1f;
    private static final int   MAX_RADIUS   = 25;
    private static final int   MIN_RADIUS   = 1;
    Drawable imageOnView;


    public BlurImageView(Context context) {
        super(context);
        init(null);
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (imageOnView == null)
            imageOnView = drawable;
    }

    private void init(AttributeSet attrs) {

        if (attrs != null && getDrawable() != null) {

            imageOnView = getDrawable();

            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BlurImageView, 0, 0);

            Integer radius = typedArray.getInteger(R.styleable.BlurImageView_radius, 0);

            setBlur(radius);

            typedArray.recycle();
        }
    }


    public void setBitmapScale(float bitmapScale) {
        this.defaultBitmapScale = bitmapScale;
    }

    public void setBlur(int radius) {

        if (imageOnView == null)
            imageOnView = getDrawable();

        // max radius = 25
        if (radius > MIN_RADIUS && radius <= MAX_RADIUS) {

            Bitmap blurred = blurRenderScript(((BitmapDrawable) imageOnView).getBitmap(), radius);
            setImageBitmap(blurred);
            invalidate();

        } else if (radius == 0) {
            setImageDrawable(imageOnView);
            invalidate();

        } else
            Log.e("BLUR", "actualRadius invalid: " + radius);
    }

    public Bitmap blurRenderScript(Bitmap smallBitmap, int radius) {

        int width  = Math.round(smallBitmap.getWidth() * defaultBitmapScale);
        int height = Math.round(smallBitmap.getHeight() * defaultBitmapScale);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(smallBitmap, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(getContext());
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
