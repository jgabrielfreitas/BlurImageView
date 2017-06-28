package com.jgabrielfreitas.blurimageview;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import butterknife.Bind;
import butterknife.OnClick;
import com.jgabrielfreitas.core.BlurImageView;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;
import com.jgabrielfreitas.permissions.core.interfaces.OnPermissionRequest;
import com.jgabrielfreitas.permissions.core.managers.StoragePermissionManager;
import com.karumi.dexter.PermissionToken;

import static android.content.Intent.ACTION_PICK;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static android.provider.MediaStore.MediaColumns.DATA;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnSeekBarChangeListener, OnPermissionRequest {

  private static final int RESULT_LOAD_IMAGE = 100;
  @Bind(R.id.dogBlurImageView) BlurImageView dogBlurImageView;
  @Bind(R.id.blurSeekBar)      SeekBar       blurSeekBar;

  protected void modifyViews() {
    blurSeekBar.setOnSeekBarChangeListener(this);
  }

  @OnClick(R.id.chooseFromGalleryButton) public void getImageFromGallery() {
    StoragePermissionManager storagePermissionManager = new StoragePermissionManager(this, this);
    storagePermissionManager.requestPermission();
  }

  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    Log.e("seekbar", "Progress --> " + progress);
    dogBlurImageView.setBlur(progress);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
      Uri selectedImage = data.getData();
      String[] filePathColumn = { DATA };
      Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
      cursor.moveToFirst();
      int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
      String picturePath = cursor.getString(columnIndex);
      cursor.close();
      dogBlurImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {}

  @Override public void onStopTrackingTouch(SeekBar seekBar) {}

  @Override public void onPermissionAllowed() {
    //Intent i = new Intent(ACTION_PICK, EXTERNAL_CONTENT_URI);
    //startActivityForResult(i, RESULT_LOAD_IMAGE);
    dogBlurImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.code));
  }

  @Override public void onPermissionDenied() {
    toast("Permission denied");
  }

  @Override public void onPermissionDeniedButAskAgain(PermissionToken permissionToken) {
    permissionToken.continuePermissionRequest();
  }
}
