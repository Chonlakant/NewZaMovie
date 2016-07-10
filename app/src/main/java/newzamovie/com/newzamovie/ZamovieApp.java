package newzamovie.com.newzamovie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.Random;

import newzamovie.com.newzamovie.event.ApiBus;
import newzamovie.com.newzamovie.service.ApiHandler;
import newzamovie.com.newzamovie.service.ApiService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


/**
 * Created by madhur on 3/1/15.
 */
public class ZamovieApp extends Application implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = ZamovieApp.class
            .getSimpleName();

    public static final String API_ENDPOINT = "http://27.254.95.154";

    public static Activity currentActivity;

    public static volatile Handler applicationHandler = null;
    private ApiHandler someApiHandler;
    private static ZamovieApp Instance;
    private static OkHttpClient sHttpClient;
    private static Context sContext = null;




    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        Instance = this;
        applicationHandler = new Handler(getInstance().getMainLooper());
        saveInstallation(0);


        someApiHandler = new ApiHandler(this, buildApi(), ApiBus.getInstance());
        someApiHandler.registerForEvents();
    }

    public static Context getAppContext() {
        return sContext;
    }


    public static OkHttpClient getHttpClient() {
        if (sHttpClient == null) {
            sHttpClient = new OkHttpClient();
            int cacheSize = 10 * 1024 * 1024;
            File cacheLocation = new File(StorageUtils.getIdealCacheDirectory(ZamovieApp.getAppContext()).toString());
            cacheLocation.mkdirs();
            com.squareup.okhttp.Cache cache = new com.squareup.okhttp.Cache(cacheLocation, cacheSize);
            sHttpClient.setCache(cache);
        }
        return sHttpClient;
    }




    ApiService buildApi() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })

                .build()
                .create(ApiService.class);
    }

    public static void saveInstallation(int userId) {
    }
    public static void removeInstallation(int userId) {

    }

    public static ZamovieApp get(Context context) {
        return (ZamovieApp) context.getApplicationContext();
    }



    public static ZamovieApp getInstance() {
        return Instance;
    }

    public static boolean applicationOnPause = false;
    @Override
    public void onActivityCreated(Activity arg0, Bundle arg1) {
        currentActivity = arg0;
        Log.e("VMVMVM", "onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e("VMVMVM", "onActivityDestroyed ");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
        applicationOnPause = false;
        Log.e("VMVMVM", "onActivityResumed " + activity.getClass());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        applicationOnPause = true;
        Log.e("VMVMVM", "onActivityPaused " + activity.getClass());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e("VMVMVM", "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e("VMVMVM", "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e("VMVMVM", "onActivityDestroyed ");
    }


}
