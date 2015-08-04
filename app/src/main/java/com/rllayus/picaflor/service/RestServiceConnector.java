package com.rllayus.picaflor.service;

import com.rllayus.picaflor.utils.PicaflorPreference;;
import retrofit.RestAdapter;

public class RestServiceConnector {

    private static IDataRestService mServiceMethods;

    public static IDataRestService getDataRestService() {
        if (mServiceMethods == null) {

            String restServiceUrl = PicaflorPreference.getInstance().getRestServiceUrl();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(restServiceUrl)
                    .build();
            mServiceMethods = restAdapter.create(IDataRestService.class);
        }
        return mServiceMethods;
    }

}
