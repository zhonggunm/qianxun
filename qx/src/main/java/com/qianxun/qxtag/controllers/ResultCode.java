package com.qianxun.qxtag.controllers;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public enum ResultCode {

    NoError(ResultCode.COMMON_ERROR_BASE, ""),
    CommonError(ResultCode.COMMON_ERROR_BASE + 1, "一般错误"),
    CommonUnsupported(ResultCode.COMMON_ERROR_BASE + 2, "不支持的操作"),
    CommonNotFound(ResultCode.COMMON_ERROR_BASE + 3, "未找到"),
    CommonAuthontication(ResultCode.COMMON_ERROR_BASE + 4, "认证错误"),
    CommonInvalidParameter(ResultCode.COMMON_ERROR_BASE + 5, "无效参数"),
    CommonDuplicated(ResultCode.COMMON_ERROR_BASE + 6, "重复"),

    DatabaseCommonError(ResultCode.DATABASE_ERROR_BASE, "一般数据库错误"),
    DatabaseNotFound(ResultCode.DATABASE_ERROR_BASE + 1, "未找到数据"),
    DatabaseDuplicatedRecord(ResultCode.DATABASE_ERROR_BASE + 2, "记录重复"),
    DatabaseUnunique(ResultCode.DATABASE_ERROR_BASE + 3, "非唯一记录"),
    DatabaseNoSubset(ResultCode.DATABASE_ERROR_BASE + 4, "无子集"),
    DatabaseUpdateError(ResultCode.DATABASE_ERROR_BASE + 5, "更新错误"),

    NetworkCommonError(ResultCode.NETWORK_ERROR_BASE, "一般网络错误"),
    NetworkRestInvalidParameter(ResultCode.NETWORK_ERROR_BASE + 1, "Rest参数无效"),
    NetworkRestUnsupported(ResultCode.NETWORK_ERROR_BASE + 2, "不支持的Rest请求"),

    FileCommonError(ResultCode.FILE_ERROR_BASE, "一般文件错误"),

    UserCommonError(ResultCode.USER_ERROR_BASE, "一般用户错误"),
    UserVerificationCodeMismatch(ResultCode.USER_ERROR_BASE + 1, "验证码不匹配"),
    UserNoMobileRegistered(ResultCode.USER_ERROR_BASE + 2, "手机未注册"),
    UserNoMailRegistered(ResultCode.USER_ERROR_BASE + 3, "邮箱未注册"),
    UserNotFound(ResultCode.USER_ERROR_BASE + 4, "用户未找到"),
    UserDuplicated(ResultCode.USER_ERROR_BASE + 5, "用户重复"),
    UserAuthorntication(ResultCode.USER_ERROR_BASE + 6, "验证失败"),

    QianxunItemStatusError(ResultCode.QIANXUN_ERROR_BASE, "错误状态"),
    QianxunItemNotFound(ResultCode.QIANXUN_ERROR_BASE + 1, "物品未找到"),

    ThirdpartyCommonError(ResultCode.THIRDPARTY_ERROR_BASE, "第三方用户错误"),

    LastError(ResultCode.LAST_ERROR, "错误上限");

    public final static int COMMON_ERROR_BASE = 0;
    public final static int FILE_ERROR_BASE = 1000;
    public final static int DATABASE_ERROR_BASE = 2000;
    public final static int NETWORK_ERROR_BASE = 3000;
    public final static int USER_ERROR_BASE = 4000;
    public final static int QIANXUN_ERROR_BASE = 10000;
    public final static int THIRDPARTY_ERROR_BASE = 50000;
    public final static int LAST_ERROR = Integer.MAX_VALUE;

    private int value;
    private String text;

    ResultCode(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
