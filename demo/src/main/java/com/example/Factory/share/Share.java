package com.example.Factory.share;

public interface Share {
    /**
     * 获取分享类型
     *
     * @return Integer 枚举code
     */
    String getShareFunctionType();
    /**
    * @Param: shareName
    * @return
    */
    String mainProcess(String shareName);
}
