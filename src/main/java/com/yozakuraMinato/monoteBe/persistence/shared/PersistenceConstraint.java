package com.yozakuraMinato.monoteBe.persistence.shared;

public class PersistenceConstraint {

    public static final String SOFT_DELETE_RESTRICTION = "is_deleted = false";

    public static class Entity {
        public static final int SHORT_TEXT_LENGTH = 100;
        public static final String LONG_TEXT = "TEXT";

        public static final int ENUM_LENGTH = 50;

        public static final int CURRENCY_SCALE_LENGTH = 14;
        public static final int CURRENCY_PRECISION_LENGTH = 2;
    }

}
