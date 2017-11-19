# csdn2Hexomd
CSDN博客迁移至Hexo,同步CSDN博文到本地MD文件。

功能：
- 利用爬虫爬取指定用户的CSDN博客文章
- 把爬取到的html文章内容转化为符合Hexo风格的markdown文件

通过这个项目得到了所有markdown文件，可以让我们很轻松的把文章同步到Hexo博客中，完成博客的迁移！

技术关键词：Java,网络爬虫

| 主要技术        | 工具           |
| ------------- |:-------------:| 
| 爬虫     | Httpclient,Jsoup | 
| Html转markdown      | [html2markdown](https://github.com/pnikosis/jHTML2Md)     |  


***

>效果

`运行效果`
![运行效果](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/run_out.png)

`生成文件夹结构`
![生成文件夹结构](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/dir_0.png)

`文件夹结构之html目录 存放从CSDN博客爬下来的HTML格式文章`
![文件夹结构之html目录](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/dir_html.png)

`文件夹结构之md目录   存放markdown格式文章，是由html目录下的html文章转化生成的`
![文件夹结构之md目录](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/dir_html.png)

`html格式文章详图`
![html格式文章详图](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/html_detail.png)

`markdown格式文章详图`
![markdown格式文章详图](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/markdown_detail.png)

`CSDN博客截图`
![CSDN博客截图](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/blog_csdn.png)

`Hexo博客截图`
![Hexo博客截图](https://github.com/csccoder/csdn2Hexomd/raw/master/src/main/resources/img/blog_hexo.png)