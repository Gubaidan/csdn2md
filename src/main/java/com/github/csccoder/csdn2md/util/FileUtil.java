package com.github.csccoder.csdn2md.util;

import com.github.csccoder.csdn2md.model.Article;
import com.github.csccoder.csdn2md.util.html2markdown.HTML2Md;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private  static String htmlDir;



    private  static String mdDir;

    public FileUtil(String path) {
        htmlDir = path + "/html/";
        mdDir = path + "/md/";
    }

    private  void save(String content, String filePath) {
        File file = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private  void saveHtml(Article article) {
        String filePath = htmlDir + article.getTitle();
        save(article.getContent(), filePath);

    }

    private  void saveHexomd(Article article) {
        String mdhead = HexoMdUtil.getHeader(article);
        try {
            String mdContent = HTML2Md.convertFile(new File(htmlDir + article.getTitle()), "utf-8");
            String realContent = mdhead + mdContent;
            String filePath = mdDir + article.getTitle() + ".md";
            save(realContent, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public  void html2HexoMd(Article article){
        saveHtml(article);
        saveHexomd(article);
    }

}
