package MainCall;

public class configProperties {
    public static ApplicationConfigReader appConfig = new ApplicationConfigReader();

    public static String BaseURL = appConfig.getBase();
    public static String APIKey = appConfig.getKey();
    public static String path = appConfig.getPath();
    public static String theme = appConfig.getTheme();

    public static String conversionAmount = appConfig.getAmount();
    public static String conversionCurrency = appConfig.getCurrency();
    public static String documentationTitle = appConfig.getDocumentTitle();
    public static String reportName = appConfig.getReportName();
    public static String v1 = appConfig.getv1();
    public static String v2 = appConfig.getv2();


}
