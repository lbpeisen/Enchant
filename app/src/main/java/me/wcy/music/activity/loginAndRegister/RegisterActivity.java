package me.wcy.music.activity.loginAndRegister;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.wcy.music.R;
import me.wcy.music.activity.face.FaceDetectGrayActivity;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.model.User;
import me.wcy.music.utils.Httputils;
import okhttp3.Call;
import okhttp3.MediaType;

import static me.wcy.music.application.MusicApplication.key;
import static me.wcy.music.application.MusicApplication.secret;

public class RegisterActivity extends AppCompatActivity {

    public final int REQUEST_CODE = 1;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.cv_add)
    CardView cvAdd;
    @Bind(R.id.bt_go)
    Button next;
    EditText et_user;
    EditText et_username;
    EditText et_passwd;
    String st_user;
    String st_username;
    String st_passwd;
    String pin;
    String faceToken1;
    EditText et_repeatpassword;
    EditText et_emailpin;
    String st_repeatpassword;
    String returnString;
    Boolean HasImg = false;
    Map map = new HashMap();
    String st_return = "服务器错误";
    ProgressDialog proDialog;
    private Button bt_getpin;
    Bitmap bitmaptoux;
    ImageView imgview_touxiang;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //this.getWindow().setBackgroundDrawable(R.drawable.bg);  
        imgview_touxiang = (ImageView) findViewById(R.id.imgview_touxiang);
        et_user = (EditText) findViewById(R.id.et_user);
        et_username = (EditText) findViewById(R.id.et_username);
        et_passwd = (EditText) findViewById(R.id.et_password);
        et_emailpin = (EditText) findViewById(R.id.et_emailpin);
        et_repeatpassword = (EditText) findViewById(R.id.et_repeatpassword);
        bt_getpin = (Button) findViewById(R.id.bt_getpin);
        returnString = "成功了吗？";
        ButterKnife.bind(this);
        ShowEnterAnimation();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                String test = b.getString("state");
                if (test.contains("23333")) {
                    proDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    animateRevealClose();
                } else if (test.contains("100003")) {

                    Toast.makeText(RegisterActivity.this, "用户名已被使用", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if (test.contains("100013")) {
                    Toast.makeText(RegisterActivity.this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if (test.contains("100014")) {
                    Toast.makeText(RegisterActivity.this, "请获取验证码", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else {
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    Log.i("ipceshi", "" + test);
                }


            }
        };
        final Handler emailhandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                String test = b.getString("state");
                Log.i("regesttest", "" + test);

                if (test.contains("100010")) {
                    Toast.makeText(RegisterActivity.this, "请输入正确邮箱", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if (test.contains("100011")) {
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if (test.contains("100012")) {
                    Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if (test.contains("23333")) {
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else {
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }

            }
        };
        bt_getpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_username.getText().toString();
                if (isEmail(email)) {
                    HashMap map = new HashMap();
                    map.put("email", email);
//                    map.put("type", "0");
//                    map.put("method", "sendEmail.php");
                    OkHttpUtils
                            .postString()
                            .url(MusicApplication.ip + "enchant/getCode.action")
//                            .content(new Gson().toJson(new User("zhy", "123")))
                            .content(Httputils.createJSON(map).toString())
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(RegisterActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError");
                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError" + id);
                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError" + MusicApplication.ip + "enchant/getCode.action");
                                }

                                @Override
                                public void onResponse(String response, int id) {
//                                    if(response.contains())

                                    if (response.toString().contains("\"STATUS\":1003")) {
                                        Log.d(TAG, "onResponse:3333 ");
                                        Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                    } else if (response.toString().contains("\"STATUS\":1000")) {
                                        Log.d(TAG, "onResponse:3333 ");
                                        Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                    }
//                                    Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
//                                    Log.d(TAG, "onResponse: 2333333");
                                }
                            });


//                    OkHttpUtils
//                            .post()
//                            .url(MusicApplication.ip + "enchant/getCode.action")
//                            .addParams("email", email)
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//                                    Toast.makeText(RegisterActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError");
//                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError" + id);
//                                    Log.d(TAG, "onResponse: onResponse: : 2333333 onError" + MusicApplication.ip + "enchant/getCode.action");
//                                }
//
//                                @Override
//                                public void onResponse(String response, int id) {
//                                    Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
//                                    Log.d(TAG, "onResponse: 2333333");
//                                }
//                            });
//                    new HttpThreadString(emailhandle, getApplicationContext(), map, proDialog).start();
//                    createProgressBar();
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入正确邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_username.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_user.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_emailpin.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_passwd.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_repeatpassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "重复密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_repeatpassword.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "密码不小于6位", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_repeatpassword.length() > 18) {
                    Toast.makeText(RegisterActivity.this, "密码不大于18位", Toast.LENGTH_SHORT).show();
                    return;
                }

                st_username = et_username.getText().toString();
                st_user = et_user.getText().toString();
                st_passwd = et_passwd.getText().toString();
                pin = et_emailpin.getText().toString();
                st_repeatpassword = et_repeatpassword.getText().toString();
                if (!st_passwd.equals(st_repeatpassword)) {
                    Toast.makeText(RegisterActivity.this, "2次输入密码不同", Toast.LENGTH_SHORT).show();
                }


//                map.put("reg_mail",st_username);
//                map.put("reg_passwd", st_passwd);
//                map.put("reg_name", st_user);
//                map.put("code",pin);
//                map.put("method", "register.php");
//                new HttpThreadString(handler,getApplicationContext(),map,proDialog).start();
//
//                createProgressBar();

                new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("是否使用人脸识别注册？")
                        .setCustomImage(R.drawable.face)
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // TODO: 2017/5/27
                                OkHttpUtils
                                        .postString()
                                        .url(MusicApplication.ip + "enchant/register.action")
                                        .content(new Gson().toJson(new User(st_username, st_passwd, st_user, pin)))
                                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.d(TAG, "onError: register" + call);
                                                Log.d(TAG, "onError: register" + e);
                                                Log.d(TAG, "onError: register" + id);
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.d(TAG, "onResponse: " + response);
                                                Log.d(TAG, "onResponse: " + response.toString());
                                                if (response.toString().contains("\"STATUS\":1005")) {

                                                    Log.d(TAG, "onResponse:1111 ");
                                                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                                } else if (response.toString().contains("\"STATUS\":1004")) {

                                                    Log.d(TAG, "onResponse:222 ");
                                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                    animateRevealClose();
                                                } else if (response.toString().contains("\"STATUS\":1006")) {

                                                    Log.d(TAG, "onResponse:3333 ");
                                                    Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                                                } else if (response.toString().contains("\"STATUS\":1003")) {

                                                    Log.d(TAG, "onResponse:3333 ");
                                                    Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                                }


                                            }
                                        });


//                                OkHttpUtils
//                                        .post()
//                                        .url(MusicApplication.ip + "enchant/register.action")
//                                        .addParams("user_email", "403918995@qq.com")
//                                        .addParams("user_password", "123456")
//                                        .addParams("user_name", "123")
//                                        .addParams("code", "860580")
//                                        .build()
//                                        .execute(new StringCallback() {
//                                            @Override
//                                            public void onError(Call call, Exception e, int id) {
//                                                Log.d(TAG, "onError: register" + call);
//                                                Log.d(TAG, "onError: register" + e);
//                                                Log.d(TAG, "onError: register" + id);
//                                            }
//
//                                            @Override
//                                            public void onResponse(String response, int id) {
//                                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                                                animateRevealClose();
//                                            }
//                                        });
                                sweetAlertDialog.dismiss();

                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                HasImg = false;
                                Intent intent = new Intent(RegisterActivity.this, FaceDetectGrayActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .show();


            }
        });
    }

    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(RegisterActivity.this, "请等待", "数据传送中！");
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

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    byte[] bis;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (data == null) {
                return;
            }
            if (data.getByteArrayExtra("Imagetx") != null) {
                bis = data.getByteArrayExtra("Imagetx");
                bitmaptoux = BitmapFactory.decodeByteArray(bis, 0, bis.length);
//                    imgview_touxiang.setImageBitmap(bitmaptoux);
                Drawable drawable = new BitmapDrawable(bitmaptoux);
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("这是你吗？")
                        .setCustomImage(drawable)
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                HasImg = false;
                                Intent intent = new Intent(RegisterActivity.this, FaceDetectGrayActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                Log.d(TAG, "faceToken1aaa");

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Log.d(TAG, "faceToken1aaa1");
                                        CommonOperate commonOperate = new CommonOperate(key, secret, false);
                                        FaceSetOperate FaceSet = new FaceSetOperate(key, secret, false);
                                        ArrayList<String> faces = new ArrayList<>();

                                        Response response1 = null;
                                        try {
                                            //cb1975bb94db8b29f89f82669e9d3d04
                                            response1 = commonOperate.detectByte(bis, 0, null);
                                            faceToken1 = getFaceToken(response1);
                                            faces.add(faceToken1);
                                            String faceTokens = creatFaceTokens(faces);
                                            Response faceset = FaceSet.createFaceSet(null,"test",null,faceTokens,null, 1);
                                            Log.d(TAG, "faceToken1aaa" + faceToken1);
                                            animateRevealClose();
                                            OkHttpUtils
                                                    .postString()
                                                    .url(MusicApplication.ip + "enchant/register.action")
                                                    .content(new Gson().toJson(new User(st_username, st_passwd, st_user, pin, faceToken1)))
                                                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e, int id) {
                                                            Log.d(TAG, "onError: register" + call);
                                                            Log.d(TAG, "onError: register" + e);
                                                            Log.d(TAG, "onError: register" + id);
                                                        }

                                                        @Override
                                                        public void onResponse(String response, int id) {
                                                            Log.d(TAG, "onResponse: " + response);
                                                            Log.d(TAG, "onResponse: " + response.toString());
                                                            if (response.toString().contains("\"STATUS\":1005")) {

                                                                Log.d(TAG, "onResponse:1111 ");
                                                                Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                                            } else if (response.toString().contains("\"STATUS\":1004")) {

                                                                Log.d(TAG, "onResponse:222 ");
                                                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                                animateRevealClose();
                                                            } else if (response.toString().contains("\"STATUS\":1006")) {

                                                                Log.d(TAG, "onResponse:3333 ");
                                                                Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                                                            } else if (response.toString().contains("\"STATUS\":1003")) {

                                                                Log.d(TAG, "onResponse:3333 ");
                                                                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                                            }


                                                        }
                                                    });


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

