package com.shangyi.business.network;

import java.io.File;

public interface Constants {
    String PATH_DATA = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "NetCache";
    String PATH_IMG = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "img";


    String PART_ID = "part_id";
    String USER_ID = "user_id";

    String BASEURL = "baseurl";

    String API_KEY = "api_key"; // 加密的key
    String APP_ID = "wxd930ea5d5a258f4f";

    //微信的appID
    String WXAPP_ID = "wx8c512b137c836be1";
}
