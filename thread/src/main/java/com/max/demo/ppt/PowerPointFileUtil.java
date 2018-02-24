package com.max.demo.ppt;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * <p>PowerPoint文件工具类
 *
 * <p>通用的PowerPoint文件工具类，可用于从PowerPoint文档中抽取文本信息
 *
 * @author  窦海宁, chong0660@sina.com
 * @since   AiyuCommonCore-1.0
 * @version AiyuCommonCore-1.0
 */
public class PowerPointFileUtil extends BasePowerPointFileUtil {

    /**
     * <p>从PowerPoint文档中提取文本信息
     *
     * @param  powerPointFile PowerPoint文件
     * @param  shapeSeparator Shape分隔符
     * @param  slideSeparator Slide分隔符
     *
     * @return 提取后的文本信息
     *
     * @modify 窦海宁, 2017-02-06
     */
    public static String extractTextFromPowerPointFile(File powerPointFile , String shapeSeparator , String slideSeparator) {

        String resultText = null;

        if (powerPointFile != null && powerPointFile.exists()) {

            String extension = FilenameUtils.getExtension(powerPointFile.getName());
            if (StringUtils.equalsIgnoreCase("ppt" , extension)) {

                //Office2003版文件处理
                resultText = PowerPoint2003FileUtil.extractTextFromPowerPointFile(powerPointFile , shapeSeparator , slideSeparator);
            } else if (StringUtils.equalsIgnoreCase("pptx" , extension)) {

                //Office2007版文件处理
                resultText = PowerPoint2007FileUtil.extractTextFromPowerPointFile(powerPointFile , shapeSeparator , slideSeparator);
            } else {

                //文件类型有误
            }
        }

        return resultText;
    }

}