//                                        StringBuffer sb = null;
//                                        sb.append("faceToken1: ");
//                                        sb.append(faceToken1);

                                    }
                                }).start();
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            mTextView.setText(sb.toString());
//                                        }
//                                    });
//                                ;


//                                OkHttpUtils
//                                        .postString()
//                                        .url(MusicApplication.ip + "enchant/register.action")
//                                        .content(new Gson().toJson(new User(st_username, st_passwd, st_user, pin, "c07d3f2f8d6dcaa9d5ba9b8beaa4291")))
//                                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                                        .build()
//                                        .execute(new StringCallback() {
//                                            @Override
//                                            public void onError(Call call, Exception e, int id) {
//                                                Log.d(TAG, "onError: register" + call);
//                                                Log.d(TAG, "onError: register" + e);
//                                                Log.d(TAG, "onError: register" + id);
//                                            }
//
//                                            @Override
//                                            public void onResponse(String response, int id) {
//                                                Log.d(TAG, "onResponse: " + response);
//                                                Log.d(TAG, "onResponse: " + response.toString());
//                                                if (response.toString().contains("\"STATUS\":1005")) {
//
//                                                    Log.d(TAG, "onResponse:1111 ");
//                                                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                                                } else if (response.toString().contains("\"STATUS\":1004")) {
//
//                                                    Log.d(TAG, "onResponse:222 ");
//                                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                                                    animateRevealClose();
//                                                } else if (response.toString().contains("\"STATUS\":1006")) {
//
//                                                    Log.d(TAG, "onResponse:3333 ");
//                                                    Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
//                                                } else if (response.toString().contains("\"STATUS\":1003")) {
//
//                                                    Log.d(TAG, "onResponse:3333 ");
//                                                    Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
//                                                }
//
//
//                                            }
//                                        });


                            }
                        })
                        .show();
                HasImg = true;
                Log.e("hh", "3");
            }
            Log.e("hh", "2");

        } else {
            Log.e("hh1", requestCode + "  " + resultCode);
        }
    }


    private byte[] getBitmap(int res) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private String getFaceToken(Response response) throws JSONException {
        if (response.getStatus() != 200) {
            return new String(response.getContent());
        }
        String res = new String(response.getContent());
        Log.e("response", res);
        JSONObject json = new JSONObject(res);
        String faceToken = json.optJSONArray("faces").optJSONObject(0).optString("face_token");
        return faceToken;
    }

    private String creatFaceTokens(ArrayList<String> faceTokens){
        if(faceTokens == null || faceTokens.size() == 0){
            return "";
        }
        StringBuffer face = new StringBuffer();
        for (int i = 0; i < faceTokens.size(); i++){
            if(i == 0){
                face.append(faceTokens.get(i));
            }else{
                face.append(",");
                face.append(faceTokens.get(i));
            }
        }
        return face.toString();
    }

}