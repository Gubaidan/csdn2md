package com.github.csccoder.csdn2md;

import com.github.csccoder.csdn2md.paser.CorePaser;

import java.io.IOException;


public class Main {

    private static String host = "http://blog.csdn.net";


    public static void main(String args[]) throws IOException {




        //input your blog username
        String author = "xxxxx";
        //绝对路径，确保文件夹下有 md 和 html文件夹，需要自己新建
        String dirPath = "xxxx";
        new CorePaser().parse(host, author, dirPath);
    }
}
