package com.max.demo.ppt;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.sl.usermodel.SlideShow;


/**
 * <p>PowerPoint2003版文件工具类
 *
 * <p>通用的PowerPoint2003版文件工具类，可用于从PowerPoint文档中抽取文本信息
 *
 */
public class PowerPoint2003FileUtil extends BasePowerPointFileUtil {
    /**
     * <p>从PowerPoint文档中提取文本信息
     *
     * @param  powerPointFile PowerPoint文件
     * @param  shapeSeparator Shape分隔符
     * @param  slideSeparator Slide分隔符
     *
     */
    public static String extractTextFromPowerPointFile(File powerPointFile , String shapeSeparator , String slideSeparator) {

        StringBuffer returnValue = new StringBuffer();
        if (powerPointFile != null && slideSeparator != null && shapeSeparator != null) {

            if (powerPointFile.isFile()) {

                try {

                    SlideShow slideShow     = new HSLFSlideShow(new HSLFSlideShowImpl(powerPointFile.getCanonicalPath()));
                    Iterator  slideIterator = PowerPoint2003FileUtil.readSlideShow(slideShow).iterator();
                    //遍历Slide
                    while (slideIterator.hasNext()) {

                        Iterator shapeIterator = ((List) slideIterator.next()).iterator();
                        //遍历Shape
                        while (shapeIterator.hasNext()) {

                            Object shapeValue = shapeIterator.next();
                            if (shapeValue != null) {

                                returnValue.append((String) shapeValue);
                                if (shapeIterator.hasNext()) {

                                    returnValue.append(shapeSeparator);
                                }
                            }
                        }
                        if (slideIterator.hasNext()) {

                            returnValue.append(slideSeparator);
                        }
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
        return StringUtils.trimToNull(returnValue.toString());
    }
}
