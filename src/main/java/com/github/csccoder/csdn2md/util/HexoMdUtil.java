package com.github.csccoder.csdn2md.util;

import com.github.csccoder.csdn2md.model.Article;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class HexoMdUtil {
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     ---
     title: hexo deploy时重复输入用户名密码的问题
     date: 2017-12-12 19:17:34




     tags: hexo
     ---
     */
    public static String getHeader(Article article){
        StringBuilder sb=new StringBuilder();
        sb.append("---\n").
                append(String.format("title: %s\n",article.getTitle())).
                append(String.format("date: %s\n",dateFormat.format(article.getDate()))).
                append("tags: "+Arrays.toString(article.getTags())+"\n").
                append("category: "+article.getCatagory()+"\n").
                append("---\n");
        return sb.toString();
    }

    public static String array2String(String[] array){
        String str="";
        for(String temp:array){
            str+=temp;
        }
        return str;
    }



}
