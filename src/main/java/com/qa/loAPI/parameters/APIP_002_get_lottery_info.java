package com.qa.loAPI.parameters;

/**
 * @author urPaPa
 * @date 2020/9/29 15:40
 */
public class APIP_002_get_lottery_info {
    private int lotteryTypeId;

    public APIP_002_get_lottery_info() {

    }

    public APIP_002_get_lottery_info(int lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }
    public int getLotteryTypeId() {
        return lotteryTypeId;
    }

    public void setLotteryTypeId(int lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

}
