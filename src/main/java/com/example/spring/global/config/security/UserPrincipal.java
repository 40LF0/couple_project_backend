package com.example.spring.global.config.security;

import com.example.spring.domain.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {

    private final Member member;

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;
    private Map<String, Object> attributes;

    public UserPrincipal(Member member, Long id, String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.member = member;
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrincipal createUser(final Member member) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole().getValue()));
        return new UserPrincipal(
                member,
                member.getMemberId(),
                member.getEmail(),
                member.getPassword(),
                authorities
        );
    }
    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.createUser(member);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}