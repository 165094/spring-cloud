package com.test.auth.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer extends JwtAccessTokenConverter implements Serializable {

    private static final String TOKEN_SEG_USER_ID = "X-Tools-UserId";
    private static final String TOKEN_SEG_CLIENT = "X-Tools-ClientId";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        SysUserAuthentication userDetails = (SysUserAuthentication) authentication.getPrincipal();
//        authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put(TOKEN_SEG_USER_ID, userDetails.getUserId());
        info.put(TOKEN_SEG_CLIENT, userDetails.getClientId());
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
//                enhancedToken.getAdditionalInformation().put(TOKEN_SEG_CLIENT, userDetails.getClientId());
        return super.enhance(customAccessToken, authentication);
    }

//    public static void main(String[] args) {
//        String encode = new BCryptPasswordEncoder().encode("123456");
//        System.out.println(encode);
//    }
}
