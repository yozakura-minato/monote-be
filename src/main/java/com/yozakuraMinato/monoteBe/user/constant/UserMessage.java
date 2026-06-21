package com.yozakuraMinato.monoteBe.user.constant;

public class UserMessage {

    private static final String id = "user.id.";
    private static final String email = "user.email.";
    private static final String password = "user.password.";

    public class Id {
        public static final String notFound = password + "notFound";
    }

    public class Email {
        public static final String isNull = email+ "isNull";
        public static final String isInvalid = email + "isInvalid";
        public static final String alreadyExists = email + "alreadyExists";
    }

    public class Password {
        public static final String isNull = password + "isNull";
        public static final String hasInvalidSize = password + "hasInvalidSize";
        public static final String isWeak = password + "isWeak";
    }

}
