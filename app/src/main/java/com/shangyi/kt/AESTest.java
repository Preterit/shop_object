package com.shangyi.kt;

import com.shangyi.business.http.AESUtils;

/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
public class AESTest {
    public static void main(String[] args) {
        String data = "wsqAqwPrvvd+NT3+jerpZ3DOX9Iu63OS5WLxwXPD5No=";
        String key = "1234567890123456";
        try {
            String encrypt = AESUtils.encrypt(data, key);
            System.out.println("解密后的数据 --- " + encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
