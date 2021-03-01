package com.test.auth.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;


public class BaseClientDetailService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //这里可以改为查询数据库
        BaseClientDetails client = new BaseClientDetails();
        client.setClientId(clientId);
        client.setClientSecret(new BCryptPasswordEncoder().encode("123456"));
        //client.setResourceIds(Arrays.asList("order"));  //资源id
        client.setAuthorizedGrantTypes(Arrays.asList("authorization_code",
        "client_credentials", "refresh_token", "password", "implicit"));
        //不同的client可以通过 一个scope 对应 权限集
        client.setScope(Arrays.asList("all", "select"));
        client.setAuthorities(AuthorityUtils.createAuthorityList("admin_role"));
        client.setAccessTokenValiditySeconds(60 * 60); //1分钟
        client.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7); //1分钟
        if(client == null) {
            throw new NoSuchClientException("No client width requested id: " + clientId);
        }
        return client;
    }
}
