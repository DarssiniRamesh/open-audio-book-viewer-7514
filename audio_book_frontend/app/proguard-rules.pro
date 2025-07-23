# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# For ExoPlayer and Coil
-keep class com.google.android.exoplayer2.** { *; }
-keep class coil.** { *; }
-keep class androidx.compose.** { *; }
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

# Glide/Coil image loading
-dontwarn coil.**
-dontwarn okhttp3.**
-dontwarn okio.**
