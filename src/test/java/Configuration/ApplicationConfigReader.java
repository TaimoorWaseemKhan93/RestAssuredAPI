package Configuration;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath({"ApplicationConfig.properties"})
public class ApplicationConfigReader {
    @Property("BaseURL")
    private String BaseURL;

    @Property("APIKey")
    private String APIKey;

    @Property("conversionAmount")
    private String conversionAmount;

    @Property("conversionCurrency")
    private String conversionCurrency;

    @Property("chromeUrl")
    private String Url;

    @Property("APIv1")
    private String v1;

    @Property("APIv2")
    private String v2;

    public ApplicationConfigReader() {
        PropertyLoader.newInstance().populate(this);
    }

    public String getBaseURL(){return BaseURL; }

    public String getAPIKey(){return APIKey; }

    public String getConversionAmount(){return conversionAmount; }

    public String getConversionCurrency(){return conversionCurrency; }

    public String getAPIv1(){return v1; }

    public String getAPIv2(){return v2; }

    public String getUrl() {
        return this.Url;
    }

}
