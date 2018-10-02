package com.springboot.demo001;

import com.google.common.io.Files;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LuckDraw {

    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat("EEE, MMM dd, yyyy");
        List<String> lines = Files.readLines(new File(LuckDraw.class.getClassLoader().getResource("result").getFile()), Charset.defaultCharset());
        List<Integer> subList;
        Map<Date, List<Integer>> maps = new TreeMap<>();
        Date lastDate = null;
        for(int index = 0; index < lines.size(); index++) {
            String _temp = lines.get(index).trim();
            if(_temp.isEmpty()) {
                continue;
            }
            Date date;
            try{
                date = df.parse(_temp);
                lastDate = date;
                maps.put(date, new ArrayList<>());
                continue;
            } catch (Exception e) {
            }
            subList = maps.get(lastDate);
            subList.add(Integer.parseInt(_temp));
        }

        Iterator<Map.Entry<Date, List<Integer>>> iterator = maps.entrySet().iterator();
        List<LuckDrawBean> beans = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Date, List<Integer>> entry = iterator.next();
            Date date = entry.getKey();
            if(entry.getValue().size()!=7) {
                System.out.println(df.format(entry.getKey()));
            }
            List<Integer> longList = entry.getValue().subList(0,5);
            List<Integer> shortList = entry.getValue().subList(5,7);
            beans.add(new LuckDrawBean(date, longList, shortList));
        }

        Analyze01(beans);
    }

    public static void Analyze01(List<LuckDrawBean> beans) {

        Map<Integer, Integer> maps = new HashMap<>();
        for(int index = 1; index <= 50; index++) {
            maps.put(index, 0);
        }

        for(int index = 0; index < beans.size(); index++) {
            //List<Integer> list = beans.get(index).getLongList();
            List<Integer> list = beans.get(index).getShortList();
            for(Integer number : list) {
                int value = maps.get(number);
                value++;
                maps.put(number, value);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> entryIterator = maps.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = entryIterator.next();
            System.out.println(entry.getKey() + ":" + devideFunction(entry.getValue(), beans.size()));
        }

    }

    public static String devideFunction(int diliverNum, int queryMailNum) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float)diliverNum/(float)queryMailNum*100);
    }

    private static class LuckDrawBean {
        private Date date;
        private List<Integer> longList;
        private List<Integer> shortList;

        public LuckDrawBean(Date date, List<Integer> longList, List<Integer> shortList) {
            this.date = date;
            this.longList = longList;
            this.shortList = shortList;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<Integer> getLongList() {
            return longList;
        }

        public void setLongList(List<Integer> longList) {
            this.longList = longList;
        }

        public List<Integer> getShortList() {
            return shortList;
        }

        public void setShortList(List<Integer> shortList) {
            this.shortList = shortList;
        }
    }
}
