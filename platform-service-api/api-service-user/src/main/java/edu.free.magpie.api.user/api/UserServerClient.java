package edu.free.magpie.api.user.api;

import edu.free.magpie.api.user.dto.LoginUser;
import edu.free.magpie.api.user.dto.SysClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-server")
public interface UserServerClient {
    /**
     * feign rpc访问远程/users-anon/login接口
     * @param username
     * @return
     */
    @GetMapping(value = "/sysUser/login")
    LoginUser findByUsername(@RequestParam("username") String username);

    /**
     * @param cleintId
     * @return
     */
    @GetMapping(value = "/sysClient/loadClientByClientId")
    SysClientDto loadClientByClientId(@RequestParam("clientId") String cleintId);
}
