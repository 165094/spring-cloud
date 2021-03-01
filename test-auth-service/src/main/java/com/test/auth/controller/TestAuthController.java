package com.test.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.auth.model.UserDto;
import com.test.auth.vo.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
@Slf4j
public class TestAuthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @Value("${login.clientId}")
    private String clientId;

    @Value("${login.clientSecret}")
    private String clientSecret;

    @RequestMapping(value = "/order/user")
    public String getUserDto(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        UserDto userDto = new UserDto();
        userDto.setUserName("admin");
        userDto.setPassWord("123456");
        userDto.setPhone("19201928931");
        userDto.setSex("男");
        return JSONObject.toJSONString(userDto);
    }

    @GetMapping(value = "/test")
    public String getTest(){
        UserDto userDto = new UserDto();
        userDto.setUserName("张三");
        userDto.setSex("男");
        return JSONObject.toJSONString(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Map<String, String> parameters = new HashMap<>(3);
        // 设置授权类型为密码模式
        parameters.put("grant_type", "password");
        parameters.put("username", request.getUsername());
        parameters.put("password", request.getPassword());
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 此处不能为空
        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                clientId, clientSecret, grantedAuthorities);
        try {
            ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication, parameters);
            OAuth2AccessToken auth2AccessToken = responseEntity.getBody();
            String key = "access_token:"+auth2AccessToken.getValue();
            redisTemplate.opsForValue().set(key, auth2AccessToken.getValue(), auth2AccessToken.getExpiresIn(), TimeUnit.SECONDS);
            return JSONObject.toJSONString(responseEntity.getBody());
        } catch (NoSuchClientException e) {
            log.error("应用不存在", e);
            return "应用不存在";
        } catch (BadCredentialsException e) {
            return "应用密钥错误";
        } catch (InvalidGrantException e) {
            return "用户名密码不正确";
        } catch (Exception e) {
            return "登录失败" + e.getMessage();
        }
    }

    /**
     * 退出登录
     * @param accessToken
     * @return
     */
    @GetMapping("/logout/{accessToken}")
    public String exit(HttpServletRequest request,
                       @NotBlank(message = "token不能为空！") @PathVariable String accessToken) {
        String token = request.getParameter("access_token");
        boolean success = consumerTokenServices.revokeToken(accessToken);
        if (success){
            return "注销成功！";
        }
        return "注销失败";
    }
}
