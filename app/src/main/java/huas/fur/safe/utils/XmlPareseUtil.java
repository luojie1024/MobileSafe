package huas.fur.safe.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

import huas.fur.safe.bean.Updatabean;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/12/31 12:48
 */
public class XmlPareseUtil {
          /**
           * 解析XML
           * @param inutStream
           * @return updatabean
           */
          public static Updatabean getUpdataInfo(InputStream inutStream) {
                    //获得一个解析器,并解析数据，封装到bean中
                    XmlPullParser parser = Xml.newPullParser();
                    Updatabean bean = new Updatabean();
                    try {
                              parser.setInput(inutStream,"UTF-8");
                              int type = parser.getEventType();
                              while (type != XmlPullParser.END_TAG) {
                                        switch (type) {
                                                  case XmlPullParser.START_TAG:
                                                            if ("version".equals(parser.getName())) {
                                                                      bean.setVersion(parser.nextText());
                                                            } else if ("description".equals(parser.getName())) {
                                                                      bean.setDes(parser.nextText());
                                                            } else if ("apkurl".equals(parser.getName())) {
                                                                      bean.setApkUrl(parser.nextText());
                                                            }
                                                            break;
                                        }
                                        type=parser.next();
                              }
                    } catch (Exception e) {
                              e.printStackTrace();
                    }
                    return bean;
          }
}
