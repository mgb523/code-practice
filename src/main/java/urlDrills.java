import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urlDrills {

    @Test
    public void normalizeUrl() throws MalformedURLException {
        //final String rawUrl = "https://www.linkedin.com/search/results/all/?keywords=marquette&origin=HISTORY&sid=r-6";
        final String rawUrl = "https://www.linkedin.com/";
        String resultLink = "";

        final URL url;
        try {
            url = new URI(rawUrl).normalize().toURL();
        } catch (URISyntaxException e) {
            throw new MalformedURLException(e.getMessage());
        }

        String protocol = url.getProtocol();
        String host = url.getHost();
        String path = url.getPath();

        resultLink = protocol + "://" + host
                + (null != path ? "/" + path : "");

        String query = url.getQuery();

        if (null != query) {
            // Build query param map
            String[] params = url.getQuery().split("&");
            Map<String, String> paramMap = new HashMap<>();

            // Clean query params
            for (String p : params) {
                String[] param = p.split("=");
                if (!param[0].equals("sid")) {
                    paramMap.put(param[0], param[1]);
                }
            }

            // Rebuild query params
            String paramStr = "";
            Iterator<Map.Entry<String, String>> mapIterator = paramMap.entrySet().iterator();
            while (mapIterator.hasNext()) {
                Map.Entry<String, String> entry = mapIterator.next();
                String separator = mapIterator.hasNext() ? "&" : "";
                paramStr = paramStr + entry.getKey() + "=" + entry.getValue() + separator;
            }

            resultLink += "?" + paramStr;
        }

        System.out.println("Url = " + resultLink);
    }

    @Test
    public void parseUrl() throws MalformedURLException {
        final String url = "https://www.linkedin.com/search/results/all/?keywords=marquette&origin=HISTORY&sid=r-6";
        String resultLink = "";

        // Verify valid url
        boolean isValidUrl = IsMatch(url, "^(https?|ftp|file)://");

        if (isValidUrl) {
            // Build param map
            String[] linkArray = url.split("\\?");

            if (linkArray.length > 0) {
                String linkQuery = linkArray[1];
                String[] params = linkQuery.split("&");
                Map<String, String> paramMap = new HashMap<>();

                for (String p : params) {
                    String[] param = p.split("=");
                    if (!param[0].equals("sid")) {
                        paramMap.put(param[0], param[1]);
                    }
                }

                // Rebuild params
                String paramStr = "";
                Iterator<Map.Entry<String, String>> mapIterator = paramMap.entrySet().iterator();
                while (mapIterator.hasNext()) {
                    Map.Entry<String, String> entry = mapIterator.next();
                    String separator = mapIterator.hasNext() ? "&" : "";
                    paramStr = paramStr + entry.getKey() + "=" + entry.getValue() + separator;
                }

                resultLink = linkArray[0] + "?" + paramStr;
            }

            System.out.println("Url = " + resultLink);
        } else {
            System.out.println("Url is not valid");
        }
    }

    private static boolean IsMatch(String s, String pattern) {
        try {
            Pattern pat = Pattern.compile(pattern);
            Matcher m = pat.matcher(s);
            return m.find();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
