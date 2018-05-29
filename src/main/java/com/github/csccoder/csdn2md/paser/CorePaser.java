package com.github.csccoder.csdn2md.paser;

import com.github.csccoder.csdn2md.model.Article;
import com.github.csccoder.csdn2md.util.FileUtil;
import com.github.csccoder.csdn2md.util.HttpClientUtil;
import com.github.csccoder.csdn2md.util.RegexUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.List;


public class CorePaser {


    /**
     * 解析博客的入口函数
     *
     * @param host   csdn域名
     * @param author csdn账号
     */
    public void parse(String host, String author,String path, boolean img){
        Document document;
        FileUtil fileUtil = new FileUtil(path,img);
        int recordCount = 0;
        int pageCount = 1;
        while (true) {
            System.out.println("正在爬去第" + pageCount + "页");
            try{
                document = getDocument(host + "/" + author + "/article/list/" + pageCount);
                //获得当前页所有文章的URI
                List<String> uris = parseArticleURIs(document);
                if(uris.size() == 0){
                    break;
                }
                for (String uri : uris) {
                    Article article = ArticlePaser.parseArticle(uri);
                    System.out.println("第"+recordCount+"篇  =>" + article.getId() + "  " + article.getTitle());
                    fileUtil.html2HexoMd(article);
                    recordCount++;
                }
                pageCount++;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    public static Document getDocument(String url) {
        String content = HttpClientUtil.get(url);
        Document document = Jsoup.parse(content);
        return document;
    }


    public ArrayList<String> parseArticleURIs(Document document) {
        ArrayList<String> ids = new ArrayList();
        Elements elements = document.select(".article-item-box>h4>a");
        for (Element element : elements) {
            ids.add(element.attr("href"));
        }
        return ids;
    }

    public int getRecordCount(String papeList) {
        String value = RegexUtil.match("\\s*?(\\d*)条.*", papeList, 1);
        return value == null ? 0 : Integer.parseInt(value);
    }

    public int getPageCount(String pageList) {
        String value = RegexUtil.match(".*共(\\d*)页", pageList, 1);
        return value == null ? 0 : Integer.parseInt(value);
    }


}
