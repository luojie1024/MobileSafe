package huas.fur.safe.biz;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/12/31 11:40
 */
public class ViewHelp {

          /**
           * 获取版本号
           * @param context
           * @return versionName
           */
          public static String getVersion(Context context){
                    PackageManager pm=context.getPackageManager();
                    try {
                              //封装了说有manifests数据
                              PackageInfo info = pm.getPackageInfo("huas.fur.mobilesafe", 0);
                              return  info.versionName;
                    } catch (PackageManager.NameNotFoundException e) {
                              e.printStackTrace();
                    }
                    return null;
          }
}
