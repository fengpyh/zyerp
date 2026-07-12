package com.cryptomind.trading.utils;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by chengli.wang on 2018/10/26.
 * sql2es 工具类
 */
public class Utils {

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String SPLIT = ",|，";
    public static final String FIRST_SPLIT = "!~!";
    public static final String SECOND_SPLIT = "~";
    public static final Random RANDOM = new Random();
    public static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final Pattern BLANK_REGEX = Pattern.compile("\\s{2,}");


    public static String replaceBlank(String str) {
        return BLANK_REGEX.matcher(str).replaceAll(" ");
    }

    public static String random(int length) {
        if (length <= 0) {
            return EMPTY;
        }
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sbd.append(RANDOM.nextInt(10));
        }
        return sbd.toString();
    }

    public static Timestamp getTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    //convert table column ==> es field
    public static String columnToField(String column) {
        return CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL)
                .convert(column.toUpperCase().startsWith("C_") ? column.toUpperCase().substring(2) : column);
    }

    //convert table name ==> es type

    public static String tableToType(String table) {
        return CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_HYPHEN)
                .convert(table.toUpperCase().startsWith("T_") ? table.toUpperCase().substring(2) : table);
    }


    public static String addSuffix(String src) {
        if (isBlank(src)) {
            return "/";
        }
        if (src.endsWith("/")) {
            return src;
        }
        return src + "/";
    }


    public static boolean gerate0(Number obj) {
        return obj != null && obj.doubleValue() > 0;
    }

    public static boolean less0(Number obj) {
        return obj == null || obj.doubleValue() <= 0;
    }

    public static boolean isBlank(Object obj) {
        return obj == null || obj.toString().trim().length() == 0 || "null".equalsIgnoreCase(obj.toString().trim());
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void assertException(String msg) {
        throw new RuntimeException(msg);
    }

    public static void assertException(Boolean flag, String msg) {
        if (flag != null && flag) {
            assertException(msg);
        }
    }

    public static void assertNil(Object obj, String msg) {
        assertException(isBlank(obj), msg);
    }

    public static void assert0(Number obj, String msg) {
        assertException(less0(obj), msg);
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    //array convert string,with split
    public static String toStr(Object[] array, String split) {
        if (isEmpty(array)) {
            return Utils.EMPTY;
        }

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sbd.append(array[i]);
            if (i + 1 != array.length) {
                sbd.append(split);
            }
        }
        return sbd.toString();
    }

    public static String toStr(Object[] array) {
        return toStr(array, ",");
    }

    public static <T> String toStr(Collection<T> collection) {
        return toStr(collection.toArray(), ",");
    }


    public static <T> String toStr(Collection<T> collection, String split) {
        return toStr(collection.toArray(), split);
    }

    public static <T> List<T> lists(T... values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    public static <T> List<T> linkedLists(T... values) {
        return new LinkedList<>(Arrays.asList(values));
    }

    public static <T> Set<T> sets(T... values) {

        return new HashSet<>(Arrays.asList(values));
    }

    public static <T> Set<T> linkedSets(T... values) {
        return new LinkedHashSet<>(Arrays.asList(values));
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    //
    private static <K, V> Map<K, V> maps(Map<K, V> result, Object... keysAndValues) {
        if (isNotEmpty(keysAndValues)) {
            for (int i = 0; i < keysAndValues.length; i += 2) {
                if (keysAndValues.length > i + 1) {
                    result.put((K) keysAndValues[i], (V) keysAndValues[i + 1]);
                }
            }
        }
        return result;
    }

    public static <K, V> Map<K, V> maps(Object... keysAndValues) {
        return maps(newHashMap(), keysAndValues);
    }

    public static <K, V> Map<K, V> linkedMaps(Object... keysAndValues) {
        return maps(newLinkedHashMap(), keysAndValues);
    }

    /**
     * 获取集合中的第一个元素
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> T first(Collection<T> collection) {
        return isEmpty(collection) ? null : collection.iterator().next();
    }

    /**
     * 获取集合中的最后一个元素
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> T last(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            return list.get(list.size() - 1);
        }
        Iterator<T> iterator = collection.iterator();
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    public static void checkParamNotNull(Object... args) {
        for (Object arg : args) {
            Preconditions.checkArgument(args != null, "param must not be null");
        }
    }

    public static Integer[] parseParam(String paramStr) {
        Integer[] params = null;
        if(Utils.isNotBlank(paramStr)) {
            String[] statusStr = paramStr.split(",");
            if(Utils.isNotEmpty(statusStr)){
                params = new Integer[statusStr.length];
                for(int i=0;i<statusStr.length;i++) {
                    params[i] = Integer.valueOf(statusStr[i]);
                }
            }
        }
        return params;
    }

    public static String getDoubleZeroFill(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }
        String formatStr = "0.";
        for (int i = 0; i < scale; i++) {
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }

    private static MathContext mathContext = new MathContext(10, RoundingMode.HALF_EVEN);
    public static Double calDivide(Double a ,Double b){
        if (a==null||b==null){
            //log.error("calDivide error 参数为空");
            return 0D;
        }
        if (b.compareTo(0D)==0){
            //log.error("calDivide error by zero");
            return 0D;
        }

        BigDecimal divide = new BigDecimal(a).divide(new BigDecimal(b), mathContext);
        return divide.doubleValue();
    }


}
