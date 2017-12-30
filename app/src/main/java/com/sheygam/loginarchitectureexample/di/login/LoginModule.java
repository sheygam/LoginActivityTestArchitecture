package com.sheygam.loginarchitectureexample.di.login;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.sheygam.loginarchitectureexample.business.login.ILoginInteractor;
import com.sheygam.loginarchitectureexample.business.login.LoginInteractor;
import com.sheygam.loginarchitectureexample.data.ILoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.ILoginWebRepository;
import com.sheygam.loginarchitectureexample.data.LoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.LoginWebRepository;
import com.sheygam.loginarchitectureexample.presentation.login.presenter.ILoginPresenter;
import com.sheygam.loginarchitectureexample.presentation.login.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by gregorysheygam on 30/12/2017.
 */
@Module
public class LoginModule {

    @Provides
    @LoginScope
    Gson provideGson(){
        return new Gson();
    }

    @Provides
    @LoginScope
    Handler provideHandler(){
        return new Handler();
    }

    @Provides
    @LoginScope
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @LoginScope
    ILoginWebRepository provideWebRepository(Gson gson, OkHttpClient client){
        return new LoginWebRepository(gson,client);
    }

    @Provides
    @LoginScope
    ILoginStoreRepository provideStroreRepository(Context context){
        return new LoginStoreRepository(context);
    }

    @Provides
    @LoginScope
    ILoginInteractor provideLoginInteractor(ILoginWebRepository webRepository, ILoginStoreRepository storeRepository){
        return new LoginInteractor(webRepository,storeRepository);
    }

    @Provides
    @LoginScope
    ILoginPresenter provideLoginPresenter(ILoginInteractor interactor, Handler handler){
        return new LoginPresenter(interactor,handler);
    }
}