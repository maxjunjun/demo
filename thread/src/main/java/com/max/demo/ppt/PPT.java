package com.max.demo.ppt;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hanzhongao on 2017/10/11.
 */
public class PPT {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new File("f:\\Users\\hanzhongao\\Desktop\\中国石油天然气勘探开发公司课题研究服务建议书-0622.pptx");

        XSLFPowerPointExtractor xslfPowerPointExtractor = new XSLFPowerPointExtractor(new XMLSlideShow(new FileInputStream(file)));
        System.out.print(xslfPowerPointExtractor.getText(true,false,false));
        Iterator<PackagePart> parts = xslfPowerPointExtractor.getPackage().getParts().iterator();
        xslfPowerPointExtractor.getCoreProperties();
        xslfPowerPointExtractor.getDocument();


        //读取PowerPoint文档中所有文本内容，以字符串形式返回
        // System.out.println(PowerPointFileUtil.extractTextFromPowerPointFile(file , "," , ";"));
        Map<String, Object> map = new HashMap<>();
        map.put("keti1","value1");
        PowerPoint2007FileUtil.renderPowerPointTemplate(new FileInputStream(file), map);
    }
}
