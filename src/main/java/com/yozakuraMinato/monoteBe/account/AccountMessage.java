package com.yozakuraMinato.monoteBe.account;

public class AccountMessage {

    private static final String ACCOUNT = "account.";
    private static final String ID = ACCOUNT + "id.";
    private static final String USER_ID = ACCOUNT + "userId.";
    private static final String NAME = ACCOUNT + "name.";
    private static final String STATUS = ACCOUNT + "status.";

    public static class Id {
        public static final String NOT_FOUND = ID + "notFound";
    }

    public static class UserId {
        public static final String IS_NULL = USER_ID + "isNull";
    }

    public static class Name {
        public static final String IS_NULL = NAME + "isNull";
        public static final String ALREADY_EXISTS = NAME + "alreadyExists";
        public static final String HAS_INVALID_SIZE = NAME + "hasInvalidSize";
    }

    public static class Status {
        public static final String IS_NULL = STATUS + "isNull";
    }

}
