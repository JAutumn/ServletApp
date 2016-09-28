package com.servlet.app.common.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;

import com.servlet.app.common.dao.AvatarDAO;
import com.servlet.app.common.exceptions.CannotDownloadAvatarException;

public class AvatarService {
    private AvatarDAO avatarDAO;


    public AvatarService() {
        avatarDAO = new AvatarDAO();
    }

    public void downloadData(Long userId, OutputStream _outputStream, String noAvatarPath) throws IOException {
        Optional<InputStream> data = findData(userId);
        OutputStream outputStream = new BufferedOutputStream(_outputStream);
        if (data.isPresent()) {
            InputStream inputStream = new BufferedInputStream(data.get());
            int bytesRead;
            while ((bytesRead = inputStream.read()) != -1) {
                outputStream.write(bytesRead);
            }
        } else {
            byte[] noAvatarPicture = Files.readAllBytes(Paths.get(noAvatarPath));
            outputStream.write(noAvatarPicture);
        }
    }

    private Optional<InputStream> findData(Long userId) {
        try {
            return avatarDAO.findData(userId);
        } catch (SQLException e) {
            throw new CannotDownloadAvatarException(userId.toString());
        }
    }

}
