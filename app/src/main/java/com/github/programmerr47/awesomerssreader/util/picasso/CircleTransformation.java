package com.github.programmerr47.awesomerssreader.util.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

import static android.graphics.Shader.TileMode.CLAMP;

public enum CircleTransformation implements Transformation {
    INSTANCE;

    @SuppressWarnings("SameReturnValue")
    public static Transformation circleTransformation() {
        return CircleTransformation.INSTANCE;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squaredBitmap, CLAMP, CLAMP));
        paint.setAntiAlias(true);

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
