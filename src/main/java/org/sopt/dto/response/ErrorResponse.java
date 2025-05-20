package org.sopt.dto.response;

public class ErrorResponse {
    private final boolean success;
    private final String error;

    public ErrorResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getErrpr(){
        return error;
    }
}
