package org.thekiddos.educationalportal.models;

import lombok.Data;
import org.thekiddos.educationalportal.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Data
public class User {
    @Id @NotNull @NotBlank @Column( unique = true ) @Size( min = 3)
    private String username;
    @NotNull @NotBlank
    private String password;
    @NotNull @NotBlank
    private String type;

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
}
