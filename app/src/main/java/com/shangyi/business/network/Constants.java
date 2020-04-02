package com.shangyi.business.network;

import java.io.File;

public interface Constants {
    String PATH_DATA = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "NetCache";
    String PATH_IMG = NetWorkSession.getContext().getCacheDir().getAbsolutePath() + File.separator + "img";


    String PART_ID = "part_id";
    String USER_ID = "user_id";

    String BASEURL = "baseurl";
}
