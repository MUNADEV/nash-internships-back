package com.nash.nashinternshipsback.security;

import com.nash.nashinternshipsback.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SecurityUser implements UserDetails {
    private String nombre;
    private String rut;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(String nombre, String rut, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static SecurityUser build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRol())).collect(Collectors.toList());
        return new SecurityUser(usuario.getNombre(), usuario.getRut(), usuario.getCorreo(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return rut;
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