# Coinmarketcap Scraper

*Author* : [Renco Steenbergen](https://www.linkedin.com/in/renco-steenbergen-87b52a119/)

*APIs* : [Coinmarketcap API](https://coinmarketcap.com/api/)

*Summary* : Scraper tool to store cryptocurrency data on a Raspberry Pi 3

*Technologies & Libraries* : Raspbian GNU/Linux 8, Java SE 1.8.0, Slf4j 1.7.25, JSONSimple 1.1.1, Maven 3.5.0, Crontab

## Purpose
The main purpose of this tool is to obtain cryptocurrency data that can be used for research purposes or technical analysis. 
This Java scraper tool can be used to store 5 minute interval data of the cryptocurrencies, coins or tokens using the Coinmarketcap API. 
The data will be stored in a CSV file, where every CSV file contains data of 1 month. 
This tool is designed to use on a Raspberry Pi, but it can also be used on other devices.

## Variables
The variables that will be stored for the coins are: 

* Price in BTC
* Price in USD
* Daily Volume in USD
* Market Cap in USD
* Price in EUR
* Daily Volume in EUR
* Market Cap in EUR
* Total Supply
* Available Supply
* Percentual Change per Hour
* Percentual Change per 24 Hour
* Percentual Change per 7 Days
* Date as a Java Timestamp

## Properties
The following properties are added in `coinmarketcap-scraper.properties`:

* `coinmarketcap-scraper.coinMarketCapApi` refers to the URL of the Coinmarketcap API.
* `coinmarketcap-scraper.storageFolder` refers to the folder on your system where the data will be stored.

* `coins` refers to the coin ID from the Coinmarketcap API and decides which coins will be scraped. The coins propery can be modified by adding other coins from the API.


