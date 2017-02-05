package com.qzj.facial.common.data;

import com.qzj.facial.bean.FacePart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qzj.
 * Date: 2017/2/4
 */
public class FacePartUtils {

    public static List<FacePart> getPicFacePartList(String tag) {
        int typeCount = 0;
        String partTag = "";

        switch (tag.trim()) {
            case "脸型":
                partTag = "face";
                typeCount = 74;
                break;
            case "眼睛":
                partTag = "s4";
                typeCount = 52;
                break;
            case "眉毛":
                partTag = "eyebrow";
                typeCount = 30;
                break;
            case "嘴巴":
                partTag = "mouth";
                partTag = "s5";
                typeCount = 0;
                break;
            case "鼻子":
                partTag = "nose";
                partTag = "s14";
                typeCount = 13;
                break;
            case "胡子":
                partTag = "mustache";
                typeCount = 0;
                break;
            case "眼镜":
                partTag = "glasses";
                partTag = "s7";
                typeCount = 0;
                break;
        }

        List<FacePart> list = new ArrayList<>();

        for (int i=0; i<typeCount; i++) {
            FacePart fp = new FacePart();
            fp.imgName = "pic_"+partTag+"_"+i+".png";

            list.add(fp);
        }

        return list;
    }

}
