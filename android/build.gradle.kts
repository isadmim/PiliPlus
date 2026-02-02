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