package com.shangyi.business.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.IdRes;

import java.lang.ref.WeakReference;
import java.util.Random;


public class ViewUtil {
    @SuppressWarnings("unchecked")
    public static <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }

    public static int dp2px(Context ctx, int dp) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static boolean setClickLimit(View view, int limitTimeMs, String tip) {
        if (view == null) {
            return true;
        }

        final View clickView = view;
        Object object = clickView.getTag(clickView.getId());
        if (object == null) {
            view.setEnabled(false);
            postDelay(clickView, limitTimeMs);
            return false;
        } else {
            boolean bBoolean = object instanceof Boolean;
            if (bBoolean) {
                boolean tag = (Boolean) object;
                if (tag) {
                    return true;
                } else {
                    view.setEnabled(false);
                    postDelay(clickView, limitTimeMs);
                    return false;
                }
            }
            return false;
        }
    }

    private static void postDelay(final View view, int limitTimeMs) {
        view.setTag(view.getId(), true);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
                view.setTag(view.getId(), false);
            }
        }, limitTimeMs);
    }

    /**
     * 设置圆角背景
     */
    public static GradientDrawable addBtnBackgroundRound(String color, boolean flag, int radius) {
        // true标识设置为圆角，color为背景颜色,radius为圆角弧度
        int fillColor;
        GradientDrawable gd = null;
        if (flag == true) {
            fillColor = Color.parseColor(color);
            gd = new GradientDrawable();
            gd.setColor(fillColor);
            gd.setCornerRadius(radius);
        } else {
            fillColor = Color.parseColor(color);
            gd = new GradientDrawable();
            gd.setColor(fillColor);
        }
        return gd;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int getDrawableRs(Context context, String resName) {
//		try {
//			Field field = R.drawable.class.getField(resName);
//			return field.getInt(new R.drawable());
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
        return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
    }

    public static int getIdRs(Context context, String resName) {
//		try {
//			Field field = R.id.class.getField(resName);
//			return field.getInt(new R.id());
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
        return context.getResources().getIdentifier(resName, "id", context.getPackageName());
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    public static int getRealHeight(Context context, int width, int height, int padding, boolean flag) {
        int screenW = getScreenWidth(context);
        if (flag) {
            screenW = screenW / 2;
        }
        int w = screenW - (flag ? 1 : 2) * dp2px(context, padding);
        int h = (int) (((float) w) * height / width);
        return h;
    }

    public static int getRealHeight(Context context, int width, int height, int padding, boolean flag, int w) {
        int h = (int) (((float) w) * height / width);
        return h;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void setImageViewColorFilter(ImageView view) {
        final ImageView imageView = view;
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        //在按下事件中设置滤镜
                        imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        break;
                    case MotionEvent.ACTION_UP:
                        //由于捕获了Touch事件，需要手动触发Click事件
                        imageView.performClick();
                    case MotionEvent.ACTION_CANCEL:
                        //在CANCEL和UP事件中清除滤镜
                        imageView.clearColorFilter();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void editTextInScrollView(final @IdRes int editId, final EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if ((view.getId() == editId && canVerticalScroll(editText))) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private static boolean canVerticalScroll(EditText editText) {
        WeakReference<EditText> editTextWeakReference = new WeakReference<>(editText);
        editText = editTextWeakReference.get();
        if (editText.canScrollVertically(-1) || editText.canScrollVertically(1)) {
            //垂直方向上可以滚动
            return true;
        }
        return false;
    }


    /**
     * Drawable转换成一个Bitmap
     *
     * @param drawable drawable对象
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getRoundedCornerBitmapRes(Context context, int drawbleRes, float roundPx) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawbleRes);
        return getRoundedCornerBitmap(bitmap, roundPx);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    /**
     * 隐藏软键盘
     */
    public static void hideSoftKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void showSoftKeyboard(View view) {
        if (view == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
