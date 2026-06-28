package com.yozakuraMinato.monoteBe.account.shared;

public class AccountMessage {

    private static final String account = "account.";
    private static final String userId = account + "userId.";
    private static final String name = account + "name.";

    public static class UserId {
        public static final String isNull = userId + "isNull";
    }

    public static class Name {
        public static final String isNull = name + "isNull";
        public static final String alreadyExists = name + "alreadyExists";
        public static final String hasInvalidSize = name + "hasInvalidSize";
    }

}
