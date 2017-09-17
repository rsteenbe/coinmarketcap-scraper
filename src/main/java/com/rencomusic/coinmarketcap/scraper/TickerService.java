//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-17 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class TickerService {

    public static Map<String, Ticker> createTickerList(String json) {
        Map<String, Ticker> dataMap = new HashMap<String, Ticker>();
        Ticker ticker;
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(json);
            JSONArray array = (JSONArray)obj;
            for( int i = 0; i < array.size(); i++) {
                Object obj2 = parser.parse(array.get(i).toString());
                JSONObject coinData = (JSONObject)obj2;

                ticker = new Ticker();
                ticker.setId((String) coinData.get("id"));
                ticker.setName((String) coinData.get("name"));
                ticker.setSymbol((String) coinData.get("symbol"));
                ticker.setRank((String) coinData.get("rank"));
                ticker.setPriceUsd((String) coinData.get("price_usd"));
                ticker.setPriceBtc((String) coinData.get("price_btc"));
                ticker.setDailyVolumeUsd((String) coinData.get("24h_volume_usd"));
                ticker.setMarketCapUsd((String) coinData.get("market_cap_usd"));
                ticker.setAvailableSupply((String) coinData.get("available_supply"));
                ticker.setTotalSupply((String) coinData.get("total_supply"));
                ticker.setPercentChange1h((String) coinData.get("percent_change_1h"));
                ticker.setPercentChange24h((String) coinData.get("percent_change_24h"));
                ticker.setPercentChange7d((String) coinData.get("percent_change_7d"));
                ticker.setLastUpdated((String) coinData.get("last_updated"));
                ticker.setPriceEur((String) coinData.get("price_eur"));
                ticker.setVolumeEur((String) coinData.get("volume_eur"));
                ticker.setMarketCapEur((String) coinData.get("market_cap_eur"));

                dataMap.put(coinData.get("id").toString(), ticker);
            }
        } catch(ParseException pe){

            System.out.println("position: " + parser.getPosition());
            System.out.println(pe);
        }
        return dataMap;
    }
}
