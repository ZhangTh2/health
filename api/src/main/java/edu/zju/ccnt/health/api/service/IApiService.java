package edu.zju.ccnt.health.api.service;

import edu.zju.ccnt.health.api.model.Api;
import edu.zju.ccnt.health.api.response.ServerResponse;
import edu.zju.ccnt.health.api.vo.ApiBaseInfoVO;

import java.util.List;

/**
 * @author zth
 * @date 2019.4.24
 */
public interface IApiService {
    /**
     * @param serviceId
     * @return 服务所有的api的基本基本信息列表
     */
    ServerResponse<List<ApiBaseInfoVO> > listApi(Integer serviceId);
    /**
     * 更新api的状态
     * @param id
     * @param status
     * @return
     */
    ServerResponse updateStatus(Integer id,Integer status);
    /**
     * 新建api
     * @param api
     * @return
     */
    ServerResponse insertApi(Api api);
    /**
     * 更新Api
     * @param api
     * @return
     */
    ServerResponse updateApi(Api api);

    /**
     * api_id获取api的详细信息
     * @param id
     * @return
     */
    ServerResponse<edu.zju.ccnt.health.api.model.Api> getInfo(Integer id);

}
