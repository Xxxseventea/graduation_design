package http;


import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public interface MyCallBack {

    void onLoadingBefore(Request request);

    void onSuccess(Response response) throws IOException;

    void onFailure(Request request, Exception e);

    void onError(Response response);

}
