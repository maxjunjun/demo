package com.max.demo.ppt;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.sl.usermodel.*;

/**
 * <p>PowerPoint文件工具基类
 * <p>
 * <p>通用的PowerPoint文件工具基类，可用于从PowerPoint文档中抽取文本信息
 */
public class BasePowerPointFileUtil {
    /**
     * <p>读取PowerPoint文件中的幻灯片对象
     *
     * @param slideShow SlideShow对象
     * @return 读取出的工作薄列表
     */
    public static List readSlideShow(SlideShow slideShow) {

        List slideList = null;
        if (slideShow != null) {

            slideList = new ArrayList();
            List slides = slideShow.getSlides();
            for (int i = 0; i < slides.size(); i++) {

                slideList.add(BasePowerPointFileUtil.readSlide((Slide) slides.get(i)));
            }
        }
        return slideList;
    }

    /**
     * <p>读取指定的Slide中的数据
     *
     * @param slide Slide对象
     * @return 读取出的Slide数据列表
     */
    public static List readSlide(Slide slide) {

        List shapeList = null;
        if (slide != null) {

            shapeList = new ArrayList();
            List shapes = slide.getShapes();
            for (int i = 0; i < shapes.size(); i++) {

                shapeList.add(BasePowerPointFileUtil.readShape((Shape) shapes.get(i)));
            }
        }
        return shapeList;
    }

    /**
     * <p>读取指定的图形的数据
     *
     * @param shape Slide中的图形对象
     * @return 读取出的图形数据
     */
    public static Object readShape(Shape shape) {

        String returnValue = null;
        if (shape != null) {

            if (shape instanceof TextShape) {
                try {

                    returnValue = ((TextShape) shape).getText();
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
        return returnValue;
    }

    public static void renderShape(Shape shape, Map<String, Object> data) {
        if (shape instanceof TextShape) {
            BasePowerPointFileUtil.replace(shape, data);
        } else if (shape instanceof GroupShape) {
            Iterator groupShapes = ((GroupShape) shape).iterator();
            while (groupShapes.hasNext()) {
                Shape groupShape = (Shape) groupShapes.next();
                BasePowerPointFileUtil.renderShape(groupShape, data);
            }
        } else if (shape instanceof TableShape) {
            TableShape tableShape = ((TableShape) shape);
            int column = tableShape.getNumberOfColumns();
            int row = tableShape.getNumberOfRows();
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    BasePowerPointFileUtil.replace(tableShape.getCell(r, c), data);
                }
            }
        }
    }

    public static void replace(Shape shape, Map<String, Object> data) {
        if(data == null) {
            data = new HashMap<>();
        }
        if (shape instanceof TextShape) {
            TextShape textShape = (TextShape) shape;
            String text = textShape.getText();
            List<String> keys = DealStrSubUtil.getSubUtil(text, "\\$\\{(.+?)\\}");
            Map<String, Object> map = (Map<String, Object>) data;
            for (String key : keys) {
                Object value = map.get(key);
                if (value != null)
                    text = text.replaceAll("\\$\\{" + key + "\\}", value.toString());
            }
            textShape.setText(text);
        }
    }
}
