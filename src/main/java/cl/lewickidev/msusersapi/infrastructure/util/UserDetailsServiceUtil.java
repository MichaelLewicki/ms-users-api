package cl.lewickidev.msusersapi.infrastructure.util;

import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceUtil implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
    }

}
