# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\work\android_sdk\android-sdk-windows/tools/proguard/proguard-android.txt
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

##不混淆第三方jar包
-libraryjars libs/MiPush_SDK_Client_2_2_19.jar
-libraryjars libs/HwPush_SDK_V2705_nomap.jar
-libraryjars libs/google-play-services.jar
-libraryjars libs/BaiduLBS_Android.jar

-keep class com.google.**{*;}
-dontwarn  com.google.**

-keep class com.huawei.**{*;}
-dontwarn  com.huawei.**

-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**

-keep class org.apache.**{*;}
-keep public class * extends org.apache.**  
