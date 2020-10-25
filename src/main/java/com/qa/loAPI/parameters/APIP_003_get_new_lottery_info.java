package com.qa.loAPI.parameters;

/**
 * @author urPaPa
 * @date 2020/10/6 19:49
 */
public class APIP_003_get_new_lottery_info {
    private int lotteryId;
    private int userId;
    public APIP_003_get_new_lottery_info(){

    }
    public APIP_003_get_new_lottery_info(int lotteryId, int userId){
        this.lotteryId = lotteryId;
        this.userId = userId;

    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
