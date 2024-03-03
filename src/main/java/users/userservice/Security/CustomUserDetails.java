package users.userservice.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import users.userservice.Models.Role;
import users.userservice.Models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(as = CustomUserDetails.class)
public class CustomUserDetails implements UserDetails {
    private User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<CustomGrantedAuthority> customGrantedAuthorityList = new ArrayList<>();
        for(Role role : user.getRoleSet()) {
            customGrantedAuthorityList.add(new CustomGrantedAuthority(role));
        }
        return customGrantedAuthorityList;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
