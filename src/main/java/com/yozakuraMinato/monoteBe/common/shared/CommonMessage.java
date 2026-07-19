package com.yozakuraMinato.monoteBe.common.shared;

public class CommonMessage {

    private static final String GENERAL = "general.";
    private static final String PAGINATION = GENERAL + "pagination";

    public static final String BAD_REQUEST = GENERAL + "badRequest";
    public static final String UNAUTHORIZED = GENERAL + "unauthorized";
    public static final String INTERNAL_SERVER_ERROR = GENERAL + "internalServerError";

    public static class Pagination {
        public static final String HAS_INVALID_SIZE = PAGINATION + "hasInvalidSize";
        public static final String HAS_INVALID_PAGE = PAGINATION + "hasInvalidPage";
    }


}
