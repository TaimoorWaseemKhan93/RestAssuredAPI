package MainCall;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath({"ApplicationConfig.properties"})
public class ApplicationConfigReader {
    @Property("BaseURL")
    private String BaseURL;

    @Property("APIKey")
    private String APIKey;

    @Property("amount")
    private String conversionAmount;

    @Property("currency")
    private String conversionCurrency;

    @Property("APIv1")
    private String v1;

    @Property("APIv2")
    private String v2;

    @Property("Path")
    private String path;

    @Property("Theme")
    private String Theme;

    @Property("DocumentTitle")
    private String DocumentTitle;

    @Property("ReportName")
    private String ReportName;

    public String getReportName() {
        return ReportName;
    }

    public String getDocumentTitle() {
        return DocumentTitle;
    }

    public String getTheme() {
        return Theme;
    }

    public String getPath() {
        return path;
    }

    public ApplicationConfigReader() {
        PropertyLoader.newInstance().populate(this);
    }

    public String getBase(){return BaseURL; }

    public String getKey(){return APIKey; }

    public String getAmount(){return conversionAmount; }

    public String getCurrency(){return conversionCurrency; }

    public String getv1(){return v1; }

    public String getv2(){return v2; }

}
