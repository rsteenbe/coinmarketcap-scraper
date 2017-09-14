//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-14 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class CoinMarketCapScraper implements Scraper {
    private static String COIN_MARKET_CAP_URL;

    CoinMarketCapScraper() {
        COIN_MARKET_CAP_URL = loadProperty();
    }

    private String loadProperty() {
        Properties properties = new Properties();
        InputStream input = null;
        String property = null;
        try {
            input = new FileInputStream("coinmarketcap-scraper.properties");
            properties.load(input);
            property = properties.getProperty("coinmarketcap-scraper.coinMarketCapApi");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return property;
    }

    public String scrape() throws IOException {
        InputStream inputStream = new URL(COIN_MARKET_CAP_URL).openStream();
        String property = null;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}