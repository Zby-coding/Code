package com.zby.test;

import org.junit.jupiter.api.Test;

public class UploadTest {

    @Test
    public void test(){
        String name="9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg";
        String substring = name.substring(name.lastIndexOf("."));//.jpeg
        System.out.println(substring);
    }
}
