package com.newNopCommerce;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Yogesh on 15-09-2016.
 */

@RunWith(Cucumber.class)
@CucumberOptions(format = "html:target/CucumberReport",
        features = "C:/Users/Yogesh/IdeaProjects/NewnopCommerce/src/test/feature/",
        tags = "@smoke")

public class RunTest {
}
