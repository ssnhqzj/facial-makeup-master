package com.qzj.facial.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chinamobile.streetlight.R;
import com.qzj.facial.adpter.IndicatorPagerAdapter;
import com.qzj.facial.base.BaseActivity;
import com.qzj.facial.common.toolbar.Toolbar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements IndicatorPagerAdapter.OnIndicatorPagerItemListener {

    private static final String[] CHANNELS = new String[]{" 脸型 ", " 鼻子 ", " 眉毛 ", " 嘴巴 ", " 眼睛 ", " 胡子 ", " 眼镜 "};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private IndicatorPagerAdapter mIndicatorAdapter;

    private ViewPager mViewPager;
    private RelativeLayout mCompoundRoot;
    private ImageView mCompoundFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.face_view_pager);
        mCompoundRoot = (RelativeLayout) findViewById(R.id.face_compound_root);
        mCompoundFace = (ImageView) findViewById(R.id.face_compound_face);

        mIndicatorAdapter = new IndicatorPagerAdapter(this, mDataList);
        mIndicatorAdapter.setmItemListener(this);
        mViewPager.setAdapter(mIndicatorAdapter);

        initToolBar();
        initMagicIndicator();
    }

    private void initToolBar(){
        Toolbar tb = new Toolbar();
        tb.setBg(R.color.colorPrimary);
        tb.setCenterText("脸谱");
        tb.setRightText("合成");

        initToolBar(tb);
    }

    /**
     * 初始化面部组件导航栏
     */
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.face_magic_indicator);
        magicIndicator.setBackgroundColor(Color.parseColor("#455a64"));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#88ffffff"));
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return indicator;
            }
        });

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    /**
     * 合成图片
     */
    private void compoundPic(){
        mCompoundRoot.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mCompoundRoot.getDrawingCache());

        // 清缓存
        mCompoundRoot.destroyDrawingCache();
        int w = mCompoundRoot.getWidth();
        Bitmap editbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());

        String fileName = System.currentTimeMillis() + ".png";
        File parentpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(parentpath, "合成测试/" + fileName);
        file.getParentFile().mkdirs();
        try {
            editbmp.compress(Bitmap.CompressFormat.PNG, 80, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }

        editbmp.recycle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_right:
                compoundPic();
                break;
        }
    }

    @Override
    public void onItemSelected(String typeName, String path) {
        Log.e("qzj","typename:"+typeName+",path:"+path);

        switch (typeName.trim()){
            case "脸型":
                Glide.with(this).load(path).into(mCompoundFace);
                break;

            case "鼻子":
                ImageView noseIv = new ImageView(this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                noseIv.setLayoutParams(params);
                mCompoundRoot.addView(noseIv);

                Glide.with(this).load(path).into(noseIv);
                break;
        }
    }
}
