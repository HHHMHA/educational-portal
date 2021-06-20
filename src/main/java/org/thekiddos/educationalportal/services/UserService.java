package org.thekiddos.educationalportal.services;

import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User login( String username, String password );

    User register( String username, String password, String type, boolean login );

    void logout();

    User getAuthenticatedUser();

    List<User> getStudentsForCourse( Course course );
}
