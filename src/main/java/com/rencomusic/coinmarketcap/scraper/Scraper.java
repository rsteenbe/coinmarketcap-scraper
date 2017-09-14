//////////////////////////////////////////////////////////////////////////
//                                                                      //
//      2017-09-14 - Created by Renco Steenbergen                       //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

package com.rencomusic.coinmarketcap.scraper;

import java.io.IOException;

public interface Scraper {
    public Object scrape() throws IOException;
}
