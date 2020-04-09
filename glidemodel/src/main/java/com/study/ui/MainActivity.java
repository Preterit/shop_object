package com.study.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.study.glidemodel.GlideImageLoader;
import com.study.glidemodel.GlideImageView;
import com.study.glidemodel.R;
import com.study.glidemodel.ShapeImageView;
import com.study.glidemodel.progress.CircleProgressView;
import com.study.glidemodel.progress.OnGlideImageViewListener;
import com.study.glidemodel.progress.OnProgressListener;

import java.util.Random;

import static com.study.ui.SingleImageActivity.KEY_IMAGE_URL;
import static com.study.ui.SingleImageActivity.KEY_IMAGE_URL_THUMBNAIL;


/**
 * Created by sunfusheng on 2017/6/3.
 */
public class MainActivity extends Activity {

    GlideImageView image11;
    GlideImageView image12;
    GlideImageView image13;
    GlideImageView image14;

    GlideImageView image21;
    GlideImageView image22;
    GlideImageView image23;
    GlideImageView image24;

    GlideImageView image31;
    GlideImageView image32;
    GlideImageView image33;
    GlideImageView image34;

    GlideImageView image41;
    CircleProgressView progressView1;
    GlideImageView image42;
    CircleProgressView progressView2;

    TextView draggableView;

    String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497688355699&di=ea69a930b82ce88561c635089995e124&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Ff84e566bcf654b3698363409fbd676ef20161119091503.jpg";
    String url2 = "http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg";

    public static boolean isLoadAgain = false; // Just for fun when loading images!

    public static final String cat = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586423547497&di=83ab553a96940774180bf0e2f06184cb&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg";
    public static final String cat_thumbnail = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586422991743&di=7f81c816265b4c4a7d2bebb7cb658812&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg";

    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl.jpg";
    public static final String girl_thumbnail = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586422991744&di=af054b130b05c4d98630b46b6d433dff&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg";

    String gif1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496754078616&di=cc68338a66a36de619fa11d0c1b2e6f3&imgtype=0&src=http%3A%2F%2Fapp.576tv.com%2FUploads%2Foltz%2F201609%2F25%2F1474813626468299.gif";
    String gif2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497276275707&di=57c8c7917e91afc1bc86b1b57e743425&imgtype=0&src=http%3A%2F%2Fimg.haatoo.com%2Fpics%2F2016%2F05%2F14%2F9%2F4faf3f52b8e8315af7a469731dc7dce5.jpg";
    String gif3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b999_10000&sec=1497276275707&di=57c8c7917e91afc1bc86b1b57e743425&imgtype=0&src=http%3A%2F%2Fimg.haatoo.com%2Fpics%2F2016%2F05%2F14%2F9%2F4faf3f52b8e8315af7a469731dc7dce5.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image11 = (GlideImageView) findViewById(R.id.image11);
        image12 = (GlideImageView) findViewById(R.id.image12);
        image13 = (GlideImageView) findViewById(R.id.image13);
        image14 = (GlideImageView) findViewById(R.id.image14);

        image21 = (GlideImageView) findViewById(R.id.image21);
        image22 = (GlideImageView) findViewById(R.id.image22);
        image23 = (GlideImageView) findViewById(R.id.image23);
        image24 = (GlideImageView) findViewById(R.id.image24);

        image31 = (GlideImageView) findViewById(R.id.image31);
        image32 = (GlideImageView) findViewById(R.id.image32);
        image33 = (GlideImageView) findViewById(R.id.image33);
        image34 = (GlideImageView) findViewById(R.id.image34);

        image41 = (GlideImageView) findViewById(R.id.image41);
        progressView1 = (CircleProgressView) findViewById(R.id.progressView1);
        image42 = (GlideImageView) findViewById(R.id.image42);
        progressView2 = (CircleProgressView) findViewById(R.id.progressView2);

        draggableView = (TextView) findViewById(R.id.draggable_view);
        draggableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
            }
        });

        isLoadAgain = new Random().nextInt(3) == 1;

        line1();
        line2();
        line3();
        line41();
        line42();

    }

    private void line1() {
        image11.loadImage(url1, R.color.placeholder_color).listener(new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                Log.d("--->image11", "bytesRead: " + bytesRead + " totalBytes: " + totalBytes + " isDone: " + isDone);
            }
        });

        image12.setBorderWidth(3);
        image12.setBorderColor(R.color.transparent20);
        image12.loadCircleImage(url1, R.color.placeholder_color);

        image13.setRadius(15);
        image13.setBorderWidth(3);
        image13.setBorderColor(R.color.blue);
        image13.setPressedAlpha(0.3f);
        image13.setPressedColor(R.color.blue);
        image13.loadImage(url1, R.color.placeholder_color);

        image14.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image14.setBorderWidth(3);
        image14.setBorderColor(R.color.orange);
        image14.setPressedAlpha(0.2f);
        image14.setPressedColor(R.color.orange);
        image14.loadImage(url1, R.color.placeholder_color);
    }

    private void line2() {
        image21.loadImage(url2, R.color.placeholder_color);
        image22.loadImage(url2, R.color.placeholder_color);
        image23.loadImage(url2, R.color.placeholder_color);
        image24.loadImage(url2, R.color.placeholder_color);
    }

    private void line3() {
        image31.loadLocalImage(R.drawable.gif_robot_walk, R.drawable.ic_launcher);

        image32.loadCircleImage(gif1, R.drawable.ic_launcher).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                Log.d("--->image32", "percent: " + percent + " isDone: " + isDone);
            }
        });

        image33.loadImage(gif2, R.drawable.ic_launcher);
        RequestOptions requestOptions = image34.requestOptions(R.drawable.ic_launcher).error(R.drawable.gif_robot_walk).centerCrop();
        image34.load(gif3, requestOptions);
    }

    private void line41() {
        image41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, cat);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, cat_thumbnail);
//                ActivityOptionsCompat compat = ActivityOptionsCompat
//                        .makeSceneTransitionAnimation(MainActivity.this, image41, getString(R.string.transitional_image));
                startActivity(intent);
            }
        });

        RequestOptions requestOptions = image41.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第一种方式加载
        image41.load(cat_thumbnail, requestOptions).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView1.setProgress(percent);
                progressView1.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void line42() {
        image42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SingleImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, girl);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, girl_thumbnail);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this, image42, getString(R.string.transitional_image));
                ActivityCompat.startActivity(MainActivity.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image42.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第二种方式加载：可以解锁更多功能
        GlideImageLoader imageLoader = image42.getImageLoader();
        imageLoader.setOnGlideImageViewListener(girl_thumbnail, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {

                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView2.setProgress(percent);
                progressView2.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
        imageLoader.requestBuilder(girl_thumbnail, requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image42);
    }

}
