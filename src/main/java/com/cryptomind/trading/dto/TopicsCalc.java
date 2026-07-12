package com.cryptomind.trading.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** @author superbyte on 27/06/2018. */
@Slf4j
@Service
public class TopicsCalc {

  // 撮合引擎调用钱包
  public static final String NEW_WALLET = "new-wallet";
  public static final String ENTRUST_MATCH_OUTBOUND = "match-outbound";

  public static final String MARKET_RETURNFEE= "market-returnfee";

  public static final String SPOT_ENTRUST_PROGRESS="spot-entrust-progress";//现货交易进度

  //订阅entrust数据保存到ES
  public static final String TASK_ENTRUST_SAVE_ES = "task-entrust-save-es";
  //订阅entrustlog数据保存到ES
  public static final String TASK_ENTRUSTLOG_SAVE_ES = "task-entrustlog-save-es";

  public static final String WSMSG_TOPIC = "ws-msg";

  public static final String WALLET_EXCHANGE_RETRY = "wallet-exchange-retry";

  public static final String WALLET_UNFROZEN_RETRY = "wallet-unfrozen-retry";

  public static final String ENTRUST_UNFROZEN_RETRY = "entrust-unfrozen-retry";

  public static final String SPOT_PRICE_CHANGE = "spot-price-change";


  public String getWalletUnfrozenRetry() {
    return WALLET_UNFROZEN_RETRY;
  }

  public String getWalletExchangeRetry() {
    return WALLET_EXCHANGE_RETRY;
  }

  public String getEntrustUnfrozenRetry() {
    return ENTRUST_UNFROZEN_RETRY;
  }

  public String getNewWallet() {
    return NEW_WALLET;
  }

  public String getWsmsgTopic() {
    return WSMSG_TOPIC;
  }

  public String getMarketReturnfee() {
    return MARKET_RETURNFEE;
  }

  public String getSpotEntrustProgress() {
    return SPOT_ENTRUST_PROGRESS;
  }

  public String getTaskEntrustlogSaveEs() {
    return TASK_ENTRUSTLOG_SAVE_ES;
  }

  public static String getSpotPriceChange() {
    return SPOT_PRICE_CHANGE;
  }
}
