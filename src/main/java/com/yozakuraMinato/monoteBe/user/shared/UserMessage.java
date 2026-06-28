package com.yozakuraMinato.monoteBe.user.shared;

public class UserMessage {

    private static final String user = "user.";
    private static final String id = user + "id.";
    private static final String email = user + "email.";
    private static final String password = user + "password.";
    private static final String displayName = user + "displayName.";

    public static class Id {
        public static final String notFound = id + "notFound";
    }

    public static class Email {
        public static final String isNull = email+ "isNull";
        public static final String isInvalid = email + "isInvalid";
        public static final String alreadyExists = email + "alreadyExists";
        public static final String notFound = email + "notFound";
    }

    public static class Password {
        public static final String isNull = password + "isNull";
        public static final String hasInvalidSize = password + "hasInvalidSize";
        public static final String isWeak = password + "isWeak";
    }

    public static class DisplayName {
        public static final String isNull = displayName + "isNull";
        public static final String hasInvalidSize = displayName + "hasInvalidSize";
    }

}
