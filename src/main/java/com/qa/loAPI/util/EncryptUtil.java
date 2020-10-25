package com.qa.loAPI.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 加密工具类
 */
@Slf4j
public class EncryptUtil {
    private EncryptUtil() {
    }

    private static final String MD5_ALGORITHM = "MD5";
    private static final String SHA1_ALGORITHM = "SHA-1";
    private static final String SHA256_ALGORITHM = "SHA-256";

    /**
     * @param object 源对象数据, 可以是Map, 实体对象.
     * @return MD5
     */
    public static String md5(Object object) {
        return md5(object, null);
    }

    /**
     * @param object     源对象数据, 可以是Map, 实体对象.
     * @param privateKey 密钥. 值："&key=l%@{%N6:J{ei.%riJlT.GXg&@Q&|A0ms"
     * @return MD5          MD5加密
     */
    public static String md5(Object object, String privateKey) {
        if (object == null) {
            return null;
        }
        TreeMap<String, Object> treeMap;
        if (object instanceof Map) {
            if (object instanceof TreeMap) {
                treeMap = (TreeMap) object;
            } else {
                treeMap = new TreeMap((Map) object);
            }
        } else if (object instanceof String) {
            if (StringUtils.isBlank(privateKey)) {
                return md5((String) object);
            }
            return md5((String) object + privateKey);
        } else if (object instanceof List) {
            if (StringUtils.isBlank(privateKey)) {
                return md5(JSON.toJSONString(object));
            }
            return md5(JSON.toJSONString(object) + privateKey);
        } else {
            JSONObject jsonObject;
            try {
                jsonObject = JSON.parseObject(JSON.toJSONString(object));
            } catch (Exception e) {
                throw new RuntimeException("-->加密前对象转换异常" + object, e);
            }
            treeMap = new TreeMap(jsonObject);
        }

        StringBuffer sb = new StringBuffer();
        treeMap.remove("sign");
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            if (entry.getValue() == null) {
                sb.append(entry.getKey())
                        .append("&");
            } else {
                sb.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        String str = sb.substring(0, sb.length() - 1);

        if (!StringUtils.isBlank(privateKey)) {
            str = str + privateKey;
        }

        log.info("-->MD5加密之前拼串: {}", str);
        return md5(str, false);
    }


    /**
     * @param str 源字符串
     * @return MD5值, 小写
     */
    public static String md5(String str) {
        return md5(str, false);
    }

    /**
     * @param str 源字符串
     * @return MD5 值, 大写
     */
    public static String md5(String str, boolean toUpperCase) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (toUpperCase) {
            return encryptStr(str, MD5_ALGORITHM).toUpperCase();
        }
        return encryptStr(str, MD5_ALGORITHM);
    }

    /**
     * @param str 源字符串
     * @return sha1 值, 小写
     */
    public static String sha1(String str) {
        return sha1(str, false);
    }

    /**
     * @param str 源字符串
     * @return sha1 值, 大写
     */
    public static String sha1(String str, boolean toUpperCase) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (toUpperCase) {
            return encryptStr(str, SHA1_ALGORITHM).toUpperCase();
        }
        return encryptStr(str, SHA1_ALGORITHM);
    }

    /**
     * @param str 源字符串
     * @return sha256 值, 小写
     */
    public static String sha256(String str) {
        return sha256(str, false);
    }

    /**
     * @param str 源字符串
     * @return sha256 值, 大写
     */
    public static String sha256(String str, boolean toUpperCase) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (toUpperCase) {
            return encryptStr(str, SHA256_ALGORITHM).toUpperCase();
        }
        return encryptStr(str, SHA256_ALGORITHM);
    }


    /**
     * @param str       源字符串
     * @param algorithm 加密算法
     * @return
     */
    private static String encryptStr(String str, String algorithm) {
        byte[] md5s = null;
        try {
            md5s = MessageDigest.getInstance(algorithm).digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("不支持 " + algorithm + " 这个算法.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md5s.length; ++i) {
            sb.append(Integer.toHexString((md5s[i] & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
}