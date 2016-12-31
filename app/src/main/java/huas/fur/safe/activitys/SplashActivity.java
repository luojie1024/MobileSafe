package huas.fur.safe.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import huas.fur.mobilesafe.R;
import huas.fur.safe.biz.LoginHelper;
import huas.fur.safe.biz.ViewHelp;

/**
 * 作者：Jacky
 * 邮箱：550997728@qq.com
 * 时间：2016/12/30 15:10
 */
public class SplashActivity extends Activity{

          private TextView tv_splash_version;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_splash);
                    tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
                    tv_splash_version.setText("版本号："+ ViewHelp.getVersion(this));
                    //登陆动作直接交给单例做
                    LoginHelper.getInstance(this).loginConnect();
          }
}
