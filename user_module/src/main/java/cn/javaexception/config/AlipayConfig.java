package cn.javaexception.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "pay")
@Component
@Data
public class AlipayConfig {
    // 商户appid
    public  String appid = "2016092500590832";
    // 私钥 pkcs8格式的
    public  String private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCHErMQpRS0D7/3o1waiQ1ROUDUMa1jItRrg2h4PuE0tYwf4lQLOnplOGBPmN1tfSuFu3T8o4BnEhsKnyg/hBZE+wLiDmX3YEA9dwD7vaY688PSeR5gdz5epgTu42SvsTaXlLKfGYW2A0i9HzxzgR6fyPqhDYtrP4jA9y9ZIuPsbp8kjb4TTUcygQ9FR5OPijIjzxmCU8S4PDXtJcQhhXzxMt5V64thN9mCCG0jk0QWb+X8tkXGKJiKJIsRrk1WIihWS0g7h/TMb1iSqoS5ybI26XvmmFYqj6D9QTFNL/DsKwZZRn32x3Lmfq/nIEbi3TVzgrtfJsjdtXWR3xEqlqJ9AgMBAAECggEAJ4rv5SWO5A/gUSz/37zTqz2pnvVUHzupGDiGMv2ljHrBpOvrUsYx4sQYXfUQjechAKuaWgO7qXEBfaCOddnDczpQ48D3VO5WoCfgn9IrggKvB0TyAaenGKpds1nathyZMNMVX8JnBJv9hXBJkD7yqjB6Pv/qGuGktWkn8V5nf2WNUZLgEruEsgIph0PCi0FyvDaSRMlV9ZFMPCdto3yk8uUptlfafQDejRTaOucvHds5Vj/KTL1h5aKYBO5EN5yLVZRYaSFWaRiVINEkqX2C9u/KEktqkYOIPHh3YDM4RcpcYYGRrHfDPTdYz24hNIgEJXAJKTKihgVcGaFckqJhAQKBgQDUpppQE2M5q5UARJcTwKnWgxzPyACXw6CRHxyHBneX+m+rgtLihjWtq31rcZGdciksZZz5ayonpuPntxmFgpvcaJBInXzRkcQEx6TRcD2AoosclV9ewNqj3H2sBQ2aJm5CNPKK+1zu6wkrjZSRvB+cxXxxXcUKa86ar2nGJG7J4QKBgQCim5+PVfuDYRivGbQvOqlpS6gxuAkBQh1JoCrJMhSj4fIhnl7OawrFJod+RPmJwOEWmTEl+UYIBjtAipzFZkLtnkNx3h7I5A88t7Ko+l4ZxQHkNosRcjfw4QclM+y/NVti32zODEoK3hizyY/BZDWKn5yTzYG3thYdUeFDLapEHQKBgQCeVXJenyDphs1Jxshqh7CtrTAdscBnP9zlUTHvg6w2d9z/6kfVLLL5HdAj2QrgHWl0yjAIMnfP0F6vYFDbbPVaNmz/9GtEq3E1UeVxoE3a4qwL21iId7YT/g+Arr6DAYxlNYFGZIS8zDomuKNfo6AyHYVb78LP0Jej62opg2UOYQKBgHvGdhiHXKJO4Jk4uZ1Ry+7otijSa5hXmS+YCbmIs4ljzgmlIYmClXoor+5/1mrzNOMczPqY2qP1CllAqcDg7IUveStN5qUIZEjVk9toaQS4n431rroi9U4YHHZHJikOFngWlh3p4NqQ4MDJ5vFQtCjHAkrHnbwXkJHU5+FY+0hJAoGBALZsRd636z0IbHtsICpeLFFN3ta4jg4q8FWhDb2NhmEhFn3XA0XyPMaKvXgFAknAYLhFDGC9ymkN0Axs9Bz/B3plwwo9kxNtbyqV0O6CBZtNSTZ7VSPzQOriueX798netqmqa/Um3ZLzGnGDGphKjNuKqSllrBYoCed8uEpoMVTD";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public  String notify_url = "http://localhost:8081/api/alipay/callback";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public  String return_url = "http://localhost:8081/api/alipay/callback";
    // 请求网关地址
    public  String url = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public  String charset = "UTF-8";
    // 返回格式
    public  String format = "json";
    // 支付宝公钥
    public  String public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv3uwFV3RHxpGG7YJQHJvMtxji4zEUqnAu44vwQVN7k/w+UpyVRKaixtxBkhuIc6wWvWAmE6sXnSiYL4Y8SosYr64pIuBbOPgo6EFyIFljOMdU+wSxozvVubpfYBmRUVxiT5hEM97Wg4FLHQ3+ueQgTTa7gxsEeklmO+mjXs0C8a49hRE9HnYneqUQ5O6pNphMdkyNhe0jyrKehSE/vmJjBc5jFapssCwVYsrbiD5GBun9I57Ro9J0rBQFBq0LRXE2ncM7wdNK5qxUn5IqXJGPC3zgPboeiW1C+ugWILmQULCpjHjHGAvpepL8bqUdWGKwRISFD4uOg3eQTCFffg8/wIDAQAB";
    // 日志记录目录
    public  String log_path = "/log";
    // RSA2
    public  String signtype = "RSA2";
}
