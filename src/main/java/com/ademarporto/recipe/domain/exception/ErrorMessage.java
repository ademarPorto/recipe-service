package com.ademarporto.recipe.domain.exception;

public class ErrorMessage {

    private ErrorMessage() {
    }
    public static final String RECIPE_NOT_FOUND_CODE = "ERROR_001";
    public static final String RECIPE_NOT_FOUND_MESSAGE = "Not found any recipe with id [ %s ]";
    public static final String NOT_READABLE_REQUEST_BODY_CODE = "ERROR_006";
    public static final String NOT_READABLE_REQUEST_BODY_MESSAGE =  "Malformed JSON request.";
    public static final String METHOD_ARGUMENT_NOT_VALID_CODE = "ERROR_007";
    public static final String METHOD_ARGUMENT_NOT_VALID_CODE_MESSAGE =
            "Invalid parameter: [ %s ], cause: field [ %s ].";



}
