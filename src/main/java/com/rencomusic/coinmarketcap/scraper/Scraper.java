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

public class Scraper {
    private static String COIN_MARKET_CAP_URL;

    Scraper(String coinMarketCapUrl) {
        COIN_MARKET_CAP_URL = coinMarketCapUrl;
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