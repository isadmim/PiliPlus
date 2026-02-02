android {
    // ... 其他配置 ...
    
    signingConfigs {
        create("release") {
            // ✅ 方法1：从gradle.properties读取（推荐）
            storeFile = file("upload-keystore.jks")
            storePassword = project.findProperty("storePassword") as String? ?: System.getenv("STORE_PASSWORD") ?: ""
            keyAlias = project.findProperty("keyAlias") as String? ?: System.getenv("KEY_ALIAS") ?: "upload"
            keyPassword = project.findProperty("keyPassword") as String? ?: System.getenv("KEY_PASSWORD") ?: ""
            
            // 验证配置
            if (storePassword.isNullOrEmpty() || keyPassword.isNullOrEmpty()) {
                logger.warn("⚠️ 签名密码未设置，使用调试密钥")
                storeFile = file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            } else {
                println("✅ 使用发布签名配置")
            }
        }
    }
    
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            // ... 其他配置 ...
        }
    }
}
buildTypes {
    release {
        isMinifyEnabled = true
        isShrinkResources = true
        
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"  // 确保这里正确
        )
        
        // 其他配置...
    }
}
android {
    compileSdk = 33
    
    defaultConfig {
        // ✅ 仅保留arm64架构
        ndk {
            abiFilters.add("arm64-v8a")
        }
    }
    
    buildTypes {
        release {
            // ✅ 启用资源缩减和代码混淆
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            
            // ✅ R8完整模式配置（代码方式）
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
        }
    }
    
    // ✅ 编译选项优化
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
}