package com.ray.baseandroid.inputtest;

import android.support.v4.app.FragmentActivity;
import android.view.View;


public abstract class UserTrackActivity extends FragmentActivity {
/*

    private static final String TAG = "UserTrackActivity";
    protected final LifecycleHandler uiHandler = new LifecycleHandler(this);
    private boolean mDestroyed = false;
    private boolean isCreated = false;
    public boolean isResumed;
    private final AppStatusChangeManager mAppStatusChangeManager = AppStatusChangeManager.getInstance();
    private NetReceiver.NetStatus netStatus;

    //-------------- 以下是语音相关成员变量 --------------
    private SpeechRecognizer speechRecognizer;
    // 输入法上面的语音输入的布局
    protected View mVoiceInputView;
    // 说话时音量大小的iv
    private ImageView mVolumeImg;
    //按住说话View
    View mSpeakView;
    //正在语音输入时的View
    View mVolumeView;
    // 输入法上面的语音输入的布局是否被加到contentFrameLayout中，加这个标记位时因为增加后会多次调用输入法弹起和隐藏的方法
    private Timer mTimer;
    //输入法语音输入是否显示
    protected boolean mIsDisplayInputVoice = true;
    private Context mContext;
    private int mScrolledHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
        mContext = this.getApplicationContext();
        determinLocale();
        if (!hasPageAuthority()) {
            onPageAuthorizeFailed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAppStatusChangeManager != null) {
            if (!mAppStatusChangeManager.isActivated()) {
                //isActivated为false说明之前进入了后台
                //调用后台进入前台的回调
                mAppStatusChangeManager.onBackgroundToForeground(this);
            }

            //记录应用切换到了前台
            mAppStatusChangeManager.setActivated(true);
        }
        NetReceiver.getInstance().registerListener(this);
        NetReceiver.NetStatus status = NetReceiver.getInstance().getStatus(this);
        if (netStatus != null && status != netStatus) {
            netStatus = status;
            onNetStatusChanged(netStatus);
        }

        initSpeechVariable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAppStatusChangeManager != null) {
            if (!AppUtil.isAppOnForeground(SOSApplication.getAppContext())) {
                //现在应用在后台
                if (mAppStatusChangeManager.isActivated()) {
                    //之前应用是在前台
                    //调用前台到后台的回调
                    mAppStatusChangeManager.onForegroundToBackground(this);
                }

                //记录应用切换到了后台
                mAppStatusChangeManager.setActivated(false);
            }
        }
        NetReceiver.getInstance().removeListener(this);
        netStatus = NetReceiver.getInstance().getStatus(this);

        if (mIsDisplayInputVoice) {
            removeOnGlobalLayoutListener();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
        mDestroyed = true;
    }

    private void initSpeechVariable() {
        if (!mIsDisplayInputVoice) {
            return;
        }

        SpeechUtility.createUtility(this, "appid=" + this.getString(R.string.app_id));
        mVoiceInputView = LayoutInflater.from(this).inflate(R.layout.custom_voice, null);
        mVoiceInputView.setVisibility(View.GONE);
        mSpeakView = mVoiceInputView.findViewById(R.id.layout_speak);
        mVolumeView = mVoiceInputView.findViewById(R.id.layout_volume);
        mVolumeImg = (ImageView) mVolumeView.findViewById(R.id.img_volume);
        mSpeakView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startListening();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (speechRecognizer != null && speechRecognizer.isListening()) {
                        createDialog();
                    }
                    stopListening();
                }
                return true;
            }
        });

        //通过decorView得到ContentFrameLayout
        FrameLayout contentFrameLayout = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewUtil.dp2px(mContext, 50));
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
//        LinearLayout rootView = (LinearLayout) getWindow().getDecorView().findViewById(android.R.id.content).getParent();
        contentFrameLayout.addView(mVoiceInputView, params);
        contentFrameLayout.requestLayout();
        addOnGlobalLayoutListener();
    }

    */
/**
 * 参数设置
 *//*

    @SuppressLint("SdCardPath")
    public void setSpeechParam() {
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin");
        speechRecognizer.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "120000");
    }

    private void startListening() {
        // 初始化识别对象
        if (speechRecognizer == null) {
            speechRecognizer = SpeechRecognizer.createRecognizer(this, mInitListener);
        }
        setSpeechParam();
        speechRecognizer.startListening(recognizerListener);
    }

    private void stopListening() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
        displaySpeakView();
    }

    */
/**
 * 初始化监听器。
 *//*

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            HLog.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code == ErrorCode.SUCCESS) {

            }
        }
    };

    */
/**
 * 听写监听器。
 *//*

    private RecognizerListener recognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
        }

        @Override
        public void onError(SpeechError error) {
            dismissDialog();
            createErrorDialog();
            displaySpeakView();
        }

        @Override
        public void onEndOfSpeech() {
            stopListening();
            createDialog();
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, String msg) {
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String text = JsonParserManager.parseIatResult(results.getResultString());

            View focusedView = getCurrentFocus();
            if (focusedView == null) {
                return;
            }

            if (focusedView instanceof EditText) {
                EditText etInput = (EditText) focusedView;
                etInput.getText().insert(etInput.getSelectionStart(), text);
            } else if (focusedView instanceof WebView) {
                WebView wvInput = (WebView) focusedView;
                WebViewFragment webViewFragment = WebViewFragment.getFromWebView(wvInput);
                if (webViewFragment == null) {
                    return;
                }

                webViewFragment.onVoiceInput(text);
            }

            dismissDialog();
            stopListening();
        }

        @Override
        public void onVolumeChanged(int volume) {
            mVolumeView.setVisibility(View.VISIBLE);
            mSpeakView.setVisibility(View.GONE);

            if (volume <= 0) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_1);
            } else if (volume > 0 && volume <= 10) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_2);
            } else if (volume > 10 && volume <= 20) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_3);
            } else if (volume > 20 && volume <= 30) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_4);
            } else if (volume > 30 && volume <= 40) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_5);
            } else if (volume > 40 && volume <= 50) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_6);
            } else if (volume > 50 && volume <= 60) {
                mVolumeImg.setImageResource(R.drawable.xunfei_voice_7);
            }
        }
    };

    private void displaySpeakView() {
        mSpeakView.setVisibility(View.VISIBLE);
        mVolumeView.setVisibility(View.GONE);
    }

    */
/**
 * 创建进度框
 *//*

    private void createDialog() {
        AlertDialogWidget.getInstance(this).createProgressDialog(
                getString(R.string.workdaily_msc_loading), getString(R.string.common_please_later));
        timerCancel();
    }

    private void timerCancel() {
        if (mTimer == null)
            mTimer = new Timer();

        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                Looper.prepare();
                dismissDialog();
                createErrorDialog();
                Looper.loop();
            }
        }, 20000);
    }

    */
/**
 * 创建进度框
 *//*

    private void createErrorDialog() {
        AlertDialogWidget.getInstance(this).createAlertDialog(com.hecom.ResUtil.getStringRes(com.hecom.mgm.R.string.wufashibie), com.hecom.ResUtil.getStringRes(com.hecom.mgm.R.string.wufashibienindeyuyin_jian), com.hecom.ResUtil.getStringRes(com.hecom.mgm.R.string.queren),
                new AlertDialogWidget.PopupDialogClick() {

                    @Override
                    public void onDialogBottomButtonClick() {
                    }
                });

    }

    */
/**
 * 进度框取消
 *//*

    private void dismissDialog() {
        AlertDialogWidget.getInstance(this).dismissProgressDialog();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    */
/**
 * @return
 *//*

    protected final String getUserTrackPageIdFromIntent() {
        return null;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.ACTION_DOWN == event.getAction() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
        }
        return super.dispatchKeyEvent(event);
    }

    */
/**
 * 子类已经继承此方法,以响应uiHandler.sendMessage()调用
 * 此方法会在UI线程被调用
 *
 * @param message
 *//*

    public void handleMessage(Message message) {
        Log.d("UserTrackActivity", "MESSAGE被忽略, message=" + message);
    }

    @Override
    public boolean isUIValid() {
        return (isCreated && !isFinishing() && !mDestroyed);
    }

    @Override
    public void onNetStatusChanged(NetReceiver.NetStatus netCode) {
        switch (netCode) {
            case NET_DISCONNECT:
                Log.d(TAG, "没有网络连接");
                break;
            case NET_2G:
                Log.d(TAG, "2g网络");
                break;
            case NET_3G:
                Log.d(TAG, "3g网络");
                break;
            case NET_4G:
                Log.d(TAG, "4g网络");
                break;
            case NET_WIFI:
                Log.d(TAG, "WIFI网络");
                break;
            case NET_UNKNOWN:
                Log.d(TAG, "未知网络");
                break;
            default:
                Log.d(TAG, "不知道什么情况~>_<~");
        }
    }

    ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        private int preHeight = 0;

        @Override
        public void onGlobalLayout() {
            View rootView = getWindow().getDecorView().getRootView();
            int windowHeight = rootView.getHeight();

            //通过decorView得到ContentFrameLayout
            FrameLayout contentFrameLayout = (FrameLayout) rootView.findViewById(android.R.id.content);
            int contentHeight = contentFrameLayout.getHeight();
            int heightDiff = windowHeight - contentHeight;

            if (preHeight == heightDiff) {
                return;
            }
            preHeight = heightDiff;

            if (heightDiff > 60) {
                onSoftInputMethodPopup();
            } else {
                onSoftInputMethodDismiss();
            }
        }
    };

    private void onSoftInputMethodPopup() {
        TraceUtil.log("onSoftInputMethodPopup");
        mVoiceInputView.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewUtil.dp2px(mContext, 50));
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mVoiceInputView.setLayoutParams(params);
        getWindow().getDecorView().getRootView().findViewById(android.R.id.content).requestLayout();

        mVoiceInputView.postDelayed(new Runnable() {
            @Override
            public void run() {
                View focusedView = getCurrentFocus();
                if (focusedView == null) {
                    return;
                }

                int coveredHeight = getCoveredHeight(focusedView, mVoiceInputView);
                if (coveredHeight == 0) {
                    return;
                }

                TraceUtil.log("covered View(" + focusedView.getClass().getSimpleName() + "), height = " + coveredHeight);
                getContentView().scrollBy(0, coveredHeight);
                mScrolledHeight = coveredHeight;
            }
        }, 100);
    }

    private View getContentView() {
        FrameLayout contentFrameLayout = (FrameLayout) getWindow().getDecorView().getRootView().findViewById(android.R.id.content);
        return contentFrameLayout.getChildAt(0);
    }

    private void onSoftInputMethodDismiss() {
        TraceUtil.log("onSoftInputMethodDismiss");

        mVoiceInputView.setVisibility(View.GONE);

        if (mScrolledHeight == 0) {
            return;
        }

        getContentView().scrollBy(0, -mScrolledHeight);
        mScrolledHeight = 0;
    }

    private void addOnGlobalLayoutListener() {
        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
    }

    private void removeOnGlobalLayoutListener() {
        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().removeGlobalOnLayoutListener(mGlobalLayoutListener);
    }

    private int getCoveredHeight(View upperView, View underView) {
        int[] location = new int[2];
        upperView.getLocationOnScreen(location);
        int upperViewBottom = location[1] + upperView.getHeight();

        underView.getLocationOnScreen(location);
        int underViewTop = location[1];

        return Math.max(upperViewBottom - underViewTop, 0);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (canShowDuangDialog()) {
            if (Config.isLoggedIn() && PrefUtils.getDuanghasneednotify()) {
                Duang duang;
                synchronized (DuangDataUtil.lock) {
                    duang = new DuangDaoUtil().getByK(PrefUtils.getDuangneednotifycode());
                }
                if (duang != null) {
                    Employee friend = EntMemberManager.getInstance().getEmployee(EntMemberSelectType.UID, duang.getUid());
                    if (friend != null) {
                        PrefUtils.setDuanghasneednotify(false);
                        PrefUtils.setDuangneednotifycode(null);
                        DuangShowDialogUtil.showDuangDialog(UserTrackActivity.this, duang);
                    }
                }
            }
        }
    }

    */
/**
 * 此Acitivy是否能弹出必达dialog
 *
 * @return
 *//*

    protected boolean canShowDuangDialog() {
        return true;
    }

    private void determinLocale() {
        String localestr = PrefUtils.getLocale();
        if (!TextUtils.isEmpty(localestr)) {
            Locale locale = new Locale(localestr);
            Locale.setDefault(locale);
            Configuration config = getBaseContext().getResources().getConfiguration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }


    */
/************************************
 * 授权相关开始
 ****************************************//*

    private AuthorityRulesValidator authorityRulesValidator = new AuthorityRulesValidator(new DataOwnerAuthorityRuleValidator(this));
    private boolean mHasPageAuthority = true;
    private boolean mHasPageAuthorityChecked = false;

    */
/**
 * 判断页面是否被授权
 * 可被多次调用
 *//*

    protected synchronized boolean hasPageAuthority() {
        if (!mHasPageAuthorityChecked) {
            mHasPageAuthority = authorityRulesValidator.validate(this.getClass());
            mHasPageAuthorityChecked = true;
        }
        return mHasPageAuthority;
    }

    */
/**
 * 在View绑定完成后，必须手动调用一次，注入权限
 * 控件如果没权限，自动隐藏； 布尔值会被注入结果
 *//*

    protected final void injectAuthority() {
        AuthorityUtils.injectAuthorty(this, authorityRulesValidator);
    }

    */
/**
 * 授权失败时的回调
 *//*

    protected void onPageAuthorizeFailed() {
        Bundle build = new BundleBuilder()
                .cancelable(false)
                .eventId(ListenerProvider.EventId.NoAuthorityAccess)
                .title(ResUtil.getStringRes(R.string.wenxintishi))
                .content(ResUtil.getStringRes(R.string.cimokuaiyibeiqiyeguanliyuan))
                .buttonText(ResUtil.getStringRes(R.string.zhidaole))
                .build();
        DialogHostActivity.showDialog(SOSApplication.getAppContext(), build);
        finish();
    }

    */
/**
 * 设置控件显示/隐藏，如果没有权限，自动设为GONE
 * 如果这个控件需要根据权限控制是否显示，你应该设用此方法，而不是{@link View#setVisibility(int)}
 *
 * @param view View不能为空
 *//*

    protected void setVisibility(final View view, int visibility) {
        view.setVisibility(visibility);
        if (visibility != View.GONE) {
            AuthorityUtils.hideViewIfNoAuthority(this, view, authorityRulesValidator);
        }
    }

    */
/**
 * 获取数据所有者，因为权限和数据所有者有关
 * 对应 {@link com.hecom.lib.authority.annotation.AuthorityRule}
 *
 * @param function 功能名称
 * @param action   行为，不会为空
 * @return 返回null代表公司组织架构根结点
 *//*

    @Override
    public DataOwner getDataOwner(String function, String action) {
        return null;
    }

    */
/**
 * 获取数据所有者，因为权限和数据所有者有关
 * 对应 {@link com.hecom.lib.authority.annotation.AuthorityPage}
 *
 * @param authorityPage {@link com.hecom.application.HQPages}
 * @return 返回null代表公司组织架构根结点
 *//*

    @Override
    public DataOwner getDataOwner(String authorityPage) {
        return null;
    }

    */
/************************************    授权相关结束   ****************************************//*


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
*/


}
