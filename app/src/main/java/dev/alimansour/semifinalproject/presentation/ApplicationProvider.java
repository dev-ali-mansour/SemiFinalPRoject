package dev.alimansour.semifinalproject.presentation;

import android.app.Application;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class ApplicationProvider {
    private static Application app;
    private static ApplicationProvider instance;

    private ApplicationProvider() {
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application application) {
        ApplicationProvider.app = application;
    }

    public static ApplicationProvider getInstance() {
        if (instance == null) {
            instance = new ApplicationProvider();
        }
        return instance;
    }

}
