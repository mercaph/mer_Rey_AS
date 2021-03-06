package com.example.a1;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class a1dMerca extends AppCompatActivity {
    public static List<Taskk2>mDatas1;
    public static List<Taskk2>mDatas2;
    public static int tab_c=-1;
    public static int wor_pos = -1;

    public static NfcAdapter mNFC_Adp;
    public static NdefMessage mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_1);
        //mNFC_Adp = NfcAdapter.getDefaultAdapter(this);
        //NfcCheck();
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        //设置adapter，滑动事件
        final ViewPager mViewPager = findViewById(R.id.vp_content);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //绑定tab点击事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab_c = tab.getPosition();
                mViewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
      // mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_ts(), Locale.ENGLISH, true)});

        //mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_re(), Locale.ENGLISH, true)});

//        switch (tab_c){
//            case 0:
//                mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_re(), Locale.ENGLISH, true)});
//                break;
//            case 1:
//                if (wor_pos!=-1 || wor_pos<=mDatas2.size()){
//                    mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_wk(), Locale.ENGLISH, true)});
//                    break;
//                }else {
//                    break;
//                }
//
//            default:mMessage = new NdefMessage(new NdefRecord[]{newTextRecord("none_defult", Locale.ENGLISH, true)});break;
//        }


    }


    @Override
    protected void onStop() {
        writeListIntoSDcard("remainRes",mDatas1);
        writeListIntoSDcard("workRes",mDatas2);
        super.onStop();
    }

    /**
     * NFC
     */
//    public void onResume() {
//        super.onResume();
//        mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_re(), Locale.ENGLISH, true)});
//        if (mNFC_Adp != null) mNFC_Adp.enableForegroundNdefPush(this, mMessage);
//    }
//
//    public void onPause() {
//        super.onPause();
//        if (mNFC_Adp != null) mNFC_Adp.disableForegroundNdefPush(this);
//    }

    /**
     * write into sdcard (object)
     * @param list
     */
    public static void writeListIntoSDcard(String str1,List<Taskk2> list){
        if (new OutputUtil<Taskk2>().writeListIntoSDcard(str1, list)) {
           // Toast.makeText(a1dMerca.this, "写入SD卡成功", Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(a1dMerca.this, "写入SD卡失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * nfc功能是否打开
     */
//
//    private void NfcCheck() {
//        mNFC_Adp = NfcAdapter.getDefaultAdapter(this);
//        if (mNFC_Adp == null) {
//            Toast.makeText(getApplicationContext(), "Where's your NFC?", Toast.LENGTH_SHORT).show();
//            finish();
//        } else {
//            if (!mNFC_Adp.isEnabled()) {
//                Intent setNfc = new Intent(Settings.ACTION_NFC_SETTINGS);
//                startActivity(setNfc);
//            }
//        }
//    }



    /**
     * NFC编码
     * @param text
     * @param locale
     * @param encodeInUtf8
     * @return
     */

    public static NdefRecord newTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);

        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

}
