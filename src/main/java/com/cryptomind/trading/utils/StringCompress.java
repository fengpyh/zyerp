package com.cryptomind.trading.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @Auther:ryu
 * @Date:2019-08-27 14:37
 * @Description
 */
@Slf4j
public class StringCompress {
    /**
     * 压缩字符串
     *
     * @param str：正常的需要压缩的字符串
     * @return 压缩后的字符串
     **/
    public static byte[] compress(String str) {
        byte[] result = null;
        try {
            result = str.getBytes("UTF-8");
            if (str.length() <= 0) {
                return result;
            }
            byte[] inputBytes = str.getBytes();
            int len = 0;
            Deflater compress = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
            compress.reset();
            compress.setInput(inputBytes);
            compress.finish();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(inputBytes.length);
            try {
                byte[] buf = new byte[1024];
                while (!compress.finished()) {
                    // 压缩并将压缩后的内容输出到字节输出流bos中
                    len = compress.deflate(buf);
                    bos.write(buf, 0, len);
                }
                result = bos.toByteArray();
            } finally {
                bos.close();
            }
            compress.end();
            return result;
        } catch (Exception ex) {
            log.error("compress data exception", ex);
            return result;
        }
    }


    /**
     * 解压缩字符串
     *
     * @param str：正常的需要解压缩的字符串
     * @return 解压缩后的字符串
     **/
    public static String uncompress(byte[] str) {
        try {
            int len = 0;
            Inflater inflater = new Inflater(true);
            inflater.setInput(str, 0, str.length);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] outByte = new byte[1024];
            try {
                while (!inflater.finished()) {
                    // 解压缩并将解压缩后的内容输出到字节输出流bos中
                    len = inflater.inflate(outByte);
                    if (len == 0) {
                        break;
                    }
                    bos.write(outByte, 0, len);
                }
                inflater.end();
            } finally {
                bos.close();
            }
            return new String(bos.toByteArray(), 0, bos.toByteArray().length, "UTF-8");
        } catch (Exception ex) {
            log.error("uncompress data exception", ex);
            return "";
        }
    }


    public static void main(String[] args) {
        String depth = "{\"code\":0,\"data\":{\"buys\":[{\"price\":\"10216.17\",\"count\":\"1\",\"amount\":\"0.65019\",\"total\":\"0.65019\"},{\"price\":\"10215.58\",\"count\":\"1\",\"amount\":\"1.08808\",\"total\":\"1.73827\"},{\"price\":\"10213.13\",\"count\":\"1\",\"amount\":\"0.89958\",\"total\":\"2.63785\"},{\"price\":\"10213.02\",\"count\":\"1\",\"amount\":\"0.6735\",\"total\":\"3.31135\"},{\"price\":\"10212.92\",\"count\":\"1\",\"amount\":\"0.75692\",\"total\":\"4.06827\"},{\"price\":\"10210.41\",\"count\":\"1\",\"amount\":\"0.74467\",\"total\":\"4.81294\"},{\"price\":\"10209.75\",\"count\":\"1\",\"amount\":\"1.14615\",\"total\":\"5.95909\"},{\"price\":\"10203.52\",\"count\":\"1\",\"amount\":\"0.14664\",\"total\":\"6.10573\"},{\"price\":\"10201.11\",\"count\":\"1\",\"amount\":\"0.54284\",\"total\":\"6.64857\"},{\"price\":\"10200.73\",\"count\":\"1\",\"amount\":\"1.01174\",\"total\":\"7.66031\"},{\"price\":\"10198.79\",\"count\":\"1\",\"amount\":\"0.51915\",\"total\":\"8.17946\"},{\"price\":\"10198.68\",\"count\":\"1\",\"amount\":\"0.17575\",\"total\":\"8.35521\"},{\"price\":\"10197.87\",\"count\":\"1\",\"amount\":\"0.23957\",\"total\":\"8.59478\"},{\"price\":\"10196.26\",\"count\":\"1\",\"amount\":\"0.01798\",\"total\":\"8.61276\"},{\"price\":\"10196.23\",\"count\":\"1\",\"amount\":\"0.79405\",\"total\":\"9.40681\"},{\"price\":\"10195.70\",\"count\":\"1\",\"amount\":\"0.80526\",\"total\":\"10.21207\"},{\"price\":\"10195.26\",\"count\":\"1\",\"amount\":\"0.00693\",\"total\":\"10.219\"},{\"price\":\"10195.15\",\"count\":\"1\",\"amount\":\"1.35033\",\"total\":\"11.56933\"},{\"price\":\"10194.68\",\"count\":\"1\",\"amount\":\"0.32288\",\"total\":\"11.89221\"},{\"price\":\"10193.97\",\"count\":\"1\",\"amount\":\"0.19953\",\"total\":\"12.09174\"},{\"price\":\"10193.23\",\"count\":\"1\",\"amount\":\"0.07714\",\"total\":\"12.16888\"},{\"price\":\"10193.09\",\"count\":\"1\",\"amount\":\"0.12299\",\"total\":\"12.29187\"},{\"price\":\"10192.67\",\"count\":\"1\",\"amount\":\"0.60771\",\"total\":\"12.89958\"},{\"price\":\"10192.09\",\"count\":\"1\",\"amount\":\"0.13773\",\"total\":\"13.03731\"},{\"price\":\"10190.89\",\"count\":\"1\",\"amount\":\"0.26323\",\"total\":\"13.30054\"},{\"price\":\"10188.36\",\"count\":\"1\",\"amount\":\"0.30585\",\"total\":\"13.60639\"},{\"price\":\"10178.74\",\"count\":\"1\",\"amount\":\"0.27573\",\"total\":\"13.88212\"},{\"price\":\"10173.77\",\"count\":\"1\",\"amount\":\"0.07722\",\"total\":\"13.95934\"},{\"price\":\"10170.00\",\"count\":\"1\",\"amount\":\"0.23089\",\"total\":\"14.19023\"},{\"price\":\"10165.95\",\"count\":\"1\",\"amount\":\"0.02861\",\"total\":\"14.21884\"},{\"price\":\"10100.00\",\"count\":\"1\",\"amount\":\"0.00912\",\"total\":\"14.22796\"},{\"price\":\"10075.00\",\"count\":\"1\",\"amount\":\"0.01088\",\"total\":\"14.23884\"},{\"price\":\"10059.39\",\"count\":\"1\",\"amount\":\"0.00504\",\"total\":\"14.24388\"},{\"price\":\"10051.12\",\"count\":\"1\",\"amount\":\"0.00632\",\"total\":\"14.2502\"},{\"price\":\"10050.00\",\"count\":\"1\",\"amount\":\"0.06116\",\"total\":\"14.31136\"},{\"price\":\"10040.50\",\"count\":\"1\",\"amount\":\"0.09956\",\"total\":\"14.41092\"},{\"price\":\"10012.12\",\"count\":\"2\",\"amount\":\"0.00872\",\"total\":\"14.41964\"},{\"price\":\"10000.00\",\"count\":\"3\",\"amount\":\"0.15704\",\"total\":\"14.57668\"},{\"price\":\"9999.22\",\"count\":\"1\",\"amount\":\"0.00503\",\"total\":\"14.58171\"},{\"price\":\"9999.00\",\"count\":\"1\",\"amount\":\"0.056\",\"total\":\"14.63771\"},{\"price\":\"9991.20\",\"count\":\"2\",\"amount\":\"0.01199\",\"total\":\"14.6497\"},{\"price\":\"9986.05\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.6499\"},{\"price\":\"9986.00\",\"count\":\"1\",\"amount\":\"0.01206\",\"total\":\"14.66196\"},{\"price\":\"9985.78\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.66216\"},{\"price\":\"9984.56\",\"count\":\"1\",\"amount\":\"0.00199\",\"total\":\"14.66415\"},{\"price\":\"9971.20\",\"count\":\"1\",\"amount\":\"0.0236\",\"total\":\"14.68775\"},{\"price\":\"9969.10\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.68795\"},{\"price\":\"9968.41\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.68815\"},{\"price\":\"9966.28\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.68835\"},{\"price\":\"9965.19\",\"count\":\"1\",\"amount\":\"0.0002\",\"total\":\"14.68855\"},{\"price\":\"9962.00\",\"count\":\"1\",\"amount\":\"0.01612\",\"total\":\"14.70467\"}],\"sells\":[{\"price\":\"10227.88\",\"count\":\"1\",\"amount\":\"0.82315\",\"total\":\"0.82315\"},{\"price\":\"10227.89\",\"count\":\"1\",\"amount\":\"0.48566\",\"total\":\"1.30881\"},{\"price\":\"10228.21\",\"count\":\"1\",\"amount\":\"1.23689\",\"total\":\"2.5457\"},{\"price\":\"10228.87\",\"count\":\"1\",\"amount\":\"0.4301\",\"total\":\"2.9758\"},{\"price\":\"10230.63\",\"count\":\"1\",\"amount\":\"0.6695\",\"total\":\"3.6453\"},{\"price\":\"10231.39\",\"count\":\"1\",\"amount\":\"1.25656\",\"total\":\"4.90186\"},{\"price\":\"10232.28\",\"count\":\"1\",\"amount\":\"0.75199\",\"total\":\"5.65385\"},{\"price\":\"10234.90\",\"count\":\"1\",\"amount\":\"1.03057\",\"total\":\"6.68442\"},{\"price\":\"10234.91\",\"count\":\"1\",\"amount\":\"0.29165\",\"total\":\"6.97607\"},{\"price\":\"10236.21\",\"count\":\"1\",\"amount\":\"1.38766\",\"total\":\"8.36373\"},{\"price\":\"10237.84\",\"count\":\"1\",\"amount\":\"0.61462\",\"total\":\"8.97835\"},{\"price\":\"10325.85\",\"count\":\"1\",\"amount\":\"0.04359\",\"total\":\"9.02194\"},{\"price\":\"10413.05\",\"count\":\"1\",\"amount\":\"0.09053\",\"total\":\"9.11247\"},{\"price\":\"10500.00\",\"count\":\"2\",\"amount\":\"0.00171\",\"total\":\"9.11418\"},{\"price\":\"10601.68\",\"count\":\"1\",\"amount\":\"0.00094\",\"total\":\"9.11512\"},{\"price\":\"10737.01\",\"count\":\"1\",\"amount\":\"0.00181\",\"total\":\"9.11693\"},{\"price\":\"10744.92\",\"count\":\"2\",\"amount\":\"0.006\",\"total\":\"9.12293\"},{\"price\":\"10756.28\",\"count\":\"1\",\"amount\":\"0.2045\",\"total\":\"9.32743\"},{\"price\":\"10763.10\",\"count\":\"1\",\"amount\":\"0.34463\",\"total\":\"9.67206\"},{\"price\":\"10771.68\",\"count\":\"1\",\"amount\":\"0.29231\",\"total\":\"9.96437\"},{\"price\":\"10800.63\",\"count\":\"1\",\"amount\":\"0.82554\",\"total\":\"10.78991\"},{\"price\":\"10809.36\",\"count\":\"1\",\"amount\":\"0.00051\",\"total\":\"10.79042\"},{\"price\":\"10842.52\",\"count\":\"1\",\"amount\":\"0.89346\",\"total\":\"11.68388\"},{\"price\":\"10853.21\",\"count\":\"1\",\"amount\":\"0.81486\",\"total\":\"12.49874\"},{\"price\":\"10909.00\",\"count\":\"1\",\"amount\":\"0.13382\",\"total\":\"12.63256\"},{\"price\":\"10909.86\",\"count\":\"1\",\"amount\":\"0.89185\",\"total\":\"13.52441\"},{\"price\":\"10920.00\",\"count\":\"1\",\"amount\":\"0.00791\",\"total\":\"13.53232\"},{\"price\":\"10929.29\",\"count\":\"1\",\"amount\":\"1.1566\",\"total\":\"14.68892\"},{\"price\":\"10948.06\",\"count\":\"1\",\"amount\":\"0.74776\",\"total\":\"15.43668\"},{\"price\":\"10975.27\",\"count\":\"1\",\"amount\":\"0.70656\",\"total\":\"16.14324\"},{\"price\":\"11000.00\",\"count\":\"1\",\"amount\":\"0.01186\",\"total\":\"16.1551\"},{\"price\":\"11195.57\",\"count\":\"1\",\"amount\":\"0.06502\",\"total\":\"16.22012\"},{\"price\":\"11206.82\",\"count\":\"1\",\"amount\":\"0.00013\",\"total\":\"16.22025\"},{\"price\":\"11425.00\",\"count\":\"1\",\"amount\":\"0.00388\",\"total\":\"16.22413\"},{\"price\":\"11475.00\",\"count\":\"1\",\"amount\":\"0.00319\",\"total\":\"16.22732\"},{\"price\":\"11635.00\",\"count\":\"1\",\"amount\":\"0.01056\",\"total\":\"16.23788\"},{\"price\":\"11777.25\",\"count\":\"1\",\"amount\":\"0.00112\",\"total\":\"16.239\"},{\"price\":\"12000.00\",\"count\":\"2\",\"amount\":\"0.03329\",\"total\":\"16.27229\"},{\"price\":\"12007.40\",\"count\":\"1\",\"amount\":\"0.00646\",\"total\":\"16.27875\"},{\"price\":\"12100.00\",\"count\":\"1\",\"amount\":\"1.65543\",\"total\":\"17.93418\"},{\"price\":\"12200.00\",\"count\":\"2\",\"amount\":\"0.01972\",\"total\":\"17.9539\"},{\"price\":\"12233.00\",\"count\":\"1\",\"amount\":\"0.00114\",\"total\":\"17.95504\"},{\"price\":\"12266.11\",\"count\":\"1\",\"amount\":\"0.0003\",\"total\":\"17.95534\"},{\"price\":\"12300.00\",\"count\":\"1\",\"amount\":\"0.111\",\"total\":\"18.06634\"},{\"price\":\"12388.00\",\"count\":\"1\",\"amount\":\"1.58151\",\"total\":\"19.64785\"},{\"price\":\"12405.12\",\"count\":\"1\",\"amount\":\"0.01183\",\"total\":\"19.65968\"},{\"price\":\"12449.50\",\"count\":\"1\",\"amount\":\"0.00646\",\"total\":\"19.66614\"},{\"price\":\"12500.00\",\"count\":\"1\",\"amount\":\"0.10667\",\"total\":\"19.77281\"},{\"price\":\"12536.65\",\"count\":\"1\",\"amount\":\"0.00572\",\"total\":\"19.77853\"},{\"price\":\"12848.70\",\"count\":\"1\",\"amount\":\"0.00573\",\"total\":\"19.78426\"},{\"price\":\"13085.10\",\"count\":\"1\",\"amount\":\"0.00766\",\"total\":\"19.79192\"}]},\"precision\":2,\"subscribe\":\"depth\",\"symbol\":53,\"uuid\":\"5a0ea65f-28a9-47d7-813a-eeeebfd8263a\"}";
        System.out.println("压缩前:" + depth);
        System.out.println("压缩前长度:" + depth.getBytes().length);


        byte[] compressByte = compress(depth);
        System.out.println("压缩后长度:" + compressByte.length);

        String uncompress = uncompress(compressByte);

        System.out.println("解压后:" + uncompress);
    }
}
