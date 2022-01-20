package com.huzaifa.webfluxdemo.service;

import lombok.SneakyThrows;

public class SleepUtil {

    @SneakyThrows
    public static void sleepSeconds(int seconds){
        Thread.sleep(seconds * 1000);
    }
}
