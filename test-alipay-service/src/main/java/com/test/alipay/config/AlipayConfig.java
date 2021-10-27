package com.test.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016080200147065";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String MERCHANT_PRICATE_KEY ="商户私钥";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY="支付宝公钥";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问，测试修改springboot端口和外网地址
    public static String NOTIFY = "http://外网地址/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网（通过netapp获取net123或自己购买生成）可以正常访问
    public static String RETURN_URL = "http://外网地址/return_url";
    // 签名方式
    public static String SIGN_TYPE = "RSA2";
    // 字符编码格式
    public static String CHARSET = "utf-8";
    // 支付宝网关
    public static String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "F:\\";
    /** * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）*/
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
