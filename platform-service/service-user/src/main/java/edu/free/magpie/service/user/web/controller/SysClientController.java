package edu.free.magpie.service.user.web.controller;

import edu.free.magpie.api.user.dto.SysClientDto;
import edu.free.magpie.service.user.web.service.SysClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sysClient")
public class SysClientController {

    @Autowired
    private SysClientService sysClientService;

    /**
     * @param clientId
     * @return
     */
    @GetMapping(value = "/loadClientByClientId")
    @ResponseBody
    public SysClientDto loadClientByClientId(@RequestParam("clientId") String clientId) {
        return sysClientService.loadClientByClientId(clientId);
    }
}
