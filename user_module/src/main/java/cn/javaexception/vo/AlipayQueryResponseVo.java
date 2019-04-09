package cn.javaexception.vo;

public class AlipayQueryResponseVo {


    /**
     * alipay_trade_query_response : {"code":"10000","msg":"Success","buyer_logon_id":"tuk***@sandbox.com","buyer_pay_amount":"0.00","buyer_user_id":"2088102177220437","buyer_user_type":"PRIVATE","invoice_amount":"0.00","out_trade_no":"61abf270-cbb9-49c8-bd24-735448760ee7","point_amount":"0.00","receipt_amount":"0.00","send_pay_date":"2019-04-09 15:46:27","total_amount":"15.22","trade_no":"2019040922001420431000044233","trade_status":"TRADE_SUCCESS"}
     * sign : uelnXJA/VfZr5Nwe45BgWbTW5KwXJ+Hg6Mq3p8gg5J1uBFJSCaT/r0wtD2VyuDr4aak7fOMs9g65ENn9sPcLU6uyIiI5BWp0LUUxHlMLq6K81EET86MebW0/DnLT5Ftz3MekC90037GGFZ1JBCbbAVuURfcpuz5PWfQtoLnNVk03x8hF6aAfTRikuvm7jNTL6L78OkDzIIV7/1mLeLfLJaPjHJfRwzutDQUBvDnsYf1+u/KUkmFwozC3Xt4WdbEnekX1UI6EXAEC+scXNaxlgAA6PRh+NXZI+1mtihFWb+Q0rW9Vnj1TfhkcxYy26qifeZxFx5wEpOe+fwTB9PRYdQ==
     */

    private AlipayTradeQueryResponseBean alipay_trade_query_response;
    private String sign;

    public AlipayTradeQueryResponseBean getAlipay_trade_query_response() {
        return alipay_trade_query_response;
    }

    public void setAlipay_trade_query_response(AlipayTradeQueryResponseBean alipay_trade_query_response) {
        this.alipay_trade_query_response = alipay_trade_query_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayTradeQueryResponseBean {
        /**
         * code : 10000
         * msg : Success
         * buyer_logon_id : tuk***@sandbox.com
         * buyer_pay_amount : 0.00
         * buyer_user_id : 2088102177220437
         * buyer_user_type : PRIVATE
         * invoice_amount : 0.00
         * out_trade_no : 61abf270-cbb9-49c8-bd24-735448760ee7
         * point_amount : 0.00
         * receipt_amount : 0.00
         * send_pay_date : 2019-04-09 15:46:27
         * total_amount : 15.22
         * trade_no : 2019040922001420431000044233
         * trade_status : TRADE_SUCCESS
         */

        private String code;
        private String msg;
        private String buyer_logon_id;
        private String buyer_pay_amount;
        private String buyer_user_id;
        private String buyer_user_type;
        private String invoice_amount;
        private String out_trade_no;
        private String point_amount;
        private String receipt_amount;
        private String send_pay_date;
        private String total_amount;
        private String trade_no;
        private String trade_status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getBuyer_logon_id() {
            return buyer_logon_id;
        }

        public void setBuyer_logon_id(String buyer_logon_id) {
            this.buyer_logon_id = buyer_logon_id;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public String getBuyer_user_id() {
            return buyer_user_id;
        }

        public void setBuyer_user_id(String buyer_user_id) {
            this.buyer_user_id = buyer_user_id;
        }

        public String getBuyer_user_type() {
            return buyer_user_type;
        }

        public void setBuyer_user_type(String buyer_user_type) {
            this.buyer_user_type = buyer_user_type;
        }

        public String getInvoice_amount() {
            return invoice_amount;
        }

        public void setInvoice_amount(String invoice_amount) {
            this.invoice_amount = invoice_amount;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPoint_amount() {
            return point_amount;
        }

        public void setPoint_amount(String point_amount) {
            this.point_amount = point_amount;
        }

        public String getReceipt_amount() {
            return receipt_amount;
        }

        public void setReceipt_amount(String receipt_amount) {
            this.receipt_amount = receipt_amount;
        }

        public String getSend_pay_date() {
            return send_pay_date;
        }

        public void setSend_pay_date(String send_pay_date) {
            this.send_pay_date = send_pay_date;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getTrade_status() {
            return trade_status;
        }

        public void setTrade_status(String trade_status) {
            this.trade_status = trade_status;
        }
    }
}
