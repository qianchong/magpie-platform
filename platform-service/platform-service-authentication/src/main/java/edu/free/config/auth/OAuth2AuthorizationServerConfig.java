package edu.free.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    //认证管理器
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 管理令牌（Managing Token）
     * ResourceServerTokenServices 接口定义了令牌加载、读取方法
     * AuthorizationServerTokenServices 接口定义了令牌的创建、获取、刷新方法
     * ConsumerTokenServices 定义了令牌的撤销方法
     * DefaultTokenServices 实现了上述三个接口,它包含了一些令牌业务的实现，如创建令牌、读取令牌、刷新令牌、获取客户端ID。
     * 默认的当尝试创建一个令牌时，是使用 UUID 随机值进行填充的，除了持久化令牌是委托一个 TokenStore 接口实现以外，
     * 这个类几乎帮你做了所有事情
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
/*      DB 配置
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore);

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 30天
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(tokenServices);*/

        endpoints.tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager);

    }

    /**
     * 配置客户端详情
     * ClientDetailsServiceConfigurer 能够使用内存或 JDBC 方式实现获取已注册的客户端详情，有几个重要的属性：
     * clientId：客户端标识 ID
     * secret：客户端安全码
     * scope：客户端访问范围，默认为空则拥有全部范围
     * authorizedGrantTypes：客户端使用的授权类型，默认为空
     * authorities：客户端可使用的权限
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("authorization_code")
                .scopes("app")
                .autoApprove(true);

    }

    /**
     * 配置授权服务器端点的安全
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

}
