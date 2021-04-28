package io.stattrack.stattrack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApiHandlingTests {
    private List<String> tests;

    @Before
    public void init(){
        tests = new ArrayList<>(Arrays.asList("test1","test2"));
    }

    @Test
    public void UserInfoTest(){

    }

    @After
    public void cleanup(){
        tests.clear();
    }
}
