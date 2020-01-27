package com.alimasanov.unsplashappjava.server;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private String clientID;
    public HeaderInterceptor(String clientID){
        this.clientID = clientID;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Authorization", "Client-ID" + clientID)
                .build();
        return chain.proceed(request);
    }
}
