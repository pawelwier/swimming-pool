package pl.akademiakodu.swimmingpool.commons.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {

    private UserAppRepository userAppRepository;

    public CustomUserService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<UserApp> user = userAppRepository.findUserAppByName(name);
        user.orElseThrow( () -> new UsernameNotFoundException("user not found") );

        return user.map(CustomUserDetails::new).get();
    }
}
