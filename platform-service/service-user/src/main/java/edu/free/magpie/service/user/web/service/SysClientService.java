package edu.free.magpie.service.user.web.service;


import edu.free.magpie.api.user.dto.SysClientDto;
import edu.free.magpie.service.user.web.dao.SysClientDao;
import edu.free.magpie.service.user.web.entity.SysClientEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysClientService {
    @Autowired
    private SysClientDao sysClientDao;

    public SysClientDto loadClientByClientId(String clientId) {
        SysClientEntity sysClientEntity = sysClientDao.findByClientId(clientId);
        SysClientDto sysClientDto = new SysClientDto();
        BeanUtils.copyProperties(sysClientEntity, sysClientDto);
        return sysClientDto;
    }
}
