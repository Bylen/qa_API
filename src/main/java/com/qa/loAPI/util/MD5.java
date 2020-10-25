package com.qa.loAPI.util;

import java.util.HashMap;

/**
 * @author urPaPa
 * @date 2020/10/24 10:45
 */
public class MD5 {
    public void bet_user_bet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", "295");
        map.put("lotteryId", "4001");
        map.put("expectNo", "751398");
        map.put("data", "[{\"playId\":1,\"betInfo\":\"大\",\"withNo\":0,\"betMoney\":\"2.00\"}]");
        map.put("token", "8f5d2ac0-3f83-4769-a8b9-538fa25cda82");
        String md5 = EncryptUtil.md5(map,"&key=l%@{%N6:J{ei.%riJlT.GXg&@Q&|A0ms");
        System.out.println(md5);
    }
}
