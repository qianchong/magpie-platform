package edu.free.magpie.common.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/8/20 9:35
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/20
 * Version: 0.0.1
 * Modified By:
 */
public class SnebRegisterApiVO {
    private Map<String, List<AuthApiInfo>> authApiInfoLMap = new HashMap<>();

    public Map<String, List<AuthApiInfo>> getAuthApiInfoLMap() {
        return authApiInfoLMap;
    }

    public void addApi(String controller, String methodType, String apiName, String apiUrl) {
        List<AuthApiInfo> authApiInfoList = authApiInfoLMap.get(controller);
        if (authApiInfoList == null) {
            authApiInfoList = new ArrayList<>();
        }
        authApiInfoList.add(new AuthApiInfo(methodType, apiName, apiUrl));
        authApiInfoLMap.put(controller, authApiInfoList);
    }

    public class AuthApiInfo {
        //get and post ..
        private String methodType;

        private String apiName;

        private String apiUrl;

        public AuthApiInfo(String methodType, String apiName, String apiUrl) {
            this.methodType = methodType;
            this.apiName = apiName;
            this.apiUrl = apiUrl;
        }

        public String getMethodType() {
            return methodType;
        }

        public void setMethodType(String methodType) {
            this.methodType = methodType;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }
    }
}
