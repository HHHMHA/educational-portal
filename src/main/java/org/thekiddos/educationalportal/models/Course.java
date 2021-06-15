package org.thekiddos.educationalportal.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
public class Course {
    @Id @GeneratedValue
    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String description;

    @ManyToOne( cascade = CascadeType.DETACH ) @NotNull
    User instructor;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Course course = (Course) o;
        return name.equals( course.name ) &&
                description.equals( course.description ) &&
                instructor.equals( course.instructor );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, description, instructor );
    }

    @Override
    public String toString() {
        return "Course(" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instructor=" + instructor.getUsername() +
                ')';
    }
}
