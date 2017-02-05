# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\soft\android-tool\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#解决泛型问题
-keepattributes Signature
-keepattributes *Annotation*
#-keep class sun.misc.Unsafe { *; }

# eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-dontwarn org.xmlpull.v1.XmlPullParser
-dontwarn org.xmlpull.v1.XmlSerializer
-dontwarn org.codehaus.**
-dontwarn java.nio.**
-keep public class org.codehaus.** {*;}
-keep public class java.nio.** {*;}

-keep public class * implements java.io.Serializable {
   public *;
}

# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** {*;}

# ormlite
-keep class com.j256.ormlite.**{*;}

-keep class com.j256.**<br>
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }
-keepclassmembers class com.qzj.facial.common.db.dao.** {
  public *;
}

# bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# 保留原文件名和行号
-keepattributes SourceFile,LineNumberTable

# baidu map
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

# anyversion
-keep public class com.github.yoojia.anyversion.**{*;}

# retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# rxjava
-dontwarn rx.**
-keep class rx.** { *; }

# 6.0权限
-dontwarn com.zhy.m.**
-keep class com.zhy.m.** {*;}
-keep interface com.zhy.m.** { *; }
-keep class **$$PermissionProxy { *; }

# 不混淆bean
-keep class com.chinamobile.streetlight.user.bean.** { *; }
-keep class com.chinamobile.streetlight.lightmode.bean.** { *; }
-keep class com.chinamobile.streetlight.main.bean.** { *; }
-keep class com.chinamobile.streetlight.lightmanagement.bean.** { *; }
-keep class com.chinamobile.streetlight.views.bean.** { *; }
-keep class com.chinamobile.streetlight.energy.bean.** { *; }
-keep class com.chinamobile.streetlight.assistterminal.bean.** { *; }