package com.servlet.app.common.exceptions;

public class CannotDownloadAvatarException extends AppRuntimeException  {

    public CannotDownloadAvatarException(String param) {
        super(String.format("Can't download avatar for user %s", param));
    }

    public CannotDownloadAvatarException(Throwable cause) {
        super(cause);
    }
}
