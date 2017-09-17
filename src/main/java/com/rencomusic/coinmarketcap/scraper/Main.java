//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-13 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.debug("Coinmarketcap scraper running.");

        Properties properties = new Properties();
        InputStream input = null;
        String coinMarketCapUrl = null;
        String storageFolder = null;
        try {
            input = Main.class.getClassLoader().getResourceAsStream("coinmarketcap-scraper.properties");

            properties.load(input);
            coinMarketCapUrl = properties.getProperty("coinmarketcap-scraper.coinMarketCapApi");
            storageFolder = properties.getProperty("coinmarketcap-scraper.storageFolder");
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
        logger.debug("Properties loaded successfully.");

        String json = null;
        try {
            json = new Scraper(coinMarketCapUrl).scrape();
            logger.debug("Scraping done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Reading JSON from coinmarketcap.com completed.");

        Map<String, Ticker> data = TickerService.createTickerList(json);
        logger.debug("All JSON data has been put in a map with key value pair: id, ticker object.");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String now = dateFormat.format(new Date());
        logger.debug("Recent date in format yyyy-MM: " + now);


        ArrayList<String> coins = new Coins().getCoins();

        for (int i = 0; i < coins.size(); i++) {
            File file = new File(storageFolder + "/" + coins.get(i) + "/" + now + "-" + coins.get(i) + ".csv");
            String date;
            if ((String) data.get(coins.get(i)).getLastUpdated() == null) {
                date = (String) data.get(coins.get(i)).getLastUpdated();
            } else {
                date = (String) data.get(coins.get(i)).getLastUpdated() + "000";
            }
            String coinData = "" +
                    "\"" + data.get(coins.get(i)).getPriceBtc() + "\"," +
                    "\"" + data.get(coins.get(i)).getPriceUsd() + "\"," +
                    "\"" + data.get(coins.get(i)).getDailyVolumeUsd() + "\"," +
                    "\"" + data.get(coins.get(i)).getMarketCapUsd() + "\"," +
                    "\"" + data.get(coins.get(i)).getPriceEur() + "\"," +
                    "\"" + data.get(coins.get(i)).getVolumeEur() + "\"," +
                    "\"" + data.get(coins.get(i)).getMarketCapEur() + "\"," +
                    "\"" + data.get(coins.get(i)).getTotalSupply() + "\"," +
                    "\"" + data.get(coins.get(i)).getAvailableSupply() + "\"," +
                    "\"" + data.get(coins.get(i)).getPercentChange1h() + "\"," +
                    "\"" + data.get(coins.get(i)).getPercentChange24h() + "\"," +
                    "\"" + data.get(coins.get(i)).getPercentChange7d() + "\"," +
                    "\"" + date + "\"";
            CSVWriter.writeData(coinData, file);
        }
        logger.debug("Data has been written to CSV.");

    }
}
