package org.thekiddos.educationalportal.services;

import org.thekiddos.educationalportal.models.User;

public interface UserService {
    User login( String username, String password );

    User register( String username, String password, String type, boolean login );

    void logout();

    User getAuthenticatedUser();
}
