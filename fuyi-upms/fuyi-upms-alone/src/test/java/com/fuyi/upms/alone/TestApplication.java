package com.fuyi.upms.alone;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootTest
public class TestApplication {

    @Test
    public void test1() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("root");
        System.out.println(encode);
    }

    @Test
    public void test2() {
        Integer[] src = new Integer[] {1, 2, 3, 4};
        Integer[] dest = new Integer[src.length - 1];
        System.arraycopy(src, 1, dest, 0, dest.length);

        System.out.print(Arrays.asList(dest));
    }
}
