package com.hz.pet.bexchange.web;

import com.hz.pet.bexchange.domain.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Optional.ofNullable;

/**
 * @author Herman Zamula
 */
@Component
public class BexchangeUserDetailsService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new BexchangeUserDetails(ofNullable(userRepository.findByPhoneNumber(username))
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user: " + username)));
    }

    public static class BexchangeUserDetails extends User {

        private static final SimpleGrantedAuthority USER_ROLE = new SimpleGrantedAuthority("ROLE_USER");
        private static final SimpleGrantedAuthority USER_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

        public BexchangeUserDetails(com.hz.pet.bexchange.domain.user.User user) {
            super(user.getPhoneNumber(), user.getPassword(), user.isAdmin() ? of(USER_ROLE, USER_ADMIN) : of(USER_ROLE));
        }

    }
}
