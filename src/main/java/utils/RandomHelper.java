package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomHelper {

    public String getRandomAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String getRandomNumberAsString(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String getRandomAlphabeticString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

}
