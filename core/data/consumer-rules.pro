-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.Result
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keep class * implements com.pablojuice.core.data.remote.api.ApiRequest{*;}
-keep class * implements com.pablojuice.core.data.remote.api.ApiResponse{*;}
-keep class * implements com.pablojuice.core.data.remote.api.Api{*;}
