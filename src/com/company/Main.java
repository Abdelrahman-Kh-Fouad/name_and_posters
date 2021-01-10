package com.company;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup. *;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.boxofficemojo.com/showdown/?ref_=bo_nb_cso_tab").get();
        String s = doc.toString();
        File f =new File("out.txt");
        if(!f.exists())
            f.createNewFile();
        FileWriter writer = new FileWriter(f);
        writer.append(s);
        Elements all =  doc.select("a");
        Pattern p = Pattern.compile(">.+</a>");
        ArrayList<String> names = new ArrayList<String>();
        for(Element i  : all)
        {
            String cur = i.toString();

            if (cur.indexOf("/release/")!=-1)
            {
                //System.out.println(cur+"->>>");
                Matcher m = p.matcher(cur);
                if(m.find())
                {
                    String tmp = m.group();
                    tmp = tmp.substring( 1 , tmp.length()-4);
                    names.add(tmp);
                }
            }
        }
        File file = new File("out.txt");
        FileWriter fileStream = new FileWriter(file);
        for (int i =0 ;i < 20 ; i++  )
        {
            String name = names.get(i);
            getimg img = new getimg(name);
            fileStream.append(name+"->"+img.get()+" =======");
            fileStream.append(System.currentTimeMillis()+"\n");
            System.out.println(i);
        }
        fileStream.close();

    }
}
