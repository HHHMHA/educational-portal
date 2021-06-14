package org.thekiddos.educationalportal.services;

import org.springframework.stereotype.Service;
import org.thekiddos.educationalportal.models.User;
import org.thekiddos.educationalportal.reposotories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private User authenticatedUser;
    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User login( String username, String password ) throws RuntimeException {
        var user = userRepository.findById( username ).orElseThrow( () -> new RuntimeException( "User not found" ) );

        if ( !user.checkPassword( password ) )
            throw new RuntimeException( "Wrong password" );

        authenticatedUser = user;
        return user;
    }

    @Override
    public User register( String username, String password, String type, boolean login ) {
        var user = userRepository.findById( username );

        if ( user.isPresent() )
            throw new RuntimeException( "User already exists" );

        User newUser = new User();
        newUser.setUsername( username );
        newUser.setPassword( password );
        newUser.setType( type );
        newUser = userRepository.save( newUser );

        if ( login )
            authenticatedUser = newUser;

        return newUser;
    }

    @Override
    public void logout() {
        authenticatedUser = null;
    }

    @Override
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
