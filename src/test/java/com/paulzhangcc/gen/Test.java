package com.paulzhangcc.gen;

import org.apache.commons.text.StringEscapeUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
public class Test {
    public static void main(String[] args) throws MalformedURLException {
        String s = "jdbc:mysql://123.56.0.93:3306/ApolloConfigDB?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        System.out.println(s);
        System.out.println(StringEscapeUtils.escapeXml10(s));
        System.out.println(StringEscapeUtils.escapeXml11(s));
        if (true){
            return;
        }
        URL url = new URL("jdbc:mysql://123.56.0.93:3306/ApolloConfigDB?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getQuery());
    }

}
