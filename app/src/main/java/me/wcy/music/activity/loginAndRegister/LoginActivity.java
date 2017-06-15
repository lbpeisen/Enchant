package me.wcy.music.activity.loginAndRegister;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wcy.music.R;
import me.wcy.music.activity.face.FaceDetectGrayActivity;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.model.User;
import me.wcy.music.utils.Httputils;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.zhy.http.okhttp.OkHttpUtils.postString;
import static me.wcy.music.application.MusicApplication.key;
import static me.wcy.music.application.MusicApplication.secret;

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
    String confidence;
    Handler handler;
    Bitmap bitmaptoux;
    String faceToken1;
    byte[] bis;
    ImageView imgss;
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
        imgss = (ImageView) findViewById(R.id.imagesss);
        tv_losepasswd = (TextView) findViewById(R.id.tv_losepasswd);
        sp = getSharedPreferences("proFile", MODE_PRIVATE);//获得实例对象
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("result", "login_fail");
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
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


    private void TestLogin(final String stUserName, String stPassWord) {
//        Map map=new HashMap();
//        map.put("method","login.php");
//        map.put("login_mail", "" + stUserName);
//        map.put("login_passwd", "" + stPassWord);
//        new HttpThreadString(handler,getApplicationContext(),map,proDialog).start();
//        createProgressBar();

        //*************************************
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("result", "login_ok");
//        bundle.putString("username", stUserName);
//        intent.putExtras(bundle);
//        setResult(RESULT_OK, intent);
//        finish();
        //*****************************************
        postString()
                .url(MusicApplication.ip + "enchant/login.action")
                .content(new Gson().toJson(new User(stUserName, Httputils.md5(stPassWord))))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onError: register" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Intent intent = new Intent();
                        Log.d(TAG, "onResponse: " + response);
                        if (response.toString().contains("\"STATUS\":1000")) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(response, User.class);
                            sp.edit().putString("email", stUserName).commit();
                            sp.edit().putString("name", user.getName()).commit();
                            sp.edit().putInt("id", user.getId()).commit();
                            sp.edit().putInt("avatar", user.getAvator()).commit();
                            Log.d(TAG, "onResponse: initUiinitUi" + user.getAvator());
                            MusicApplication.login();
                            Log.d(TAG, "onResponse:1111 ");
                            Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", "login_ok");
                            bundle.putString("username", stUserName);
                            intent.putExtras(bundle);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else if (response.toString().contains("\"STATUS\":1001")) {

                            Log.d(TAG, "onResponse:222 ");
                            Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                        } else if (response.toString().contains("\"STATUS\":1002")) {
                            Log.d(TAG, "onResponse:222 ");
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "未知错误，请联系管理员", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

//        finish();
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

                if (stUserName == null) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (stPassWord == null) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


//            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
//                    .setTitleText("识别中");
//            pDialog.show();
//            pDialog.setCancelable(false);
//            new CountDownTimer(800 * 7, 800) {
//                public void onTick(long millisUntilFinished) {
//                    // you can change the progress bar color by ProgressHelper every 800 millis
//                    i++;
//                    switch (i) {
//                        case 0:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
//                            break;
//                        case 1:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
//                            break;
//                        case 2:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
//                            break;
//                        case 3:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
//                            break;
//                        case 4:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
//                            break;
//                        case 5:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
//                            break;
//                        case 6:
//                            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
//                            break;
//                    }
//                }
//
//                public void onFinish() {
//                    i = -1;
//                    pDialog.setTitleText("Success!")
//                            .setConfirmText("OK")
//                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                    finish();
//                }
//            }.start();
        if (requestCode == REQUEST_CODE) {
            if (data == null) {
                return;
            }
            if (data.getByteArrayExtra("Imagetx") != null) {
                bis = data.getByteArrayExtra("Imagetx");
                bitmaptoux = BitmapFactory.decodeByteArray(bis, 0, bis.length);
//                    imgview_touxiang.setImageBitmap(bitmaptoux);
//                Drawable drawable = new BitmapDrawable(bitmaptoux);
//                imgss.setImageDrawable(drawable);
//                Glide.with(context).load(R.drawable.face).into(imageview);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d(TAG, "faceToken1aaa1");
                        CommonOperate commonOperate = new CommonOperate(key, secret, false);
//                        FaceSetOperate FaceSet = new FaceSetOperate(key, secret, false);
//                        ArrayList<String> faces = new ArrayList<>();
                        Response response1 = null;
                        Response response2 = null;

                        try {
//                            response1 = commonOperate.searchByOuterId(null, bis, null, "test", 1);
//                            response1 = commonOperate.detectByte(bis, 0, null);
                            response1 = commonOperate.detectByte(bis, 0, null);
                            faceToken1 = getFaceToken(response1);

                            //4cbcab712dc56c24d6fd8b87b78f362d
//                            File file = new File("file://C://Users//oreo//Desktop");
//                            byte[] buff = null;
//                            getBytesFromFile(file);
//                            String url = "https://api-cn.faceplusplus.com/facepp/v3/search";
//                            HashMap<String, String> map = new HashMap<>();
//                            HashMap<String, byte[]> byteMap = new HashMap<>();
//                            map.put("api_key", "vfXSA6XTVDYpr2HL0xOUdVqXbcJoU99C");
//                            map.put("api_secret", "AHg02tIO3JY6os6niQT93LWFfWcfcVvt");
//                            map.put("outer_id", "test");
//        map.put("image_file","http://www.lovexn.top/img/1.png");
//        map.put("check_empty","0");
//                            byteMap.put("image_file", bis);

//                            byte[] bacd = post(url, map, byteMap);
//                            String str = new String(bacd);
//                            System.out.println(str);
//                            Log.d(TAG, "run: eeee" + str);


//                            faceToken1 = getFaceToken(response1);
//                            faces.add(faceToken1);
                            Log.d(TAG, "faceToken1aaa" + faceToken1);
                            response2 = commonOperate.searchByFaceSetToken(faceToken1, null, null, MusicApplication.faceset_token, 1);
                            String result = new String(response2.getContent());
                            Log.d("result1111", result);
//                            Gson gson = new Gson();
//                            Log.d("result1111", result);
//                            facepp person = gson.fromJson(response2.toString(), facepp.class);
//                            Log.d("result1111", result);
//                            Log.d("result1111", "" + facepp.ResultsBean.getConfidence());
//                            Log.d("result1111", "" + person);
                            JSONObject jsonObject2 = new JSONObject(result);
                            JSONArray jsonArray = jsonObject2.getJSONArray("results");
                            JSONObject jsonObjectSon = (JSONObject) jsonArray.opt(0);
//                            JSONObject jsonObjectSontoken = (JSONObject) jsonArray.opt(0);
                            confidence = jsonObjectSon.getString("confidence");
                            String facetokenForSearch = jsonObjectSon.getString("face_token");
                            Log.d(TAG, "run: 333" + confidence);
//                            Toast.makeText(LoginActivity.this, "aaa", Toast.LENGTH_LONG).show();

                            if (Float.parseFloat(confidence) < 75) {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, "刷脸失败", Toast.LENGTH_LONG).show();
                                Looper.loop();
                                Log.d(TAG, "run: 333 6666");
                                return;
                            }


                            OkHttpUtils
                                    .postString()
                                    .url(MusicApplication.ip + "enchant/login.action")
//                                    .content(new Gson().toJson(new User("c07d3f2f8d6dcaa9d5ba9b8beaa4291")))
                                    .content(new Gson().toJson(new User(facetokenForSearch)))
                                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.d(TAG, "onError: " + e);
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.d(TAG, "onResponse: " + response);
                                            Intent intent = new Intent();
                                            if (response.toString().contains("\"STATUS\":1000")) {
                                                Log.d(TAG, "run: 333 66669");
                                                Log.d(TAG, "onResponse:1111 ");
                                                Gson gson = new Gson();
                                                User user = gson.fromJson(response, User.class);
                                                sp.edit().putString("email", user.getEmail()).commit();
                                                sp.edit().putString("name", user.getName()).commit();
                                                sp.edit().putInt("id", user.getId()).commit();
                                                sp.edit().putInt("avatar", user.getAvator()).commit();
                                                MusicApplication.login();
                                                Log.d(TAG, "onResponse:1111 ");
                                                Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("result", "login_ok");
                                                bundle.putString("username", stUserName);
                                                intent.putExtras(bundle);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            } else if (response.toString().contains("\"STATUS\":1008")) {
                                                Log.d(TAG, "onResponse:222 ");
                                                Toast.makeText(LoginActivity.this, "刷脸失败！", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }
        } else {
            Log.w(TAG, "onActivityResult: eeee");
        }
    }

//
//    private final static int CONNECT_TIME_OUT = 30000;
//    private final static int READ_OUT_TIME = 50000;
//    private static String boundaryString = getBoundary();

//    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
//        HttpURLConnection conne;
//        URL url1 = new URL(url);
//        conne = (HttpURLConnection) url1.openConnection();
//        conne.setDoOutput(true);
//        conne.setUseCaches(false);
//        conne.setRequestMethod("POST");
//        conne.setConnectTimeout(CONNECT_TIME_OUT);
//        conne.setReadTimeout(READ_OUT_TIME);
//        conne.setRequestProperty("accept", "*/*");
//        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
//        conne.setRequestProperty("connection", "Keep-Alive");
//        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
//        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry<String, String> entry = (Map.Entry) iter.next();
//            String key = entry.getKey();
//            String value = entry.getValue();
//            obos.writeBytes("--" + boundaryString + "\r\n");
//            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
//                    + "\"\r\n");
//            obos.writeBytes("\r\n");
//            obos.writeBytes(value + "\r\n");
//        }
//        if (fileMap != null && fileMap.size() > 0) {
//            Iterator fileIter = fileMap.entrySet().iterator();
//            while (fileIter.hasNext()) {
//                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
//                obos.writeBytes("--" + boundaryString + "\r\n");
//                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
//                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
//                obos.writeBytes("\r\n");
//                //obos.write(fileEntry.getValue());
//                obos.writeBytes("\r\n");
//            }
//        }
//        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
//        obos.writeBytes("\r\n");
//        obos.flush();
//        obos.close();
//        InputStream ins = null;
//        int code = conne.getResponseCode();
//        try {
//            if (code == 200) {
//                ins = conne.getInputStream();
//            } else {
//                ins = conne.getErrorStream();
//            }
//        } catch (SSLException e) {
//            e.printStackTrace();
//            return new byte[0];
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buff = new byte[4096];
//        int len;
//        while ((len = ins.read(buff)) != -1) {
//            baos.write(buff, 0, len);
//        }
//        byte[] bytes = baos.toByteArray();
//        ins.close();
//        return bytes;
//    }

//    private static String getBoundary() {
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 32; ++i) {
//            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
//        }
//        return sb.toString();
//    }

//    private static String encode(String value) throws Exception {
//        return URLEncoder.encode(value, "UTF-8");
//    }

//    public static byte[] getBytesFromFile(File f) {
//        if (f == null) {
//            return null;
//        }
//        try {
//            FileInputStream stream = new FileInputStream(f);
//            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = stream.read(b)) != -1)
//                out.write(b, 0, n);
//            stream.close();
//            out.close();
//            return out.toByteArray();
//        } catch (IOException e) {
//        }
//        return null;
//    }

}