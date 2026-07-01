package com.yozakuraMinato.monoteBe.account.shared;

public class AccountMessage {

    private static final String account = "account.";
    private static final String id = account + "id.";
    private static final String userId = account + "userId.";
    private static final String name = account + "name.";
    private static final String status = account + "status.";

    public static class Id {
        public static final String notFound = id + "notFound";
    }

    public static class UserId {
        public static final String isNull = userId + "isNull";
    }

    public static class Name {
        public static final String isNull = name + "isNull";
        public static final String alreadyExists = name + "alreadyExists";
        public static final String hasInvalidSize = name + "hasInvalidSize";
    }

    public static class Status {
        public static final String isNull = status + "isNull";
    }

}
