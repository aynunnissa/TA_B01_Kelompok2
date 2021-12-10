package apap.TugasAkhir.siFactory.security;

import apap.TugasAkhir.siFactory.repository.PegawaiDb;
import apap.TugasAkhir.siFactory.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import apap.TugasAkhir.siFactory.model.PegawaiModel;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PegawaiDb pegawaiDb;

    @Autowired
    private PegawaiService pegawaiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PegawaiModel pegawai = pegawaiService.getPegawaiByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(pegawai.getRole().getRole()));
        return new User(pegawai.getUsername(), pegawai.getPassword(), grantedAuthorities);
    }
}