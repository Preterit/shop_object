package com.shangyi.business.utils.qiniu;


import android.util.Log;

import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.sdxxtop.base.utils.UIUtils;
import com.sdxxtop.network.utils.PictureUtil;
import com.shangyi.business.network.Constants;
import com.shangyi.business.network.NetWorkSession;
import com.shangyi.kt.ui.mine.bean.RefundImgParams;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Date:2020/5/18
 * author:lwb
 * Desc:
 */
public class QnUploadUtil {

    private static QnUploadUtil mSingleton = null;
    private UpLoadListener mListener;
    private UploadManager mUploadManager;
    private AuthUtils utils = AuthUtils.create(Constants.QN_ACCESS_KEY, Constants.QN_SECRET_KEY);
    private int currentItem = 0;  // 当前上传的位置
    private int totalItem = 0;    // 总共的数量
    private Map<Integer, RefundImgParams> imgUrlMap = new HashMap<>();  //  存上传成功的的图片的链接

    public static QnUploadUtil getInstance() {
        if (mSingleton == null) {
            synchronized (QnUploadUtil.class) {
                if (mSingleton == null) {
                    mSingleton = new QnUploadUtil();
                }
            }
        }
        return mSingleton;
    }

    public void init() {
        //config配置上传参数
        Configuration configuration = new Configuration.Builder()
                .connectTimeout(10)
                //.zone(zone)
                //.dns(buildDefaultDns())//指定dns服务器
                .responseTimeout(60).build();

        if (this.mUploadManager == null) {
            this.mUploadManager = new UploadManager(configuration, 3);
        }
    }

    public void upLoad(List<File> files) {
        imgUrlMap.clear();
        totalItem = files.size();
        currentItem = 0;
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            Log.e("压缩之前 --- ", "" + file.length());
            String targetPath = NetWorkSession.getContext().getCacheDir() + "/syimg_" + System.currentTimeMillis() + ".png";
            upLoad(file, targetPath, 50);
        }
    }

    private void upLoad(File file, String targetPath, int quality) {
        String compressPath = PictureUtil.compressImage(file.getPath(), targetPath, quality);
        File targetFile = new File(compressPath);

        Log.e("压缩之后 --- ", "" + targetFile.length());
        String uploadToken = utils.uploadToken(Constants.QN_BUCKET, targetFile.getName());
        uploadImg(targetFile.getName(), targetFile.getPath(), uploadToken);
    }

    public void uploadImg(String key, String data, final String token) {
        Logger.i("key = " + key);
        if (mUploadManager == null) {
            init();
        }
        mUploadManager.put(data, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                if (info.isOK()) {
                    String imageUrl = "http://" + "shopvideo.xueli001.cn" + "/" + key;
                    currentItem++;
                    imgUrlMap.put(currentItem, new RefundImgParams(imageUrl));
                    if (mListener != null && currentItem == totalItem) {
                        mListener.onHeadUploadSuccessListener(imgUrlMap);
                    }
                } else {
                    UIUtils.showToast("上传失败。。。");
                    currentItem++;
                    imgUrlMap.put(currentItem, null);
                    if (mListener != null && currentItem == totalItem) {
                        mListener.onHeadUploadSuccessListener(imgUrlMap);
                    }
                }
                Logger.i(key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }

    public void setOnImgUpLoadListener(UpLoadListener listener) {
        this.mListener = listener;
    }
}
