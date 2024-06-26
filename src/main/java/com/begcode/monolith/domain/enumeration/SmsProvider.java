package com.begcode.monolith.domain.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 短信厂商
 */
public enum SmsProvider {
    /**
     * 阿里云
     */
    ALIBABA("ALIBABA", "阿里云"),
    /**
     * 华为云
     */
    HUAWEI("HUAWEI", "华为云"),
    /**
     * 云片
     */
    YUNPIAN("YUNPIAN", "云片"),
    /**
     * 腾讯云
     */
    TENCENT("TENCENT", "腾讯云"),
    /**
     * 合一
     */
    UNI_SMS("UNI_SMS", "合一"),
    /**
     * 京东云
     */
    JD_CLOUD("JD_CLOUD", "京东云"),
    /**
     * 容联云
     */
    CLOOPEN("CLOOPEN", "容联云"),
    /**
     * 亿美软通
     */
    EMAY("EMAY", "亿美软通");

    @EnumValue
    @JsonValue
    private final String value;

    private final String desc;

    SmsProvider(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SmsProvider getByValue(String value) {
        for (SmsProvider enumSmsProvider : SmsProvider.values()) {
            if (enumSmsProvider.getValue().equals(value)) {
                return enumSmsProvider;
            }
        }
        return null;
    }

    public static SmsProvider getByDesc(String desc) {
        for (SmsProvider enumSmsProvider : SmsProvider.values()) {
            if (enumSmsProvider.getDesc().equals(desc)) {
                return enumSmsProvider;
            }
        }
        return null;
    }
}
