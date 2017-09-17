//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-17 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import java.io.*;

public class CSVWriter {

    public static void writeData(String data, File file) {
        Boolean fileExists = false;
        if (fileExists = !file.exists()) {
            try {
                File dir = new File(file.getParent());
                if (!dir.exists())
                    dir.mkdirs();

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (fileExists) {
                String csvTopLine = "" +
                        "\"PRICE_BTC\"," +
                        "\"PRICE_USD\"," +
                        "\"DAILY_VOLUME_USD\"," +
                        "\"MARKET_CAP_USD\"," +
                        "\"PRICE_EUR\"," +
                        "\"DAILY_VOLUME_EUR\"," +
                        "\"MARKET_CAP_EUR\"," +
                        "\"TOTAL_SUPPLY\"," +
                        "\"AVAILABLE_SUPPLY\"," +
                        "\"PERCENT_CHANGE_1H\"," +
                        "\"PERCENT_CHANGE_24H\"," +
                        "\"PERCENT_CHANGE_7D\"," +
                        "\"DATE" +
                        "\"";
                bw.write(csvTopLine);
                bw.write(System.getProperty("line.separator"));
            }
            bw.write(data);
            bw.write(System.getProperty("line.separator"));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}