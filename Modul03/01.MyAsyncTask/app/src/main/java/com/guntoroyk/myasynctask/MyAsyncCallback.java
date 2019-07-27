package com.guntoroyk.myasynctask;

public interface MyAsyncCallback {
    void onPreExecute();
    void onPostExecute(String text);

}
