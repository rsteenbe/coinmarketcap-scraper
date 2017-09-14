//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-13 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.debug("Coinmarketcap scraper running.");

        try {
            System.out.println(new CoinMarketCapScraper().scrape());
            logger.debug("Scraping done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
