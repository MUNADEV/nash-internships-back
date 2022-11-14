package com.nash.nashinternshipsback.service.autentificacion;

import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.security.SecurityUser;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceAuth implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    /**
     *
     * @param rutOCorreo the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String rutOCorreo) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerPorRutOCorreo(rutOCorreo).get();
        /*List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(ROLE_PREFIX+user.getProfile().getName()));
        user.getProfile().getProfileMenus().stream().forEach((x) -> {
            auths.add(new SimpleGrantedAuthority(x.getMenus().getUrl()));
        });*/
        return SecurityUser.build(usuario);
    }
}
