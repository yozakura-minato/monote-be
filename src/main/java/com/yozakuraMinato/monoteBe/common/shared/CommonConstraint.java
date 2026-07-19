package com.yozakuraMinato.monoteBe.common.shared;

public class CommonConstraint {

    public static class Regex {
        public static final String UPPER_REGEX = "[A-Z]";
        public static final String LOWER_REGEX = "[a-z]";
        public static final String DIGIT_REGEX = "[0-9]";
        public static final String SPECIAL_REGEX = "[^A-Za-z0-9]";
    }

    public static class PlainPassword {
        public static final int MINIMAL_SIZE = 8;
        public static final int MAXIMAL_SIZE = 64;
        public static final int MINIMAL_LEVEL = 2;
    }

}
