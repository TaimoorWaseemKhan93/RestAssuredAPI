package Data;

import MainCall.configProperties;

public class CasesEndData {
    public static String map ="/"+ configProperties.v1 +"/cryptocurrency/map";
    public static String convert ="/"+configProperties.v2 +"/tools/price-conversion";
    public static String info ="/"+configProperties.v2 +"/cryptocurrency/info";
    private String bitCoin;
    private String USDT;
    private String ethereum;

    public String getBitCoinId() {
        return bitCoin;
    }
    public void setBitCoinId(String bitCoinId) {
        this.bitCoin = bitCoinId;
    }

    public String getUSDTId() {
        return USDT;
    }

    public void setUSDTId(String USDTId) {
        this.USDT = USDTId;
    }
    public String getEthereumId() {
        return ethereum;
    }

    public void setEthereumId(String ethereumId) {
        this.ethereum = ethereumId;
    }
}
