package com.cryptomind.wquote;

import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;
import com.cryptomind.trading.utils.ExceptionUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Component
public class KlineBizImpl implements KlineBiz {


    @Resource
    private SymbolService symbolService;

    private final Gson gson = new Gson();

    @Override
    public void klineSaveUpdateFix(String msgStr) {
        //NOTOCE: timestamp should be in mills
        long idx = System.currentTimeMillis()%1000;

        //log.info("klineSaveUpdateFix-{}.msg: {}",idx, msgStr);

        KlineDTO_UpdateParam klineDTOParam = gson.fromJson(msgStr, KlineDTO_UpdateParam.class);

        KlineDTO klineDTO = gson.fromJson(msgStr, KlineDTO.class);

        CmSymbol tradeMappingData = symbolService.findBySymbol(klineDTO.getSymbol());
        if(tradeMappingData!=null) {
            klineDTO.setSymbol(tradeMappingData.getSymbol());
        }else{
            log.warn("TradeMappingData.NULL: {}", klineDTO.getSymbol());
        }
        //log.info("klineSaveUpdateFix-{}.klineDTO: {}",idx, klineDTO);
        //String uuid = UUID.randomUUID().toString();
        //long s = System.currentTimeMillis();

        String symbol = klineDTO.getSymbol();
        //Integer seq = klineDTO.getTradeSeq().intValue();

        KlineStep step = KlineStep.get(klineDTOParam.getStepStr());
        klineDTO.setStep(step);
        //log.info("klineSaveUpdateFix_{}.step: {}", idx, step);
        Long daySeq = Long.valueOf(Objects.requireNonNull(KlineStepUtil.getDaySeq(step.getUtilType(), klineDTO.getCt(), step.getUtilStep())));
        //log.info("klineSaveUpdateFix_{}.daySeq: {}", idx, daySeq);

        klineDTO.setOt(KlineStepUtil.getOpenTimeStamp(step.getUtilType(), klineDTO.getTs(), step.getUtilStep()) );
        klineDTO.setCt(KlineStepUtil.getCloseTimeStamp(step.getUtilType(), klineDTO.getTs(), step.getUtilStep()));

        //"vu": 0, "va":0, "cp":0, "ca":0,
        //log.info("klineSaveUpdateFix-{}.msg: {}, {}",idx, msgStr, klineDTO);
        log.info("klineSaveUpdateFix-{}.msg.String: {}",idx, msgStr);
        log.info("klineSaveUpdateFix-{}.msg.KlineDTO: {}",idx, klineDTO);
        try {
            //TODO FIX
            //klineMongoDao.saveAndUpdateKline(klineDTO);
            //KlineDTO dbItem = klineRepository.selectKline(tradeMappingId, step, daySeq);
            //log.info("klineSaveUpdateFix_{}.klineSaved:  {}", idx, dbItem);

        } catch (Exception e) {
            log.error("klineSaveUpdateFixERROR_{}, {} step={}, exception={}", idx, symbol, step, ExceptionUtil.toString(e));
        }
    }
}
