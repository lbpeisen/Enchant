package me.wcy.music.activity.loginAndRegister;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.wcy.music.R;
import me.wcy.music.activity.face.FaceDetectGrayActivity;

import static com.tencent.bugly.crashreport.crash.c.i;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText username;
    @Bind(R.id.et_password)
    EditText userpassword;
    @Bind(R.id.bt_go)
    Button btGo;
    @Bind(R.id.cv)
    CardView cv;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.remember)
    CheckBox remember;
    @Bind(R.id.auto)
    CheckBox auto;

    ProgressDialog proDialog;
    String stUserName;
    String stPassWord;
    Handler handler;
    private int i = -1;
    private TextView tv_losepasswd;
    private SharedPreferences sp;
    public final int REQUEST_CODE = 2;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tv_losepasswd = (TextView) findViewById(R.id.tv_losepasswd);
        sp = getSharedPreferences("ziliao", MODE_PRIVATE);//获得实例对象
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                Bundle b=msg.getData();
//                String test=b.getString("state");
//                Log.i("logintest",""+test);
//                if(test.contains("user_id"))
//                {
//                    proDialog.dismiss();
//                    Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i2);
//
//                    Gson gson = new Gson();
//                    Userinfo userinfo = gson.fromJson(test, Userinfo.class);
//
//                        sp.edit().putString("user_mail", stUserName).commit();
//                        sp.edit().putString("user_password", stPassWord).commit();
//                        sp.edit().putString("user_name", userinfo.getUser_name()).commit();
//                        sp.edit().putString("user_phone", userinfo.getUser_phone()).commit();
//                        sp.edit().putInt("user_academy_number", userinfo.getUser_academy_number()).commit();
//                        sp.edit().putInt("user_id", userinfo.getUser_id()).commit();
//                        sp.edit().putInt("user_address_province", userinfo.getUser_address_province()).commit();
//                        sp.edit().putString("user_address_city", userinfo.getUser_address_city()).commit();
//                        sp.edit().putString("user_sex", userinfo.getUser_sex()).commit();
//                        sp.edit().putInt("user_avatar", userinfo.getUser_avatar()).commit();
//                        sp.edit().putInt("user_type", userinfo.getUser_type()).commit();
//                        sp.edit().putInt("user_id", userinfo.getUser_id()).commit();
//                    finish();
//                }
//                else{
//                    if(test.contains("100005")){
//                        Toast.makeText(LoginActivity.this, "该用户未注册", Toast.LENGTH_SHORT).show();
//                        proDialog.dismiss();
//                    }else if(test.contains("100006")){
//                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
//                        proDialog.dismiss();
//                    }else {
//                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
            }

        };
    }
//        if (sp.getBoolean("auto",false)){
//            stUserName = sp.getString("user_mail","");
//            stPassWord = sp.getString("user_password","");
//            TestLogin(stUserName,stPassWord);
//            remember.setChecked(true);
//            auto.setChecked(true);
//        }
//        else if (sp.getBoolean("ischeck", false)) {
//            username.setText(sp.getString("user_mail", ""));
//            userpassword.setText(sp.getString("user_password", ""));
//            remember.setChecked(true);
//            auto.setChecked(false);
//        } else {
//            username.setText(sp.getString("user_mail", ""));
//            userpassword.setText("");
//            auto.setChecked(false);
//            remember.setChecked(false);
//        }

//        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    sp.edit().putBoolean("ischeck", true).commit();
//                } else {
//                    sp.edit().putBoolean("ischeck", false).commit();
//                    auto.setChecked(false);
//                }
//            }
//        });

//        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    sp.edit().putBoolean("auto", true).commit();
//                    sp.edit().putBoolean("ischeck", true).commit();
//                    remember.setChecked(true);
//                }else{
//                    sp.edit().putBoolean("auto", false).commit();
//                    sp.edit().putBoolean("ischeck", false).commit();
//                    remember.setChecked(false);
//                }
//            }
//        });
//    }


    private void TestLogin(String stUserName, String stPassWord) {
//        Map map=new HashMap();
//        map.put("method","login.php");
//        map.put("login_mail", "" + stUserName);
//        map.put("login_passwd", "" + stPassWord);
//        new HttpThreadString(handler,getApplicationContext(),map,proDialog).start();
//        createProgressBar();
        finish();
    }

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(LoginActivity.this, "请等待", "数据传送中！");
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
            }
        };
        thread.start();

    }

    @OnClick({R.id.bt_go, R.id.fab, R.id.tv_losepasswd, R.id.bt_face_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                break;
            case R.id.bt_go:
                stUserName = username.getText().toString();
                stPassWord = userpassword.getText().toString();
                TestLogin(stUserName, stPassWord);
                break;
            case R.id.tv_losepasswd:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this, LostPasswdActivity.class), options1.toBundle());
                break;
            case R.id.bt_face_go:
                Intent intent = new Intent(LoginActivity.this, FaceDetectGrayActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("识别中");
            pDialog.show();
            pDialog.setCancelable(false);
            new CountDownTimer(800 * 7, 800) {
                public void onTick(long millisUntilFinished) {
                    // you can change the progress bar color by ProgressHelper every 800 millis
                    i++;
                    switch (i){
                        case 0:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                            break;
                        case 1:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                            break;
                        case 2:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                            break;
                        case 3:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                            break;
                        case 4:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                            break;
                        case 5:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                            break;
                        case 6:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                            break;
                    }
                }

                public void onFinish() {
                    i = -1;
                    pDialog.setTitleText("Success!")
                            .setConfirmText("OK")
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    finish();
                }
            }.start();

        }else {
            Log.e(TAG, "onActivityResult: ");
        }
    }
}