package com.ssafy.travelog.auth.service;

import com.ssafy.travelog.auth.dao.AuthDao;
import com.ssafy.travelog.user.dto.UserDto;
import com.ssafy.travelog.util.jwt.TokenInfo;
import com.ssafy.travelog.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final AuthDao authDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public AuthServiceImpl(AuthDao authDao) {
//        this.authDao = authDao;
//    }

    @Override
    public int join(Map<String, String> map) throws SQLException {
        return authDao.join(map);
    }

    @Override
    public UserDto login(Map<String, String> map) throws SQLException {
        return authDao.login(map);
    }

    @Override
    public int checkId(Map<String, String> map) throws SQLException {
        return authDao.checkId(map);
    }

    @Override
    public int checkEmail(Map<String, String> map) throws SQLException {
        return authDao.checkEmail(map);
    }

    @Transactional
    @Override
    public TokenInfo getToken(Map<String, String> map) throws SQLException  {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        String memberId = map.get("userId");
        String password = map.get("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    public UserDetails createUserDetails(UserDto user) {

        user.getRoles().add("USER");

        return User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, RuntimeException {
        // 여기 수정하기

        Map<String,String> map = new HashMap<>();
        map.put("userId", username);

        UserDto ret= null;
        UserDetails ret2 = null;

        try {
            ret = authDao.loadUserByUsername(map);
            ret2 = createUserDetails(ret);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return ret2;
    }

}
