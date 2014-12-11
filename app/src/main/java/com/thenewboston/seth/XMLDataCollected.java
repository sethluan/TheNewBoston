package com.thenewboston.seth;

/**
 * Created by 22707561 on 12/11/2014.
 */
public class XMLDataCollected {
    float temp = 0;
    String city = null;

    public void setCity(String c) {
        city = c;
    }

    public void setTemp(float t){
        temp = t;
    }

    public String dataToString(){
        return "In " + city + " the current temperature in C is " + temp + " degrees";
    }
}
