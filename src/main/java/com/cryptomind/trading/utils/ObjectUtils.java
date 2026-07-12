package com.cryptomind.trading.utils;

import java.util.Map;
import java.util.UUID;

import org.springframework.cglib.beans.BeanMap;

import com.google.common.collect.Maps;

/**
 * Created by chengli.wang on 2018/11/28.
 */
public class ObjectUtils {

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
            map.put("uuid", UUID.randomUUID().toString());
        }

        return map;
    }

    
    /*
    public static EntrustEs dataEntrust2EntrustEs(Entrust entrust) {
        EntrustEs entrustEs = new EntrustEs();
        entrustEs.setFid(entrust.getId());
        entrustEs.setFreqNo(entrust.getFreqNo());
        entrustEs.setFtrademapping(entrust.getSymbol());
        entrustEs.setFusFid(entrust.getUserId());
        entrustEs.setFentrusttype(entrust.getEntrustType());
        entrustEs.setFprize(entrust.getPrice());
        entrustEs.setFamount(entrust.getAmount());
        entrustEs.setFfees(entrust.getFees());
        entrustEs.setFleftfees(entrust.getLeftfeest());
        entrustEs.setFsuccessamount(entrust.getSuccessAmount());
        entrustEs.setFcount(entrust.getCount());
        entrustEs.setFleftcount(entrust.getLeftCount());
        entrustEs.setFstatus(entrust.getStatus());
        entrustEs.setFislimit(entrust.getIsLimit());
        entrustEs.setFcreatetime(entrust.getCreateTime());
        entrustEs.setFlastupdattime(entrust.getUpdateTime());
        entrustEs.setFhassubscription(entrust.getHasSubscription());
        entrustEs.setVersion(entrust.getVersion());
//               entrustEs.setFissend();
        return entrustEs;
    }


    public static Entrust dataEntrustEsToEntrust(EntrustEs entrustEs) {
        if (Utils.isBlank(entrustEs)) {
            return null;
        }
        Entrust entrust = new Entrust();
        entrust.setId(entrustEs.getFid());
        entrust.setFreqNo(entrustEs.getFreqNo());
        entrust.setSymbol(entrustEs.getFtrademapping());
        entrust.setUserId(entrustEs.getFusFid());
        entrust.setEntrustType(entrustEs.getFentrusttype());
        entrust.setPrice(entrustEs.getFprize());
        entrust.setAmount(entrustEs.getFamount());
        entrust.setFees(entrustEs.getFfees());
        entrust.setLeftfeest(entrustEs.getFleftfees());
        entrust.setSuccessAmount(entrustEs.getFsuccessamount());
        entrust.setCount(entrustEs.getFcount());
        entrust.setLeftCount(entrustEs.getFleftcount());
        entrust.setStatus(entrustEs.getFstatus());
        entrust.setIsLimit(entrustEs.getFislimit());
        entrust.setCreateTime(entrustEs.getFcreatetime());
        entrust.setUpdateTime(entrustEs.getFlastupdattime());
        entrust.setHasSubscription(entrustEs.getFhassubscription());
        entrust.setVersion(entrust.getVersion());
        return entrust;
    }


    public static EntrustLog dataEntrustLogEsToEntrustLog(EntrustLogEs entrustLogEs) {
        if (Utils.isBlank(entrustLogEs)) {
            return null;
        }
        EntrustLog entrustLog = new EntrustLog();
        entrustLog.setId(entrustLogEs.getFid());
        entrustLog.setFenFid(entrustLogEs.getFenFid());
        entrustLog.setFamount(entrustLogEs.getFamount());
        entrustLog.setFcreatetime(entrustLogEs.getFcreatetime());
        entrustLog.setFprize(entrustLogEs.getFprize());
        entrustLog.setFcount(entrustLogEs.getFcount());
        entrustLog.setVersion(entrustLogEs.getVersion());
        entrustLog.setIsactive(entrustLogEs.isIsactive());
        entrustLog.setFtrademapping(entrustLogEs.getFtrademapping());
        entrustLog.setFentrusttype(entrustLogEs.getFentrusttype());
        entrustLog.setFfees(entrustLogEs.getFfees());
        entrustLog.setUid(entrustLogEs.getUid());
        entrustLog.setAdversaryEnfid(entrustLogEs.getAdversaryEnfid());
        entrustLog.setAccountmark(entrustLogEs.getAccountmark());
        entrustLog.setFupdatetime(entrustLogEs.getFupdatetime());
        entrustLog.setWalletid(entrustLogEs.getWalletid());
        return entrustLog;
    }
    public static List<EntrustLog> dataEntrustLogEsToEntrustLogList(List<EntrustLogEs> entrustLogEsList) {
        if (Utils.isEmpty(entrustLogEsList)) {
            return Lists.newArrayList();
        }
        List<EntrustLog> entrustLogList = new ArrayList<>(entrustLogEsList.size());
        for (EntrustLogEs entrustLogEs : entrustLogEsList) {
            entrustLogList.add(dataEntrustLogEsToEntrustLog(entrustLogEs));
        }
        return entrustLogList;
    }

    public static List<Entrust> dataEntrustEsToEntrustList(List<EntrustEs> entrustEsList) {
        if (Utils.isEmpty(entrustEsList)) {
            return Lists.newArrayList();
        }
        List<Entrust> entrustList = new ArrayList<>(entrustEsList.size());
        for (EntrustEs entrustEs : entrustEsList) {
            entrustList.add(dataEntrustEsToEntrust(entrustEs));
        }
        return entrustList;
    }
	*/
}
