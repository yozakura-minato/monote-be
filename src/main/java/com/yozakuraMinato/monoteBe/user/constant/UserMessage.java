package com.yozakuraMinato.monoteBe.user.constant;

public class UserMessage {

    private static final String USER = "user.";
    private static final String ID = USER + "id.";
    private static final String EMAIL = USER + "email.";
    private static final String PASSWORD = USER + "password.";
    private static final String DISPLAY_NAME = USER + "displayName.";

    public static class Id {
        public static final String notFound = ID + "notFound";
    }

    public static class Email {
        public static final String IS_NULL = EMAIL + "isNull";
        public static final String IS_INVALID = EMAIL + "isInvalid";
        public static final String ALREADY_EXISTS = EMAIL + "alreadyExists";
        public static final String NOT_FOUND = EMAIL + "notFound";
    }

    public static class Password {
        public static final String IS_NULL = PASSWORD + "isNull";
        public static final String HAS_INVALID_SIZE = PASSWORD + "hasInvalidSize";
        public static final String IS_WEAK = PASSWORD + "isWeak";
    }

    public static class DisplayName {
        public static final String IS_NULL = DISPLAY_NAME + "isNull";
        public static final String HAS_INVALID_SIZE = DISPLAY_NAME + "hasInvalidSize";
    }

}
