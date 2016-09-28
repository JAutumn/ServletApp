package com.servlet.app.common.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.common.AppStarter;
import com.servlet.app.common.services.AvatarService;
import com.servlet.app.core.wrappers.IdAwareHttpServletRequest;
import com.servlet.app.core.wrappers.IdAwareHttpServletWrapper;

@WebServlet("/pages/avatars/*")
public class AvatarDownloadController extends IdAwareHttpServletWrapper {
    private AvatarService avatarService;

    @Override
    public void init() throws ServletException {
        avatarService = new AvatarService();
    }

    @Override
    protected void processIdAwareGet(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = req.getIdFromRequest();
        String noAvatarPath = getServletContext().getRealPath(AppStarter.getNoAvatarPath());
        avatarService.downloadData(userId, resp.getOutputStream(), noAvatarPath);
    }

    @Override
    protected void processIdAwarePost(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new UnsupportedOperationException(String.format("POST method unsupported in %s", getClass().getName()));
    }
}
