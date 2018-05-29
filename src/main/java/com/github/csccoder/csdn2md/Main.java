package com.github.csccoder.csdn2md;

import com.github.csccoder.csdn2md.paser.CorePaser;

import java.io.IOException;


public class Main {

    private static String host = "http://blog.csdn.net";

    public static void main(String args[]) throws IOException {

        String author = "hu_lichao";                           //csdn用户名

        String dirPath = "/Users/gubaidan/Downloads/00000/";   //文件保存路径（绝对路径）

        new CorePaser().parse(host, author, dirPath, true);  //是否爬取图片 默认false
    }
}
