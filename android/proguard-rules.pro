# ============================================
# Flutter 应用简化版 ProGuard 规则
# 专为您手机优化，最大程度减少内存
# ============================================

# 1. Flutter 核心
-keep class io.flutter.** { *; }
-keep class io.flutter.app.** { *; }
-keep class io.flutter.plugin.** { *; }

# 2. 您的应用包（替换为您实际的包名）
-keep class com.piliX.** { *; }

# 3. Android 组件
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.view.View

# 4. 移除日志（节省内存）
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# 5. 保留资源ID
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 6. 基本优化
-dontobfuscate
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# 7. 忽略警告（减少构建噪音）
-dontwarn **