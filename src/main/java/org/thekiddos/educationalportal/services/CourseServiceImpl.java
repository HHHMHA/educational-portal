package org.thekiddos.educationalportal.services;

import org.springframework.stereotype.Service;
import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;
import org.thekiddos.educationalportal.reposotories.CourseRepository;
import org.thekiddos.educationalportal.reposotories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl( CourseRepository courseRepository, UserRepository userRepository ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addCourse( User instructor, String name, String description ) {
        if ( !instructor.isInstructor() )
            throw new RuntimeException( "You can't create a course" );

        Course course = new Course();
        course.setName( name );
        course.setDescription( description );
        course.setInstructor( instructor );
        courseRepository.save( course );
    }

    @Override
    public List<Course> getCourses( User user ) {
        if ( user.isInstructor() )
            return courseRepository.findAllByInstructor( user );

        if ( user.isStudent() ) {
            var refreshedUser = userRepository.findById( user.getUsername() ).orElseThrow( () -> new RuntimeException( "Something went wrong" ) );
            return List.copyOf( refreshedUser.getEnrolledInCourses() );
        }

        return new ArrayList<>();
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public void enrollInCourse( Course course, User student ) {
        if ( !student.isStudent() )
            throw new RuntimeException( "You are not a student" );

        if ( student.getEnrolledInCourses().contains( course ) )
            throw new RuntimeException( "You already enrolled" );

        student.addCourse( course );
        userRepository.save( student );
    }
}
