package com.github.csccoder.csdn2md.util;

import com.github.csccoder.csdn2md.model.Article;
import com.github.csccoder.csdn2md.util.html2markdown.HTML2Md;


import java.io.*;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Pattern;

public class FileUtil {
    private static String dir;
    private static String htmlDir;
    private static String mdDir;
    private static String imgDir;
    private static boolean img;
    private static Pattern FilePattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");

    public FileUtil(String path, boolean imgSwitch) {
        if (path == null) return;
        dir = path;
        htmlDir = path + "/html/";
        mdDir = path + "/md/";
        imgDir = path + "/img/";
        if (img) img = false;
        else img = imgSwitch;
        judeDirExists(dir, htmlDir, mdDir, imgDir);
    }

    private void save(String content, String filePath) {
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

    private void saveHtml(Article article) {
        String fileName = article.getTitle();
        fileName = (fileName == null ? null : FilePattern.matcher(fileName).replaceAll("")); //过滤文件名特殊字符
        String filePath = htmlDir + fileName;
        save(article.getContent(), filePath);

    }

    private void saveHexomd(Article article) {
        String mdhead = HexoMdUtil.getHeader(article);
        try {
            String fileName = article.getTitle();
            fileName = (fileName == null ? null : FilePattern.matcher(fileName).replaceAll("")); //过滤文件名特殊字符
            String mdContent = HTML2Md.convertFile(new File(htmlDir + fileName), "utf-8");
            String realContent = mdhead + mdContent;
            String filePath = mdDir + article.getTitle() + ".md";
            save(realContent, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void html2HexoMd(Article article) {
        saveHtml(article);
        saveHexomd(article);
    }

    public static void getPicture(String url) {
        if (img && !url.equals("")) {
            URL ur;
            BufferedInputStream in;
            ByteArrayOutputStream outStream;
            try {
                String fix = url.substring(url.lastIndexOf("/") + 1, url.length());
                String fileName = UUID.randomUUID().toString();
                ur = new URL(url);
                in = new BufferedInputStream(ur.openStream());
                outStream = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    outStream.write(buf, 0, length);
                }
                byte[] bytes = outStream.toByteArray();
                if (!"".equals(fix)) {
                    if (!fix.contains(".")) {
                        fix = fix + "." + GetFileSuffix(bytes);
                    }
                }
                File fileOut = new File(imgDir + fileName + fix);
                FileOutputStream op = new FileOutputStream(fileOut);

                op.write(bytes);
                op.close();
                in.close();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static String GetFileSuffix(byte[] fileData) {
        if (fileData == null || fileData.length < 10) {
            return null;
        }

        if (fileData[0] == 'G' && fileData[1] == 'I' && fileData[2] == 'F') {
            return "gif";
        } else if (fileData[1] == 'P' && fileData[2] == 'N' && fileData[3] == 'G') {
            return "png";
        } else if (fileData[6] == 'J' && fileData[7] == 'F' && fileData[8] == 'I' && fileData[9] == 'F') {
            return "jpg";
        } else if (fileData[0] == 'B' && fileData[1] == 'M') {
            return "bmp";
        } else {
            return null;
        }
    }

    // 判断文件夹是否存在
    public static void judeDirExists(String dirRoot, String htmlDirPath, String mdDirPath, String imgDirPath) {
        try {
            File file = new File(dirRoot);
            if (!file.exists()) {
                if (!file.isDirectory()) {
                    File dirR = new File(dirRoot);
                    if (dirR.mkdirs()) {
                        System.out.println("已创建根目录！");
                    }
                }
            }

            File htmlDirP = new File(htmlDirPath);
            File mdDirP = new File(mdDirPath);
            File imgDirP = new File(imgDirPath);
            if (htmlDirP.mkdirs() && mdDirP.mkdirs() && imgDirP.mkdirs()) {
                System.out.println("目录创建成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }


}
