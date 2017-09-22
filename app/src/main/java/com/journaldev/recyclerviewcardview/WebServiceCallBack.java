package com.journaldev.recyclerviewcardview;

import java.io.IOException;

/**
 * Created by inforlinx on 12/4/15.
 */
public interface WebServiceCallBack {
    int getLayoutResource();

    void onJSONResponse(String jsonResponse, String type) throws IOException;

    void onFailure();
}

