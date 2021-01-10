package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class getimg {
    private String name ;
    public  Document doc ;
    public getimg(String name )
    {
        this.name = name+" poster";
    }
    String get() throws IOException {
        String s ="https://www.bing.com/images/search?q="+name+"&form=HDRSC3&first=1&tsc=ImageBasicHover";
        Document doc = Jsoup.connect(s).get();
        this.doc =doc;
        Elements imgpage = doc.getElementsByClass("mimg vimgld");
        String secondurl =  imgpage.attr("data-src");
        return secondurl;
    }

}
