package org.thekiddos.educationalportal.models;

import lombok.Data;
import org.thekiddos.educationalportal.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class User {
    @Id @NotNull @NotBlank @Column( unique = true ) @Size( min = 3)
    private String username;
    @NotNull @NotBlank
    private String password;
    @NotNull @NotBlank
    private String type;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private Set<Course> enrolledInCourses;

    public boolean isStudent() {
        return type.equals( UserType.STUDENT );
    }

    public boolean isInstructor() {
        return type.equals( UserType.INSTRUCTOR );
    }

    public boolean checkPassword( String password ) {
        return Utils.PASSWORD_ENCODER.matches( password, getPassword() );
    }

    public void setPassword( String password ) {
        this.password = Utils.PASSWORD_ENCODER.encode( password );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        User user = (User) o;
        return username.equals( user.username );
    }

    @Override
    public int hashCode() {
        return Objects.hash( username );
    }

    public Set<Course> getEnrolledInCourses() {
        return Collections.unmodifiableSet( enrolledInCourses );
    }

    public void addCourse( Course course ) {
        if ( !isStudent() )
            throw new RuntimeException( "Not a student" );

        enrolledInCourses.add( course );
    }
}
