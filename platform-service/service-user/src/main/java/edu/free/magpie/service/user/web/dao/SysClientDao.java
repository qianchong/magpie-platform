package edu.free.magpie.service.user.web.dao;


import edu.free.magpie.service.user.web.entity.SysClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysClientDao extends JpaRepository<SysClientEntity, String>, JpaSpecificationExecutor<SysClientEntity> {
    SysClientEntity findByClientId(String clientId);
}
