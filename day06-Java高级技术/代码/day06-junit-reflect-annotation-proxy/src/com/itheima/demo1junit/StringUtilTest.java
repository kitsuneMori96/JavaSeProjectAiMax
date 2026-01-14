package com.itheima.demo1junit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StringUtilTest {
     @Test
    public void testPrintNumber(){
         StringUtil.printNumber("张三");
         StringUtil.printNumber(null);
         StringUtil.printNumber("");
     }

     @Test
     public void testGetMaxIndex(){
         StringUtil.getMaxIndex("abcde");
         StringUtil.getMaxIndex("");
         StringUtil.getMaxIndex(null);
        Assert.assertEquals(4,StringUtil.getMaxIndex("abcde"));
        Assert.assertEquals(-1,StringUtil.getMaxIndex(""));
        Assert.assertEquals(-1,StringUtil.getMaxIndex(null));
     }

 }