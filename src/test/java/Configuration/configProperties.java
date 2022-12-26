package Configuration;

public class configProperties {
    public static ApplicationConfigReader appConfig = new ApplicationConfigReader();

    public static String BaseURL = appConfig.getBaseURL();
    public static String APIKey = appConfig.getAPIKey();
    public static String conversionAmount = appConfig.getConversionAmount();
    public static String conversionCurrency = appConfig.getConversionCurrency();
    public static String APIv1 = appConfig.getAPIv1();
    public static String APIv2 = appConfig.getAPIv2();
    public static String Url = appConfig.getUrl();

}
