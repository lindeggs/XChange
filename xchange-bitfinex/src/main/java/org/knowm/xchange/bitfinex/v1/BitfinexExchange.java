package org.knowm.xchange.bitfinex.v1;

import java.io.IOException;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitfinex.v1.service.polling.BitfinexAccountService;
import org.knowm.xchange.bitfinex.v1.service.polling.BitfinexMarketDataService;
import org.knowm.xchange.bitfinex.v1.service.polling.BitfinexTradeService;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.AtomicLongIncrementalTime2013NonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class BitfinexExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new AtomicLongIncrementalTime2013NonceFactory();

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new BitfinexMarketDataService(this);
    this.pollingAccountService = new BitfinexAccountService(this);
    this.pollingTradeService = new BitfinexTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.bitfinex.com/");
    exchangeSpecification.setHost("api.bitfinex.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("BitFinex");
    exchangeSpecification.setExchangeDescription("BitFinex is a bitcoin exchange.");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

  @Override
  public void remoteInit() throws IOException, ExchangeException {

    // TODO Implement this. Should implement the `/symbols_details` endpoint  at http://docs.bitfinex.com/#symbols too , and build a complete ExchangeMetaData object.
    //    List<String> symbols = ((BitfinexMarketDataServiceRaw) pollingMarketDataService).getBitfinexSymbols();
    // TODO take all the info and create a `ExchangeMetaData` object via a new method in `BitfinexAdapters`
    //    exchangeMetaData = BitfinexAdapters.adaptToExchangeMetaData(blah, blah);

    super.remoteInit();
  }

}
