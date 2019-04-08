package cn.javaexception.pay;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016092500590832";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCOe5XZdV+xWd6qLXyUefPtJqqpsOjQdf4WBBCqsSMCw+UJhNf0uNssac8nkjGTmktYwkM8My0cciMgy/4oQJlLLVn7W8C7csJ78611rF4bqWmUjE6dlKH5xldMt93Bqxusph2tYrkRwAz0Pb2SIaDdyN9LqnVKt6mcK4eYr4RdLxhMz1yOzBwBqR7TsysE80HX+M53ISuIUu5tbnfUoeD4LqWqQJtIx7a0BRUuKcxPrB+JJbPeSAqzHENTsIm8hCEqVGvjtENa5gOYA3CZr+izkAm/kTPFK3n9C8xRSFj5ZauCwjLON2FKsa+kTqLHy5WcEKhP1cKZE3dmtCXYZNCDAgMBAAECggEBAITcOswOxc+DhLzdM3jkCw/NaYBYRY/1Jpvy6HOyP3pZnp30sMczBLTyaUd17CK90pqOctGUyvDmgVkYvwNb0VWnkWtwkFR6W13nyIyc0RgsLhZliv0n1q4pQ8h2/yKTYnfoYm4GS5qAodOyKq40C2Qj5gwRg9PLVS4Z7RP0PpIAboQTjZzzjZnFqwBBdRZAmSD6We+trbswZgqu9gTsmtEPMEtgh3+bDd/+SHDwfc78B9EsPID/iD8iiWiadT2wBadXwjn6fz+K/D80KWc9rDe990d7NdPYae/1VQeXeLW/yQRJm4Jbz6xv70lvnrEwwwEu92PPK1bEAAbE4ByIQcECgYEAyfwIhXix7MZJ4yAR2SIHV6/b92x+RlBFtTBgsNHmz4cn6tw9gugCIwlwsJRl9sz4FPKMIqFlZKdX8Fv+TWxqL3lrbjSc/oCwURUCuVy136wV8Rtg890ZYbQIZTq3wU2g+i1MzKkbeKXj8RfbIak0CQeVdkMP7ZmP1IoGCXTpRCECgYEAtJYH1dkzUirqhY021fLvAB0QYcneyhYy8gk57XNH49PsKTTAHtU1q7/PMYsAvHdUn3a7sW4ZRf/s2wQuKH1OB6lOID/SO04lccoT2Ee1d1fbSZW8/DxP/ErGmRTHNWs+6jJDa3LQwZZ3TsCrL3IIYbaTkLY+nq/MZcLESuxwgCMCgYEAnKX9yd4IR7SlGyQFE5wji6sU8rhO2qNo3JPveVLhKfQNTkWO4xaiiZ/JqPj+d0ER6dUOP9FiPITYW85M1KkPVVBatDbTx0Hw/IFKAo5Xc2gYAE0WjSrdg3vHwxKd7qoQ/dXiKRvB9Xja5mF1Do8WjyY1kWmDUed1uAVvlyf3vWECgYA+N05dw+YlkfmYp41T6s2i2WCSgE1MjJdiwgD+KPCEsrezfqQOO2CxWnUU2ZIYXHiANBT4TTlR35mH34nTk1A13YO4IqTYn9TQvlFV+2auxksbsAYbVOAtyMlhZkI3ykozv7twom7/s0pJjKGtx/jRSWjPu6oZW/HswUyFj642xwKBgBnPkBHtiOE0tgcAflvrmRKpEPX4bdLoRc22tp8d6KsBe61VAktxFCLG6GhAGIaDwVvW2fHFhS+ZrNsKPmivnpvgz9KQiAuSjno7doSyda8LaYeHUBY2+rsskUXKkmzcunzC4YXJ+fQCNMdZyJ9d2lA2iHDYRtkqcREyWf4gGH/6";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv3uwFV3RHxpGG7YJQHJvMtxji4zEUqnAu44vwQVN7k/w+UpyVRKaixtxBkhuIc6wWvWAmE6sXnSiYL4Y8SosYr64pIuBbOPgo6EFyIFljOMdU+wSxozvVubpfYBmRUVxiT5hEM97Wg4FLHQ3+ueQgTTa7gxsEeklmO+mjXs0C8a49hRE9HnYneqUQ5O6pNphMdkyNhe0jyrKehSE/vmJjBc5jFapssCwVYsrbiD5GBun9I57Ro9J0rBQFBq0LRXE2ncM7wdNK5qxUn5IqXJGPC3zgPboeiW1C+ugWILmQULCpjHjHGAvpepL8bqUdWGKwRISFD4uOg3eQTCFffg8/wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
