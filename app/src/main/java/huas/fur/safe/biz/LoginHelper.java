package huas.fur.safe.biz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.net.HttpURLConnection;
import java.net.URL;

import huas.fur.mobilesafe.R;
import huas.fur.safe.bean.Updatabean;
import huas.fur.safe.utils.XmlPareseUtil;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/12/31 12:24
 *
 *专门处理登陆逻辑
 */
public class LoginHelper {

          private static LoginHelper login;
          private Activity context;

          private final int UPDATA=1;
          private Updatabean updatabean;

          /**
           * 单例
           * @param context
           */
          private LoginHelper(Activity context)
          {
                    this.context = context;
          }
          public static LoginHelper getInstance(Activity context)
          {
                    if (login==null) {
                              login = new LoginHelper(context);
                    }
                    return login;
          }

          /**
           * 连接服务器
           */
          public void loginConnect() {
                    new Thread(new Runnable() {
                              @Override
                              public void run() {
                                        connext();
                              }
                    }).start();
          }

          protected void connext() {
                    //获取配置上的URI地址
                    String apkurl=context.getResources().getString(R.string.apkurl);
                    URL url= null;
                    try {
                              url = new URL(apkurl);
                              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                              conn.setRequestMethod("GET");
                              conn.setConnectTimeout(5000);
                              if (conn.getResponseCode() == 200) {
                                        //连接成功
                                        updatabean = XmlPareseUtil.getUpdataInfo(conn.getInputStream());
                                        if (updatabean !=null) {
                                                  if (updatabean.getVersion() == ViewHelp.getVersion(context)) {
                                                            //已经是最新版本，进入主界面
                                                            enterMain();
                                                  } else {
                                                            //有新版本，需要更新，弹窗
                                                            Message msg = handler.obtainMessage();
                                                            msg.what=UPDATA;
                                                            handler.sendMessage(msg);

                                                  }

                                        }
                              } else {
                                        //连接失败
                              }

                    } catch (Exception e) {
                              //服务器连接不上
                              e.printStackTrace();
                    }
          }

          /**
           * 进入主界面
           */
          private void enterMain() {

          }

          private Handler handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                              super.handleMessage(msg);
                              switch (msg.what) {
                                        case UPDATA://进行更新提示
                                                  updataTipDialog();
                                                  break;
                                        default:break;
                              }

                    }
          };

          /**
           * 提示用户更新升级
           */
          protected void updataTipDialog() {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("升级提示");
                    builder.setMessage(updatabean.getDes());
                    builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {

                              }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {

                              }
                    });
                    builder.show();
          }


}
