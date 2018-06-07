package com.mkiisoft.androidlibrary.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.mkiisoft.androidlibrary.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Utils {

    public static void animateColor(View view, int nextColor) {
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            int color = ((ColorDrawable) background).getColor();
            ObjectAnimator animator = ObjectAnimator
                    .ofInt(view, view.getContext().getString(R.string.property_background), color, nextColor)
                    .setDuration(400);
            animator.setEvaluator(new ArgbEvaluator());
            animator.start();
        }
    }

    public static Uri shareBitmap(View view) {
        Uri uri = getImageUri(view.getContext(), getViewBitmap(view));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        view.getContext().startActivity(Intent.createChooser(intent,
                view.getContext().getString(R.string.share_joke_title)));
        return uri;
    }

    private static Bitmap getViewBitmap(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private static Uri getImageUri(Context context, Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                image,
                String.valueOf(System.currentTimeMillis()),
                null);
        return Uri.parse(path);
    }

    @SuppressWarnings("unused")
    private static String imageRealPath(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] projection = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            assert cursor != null;
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columnIndex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @SuppressWarnings("unused")
    public static boolean deleteSharedFile(Context context, Uri uri) {
        File file = new File(imageRealPath(context, uri));
        return file.exists() && file.delete();
    }
}