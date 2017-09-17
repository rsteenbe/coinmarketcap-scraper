//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-17 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Coins {
    private ArrayList<String> coins = new ArrayList<String>();

    public ArrayList<String> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<String> coins) {
        this.coins = coins;
    }

    public Coins() {
        Properties properties = new Properties();
        InputStream input = null;
        String coinProperty = null;
        try {
            input = input = Coins.class.getClassLoader().getResourceAsStream("coinmarketcap-scraper.properties");
            properties.load(input);
            coinProperty = properties.getProperty("coins");
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
        String[] coin = coinProperty.split(",");
        for (int i = 0; i < coin.length; i++) {
            coins.add(coin[i]);
        }
    }
}
