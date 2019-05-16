package edu.zju.ccnt.health.api.service.Impl;

import edu.zju.ccnt.health.api.dao.ApiBaseInfo;
import edu.zju.ccnt.health.api.dao.ApiMapper;
import edu.zju.ccnt.health.api.model.Api;
import edu.zju.ccnt.health.api.response.ServerResponse;
import edu.zju.ccnt.health.api.service.IApiService;
import edu.zju.ccnt.health.api.vo.ApiBaseInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zth
 * @date 2019.4.14
 */
@Service("iApiService")
public class ApiServiceServiceImpl implements IApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceServiceImpl.class);

    @Autowired
    private ApiMapper apiMapper;

    public ServerResponse<List<ApiBaseInfoVO>> listApi(Integer serviceId) {
        logger.info(serviceId + "查询所有的api");
        List<ApiBaseInfo> apiBaseInfos = apiMapper.selectApiByServiceId(serviceId);
        List<ApiBaseInfoVO> apiBaseInfoVOS = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        for (ApiBaseInfo apiBaseInfo : apiBaseInfos) {
            ApiBaseInfoVO apiBaseInfoVO = new ApiBaseInfoVO();
            apiBaseInfoVO.setId(apiBaseInfo.getId());
            apiBaseInfoVO.setName(apiBaseInfo.getName());
            apiBaseInfoVO.setStatus(apiBaseInfo.getStatus());
            if (apiBaseInfo.getCreate_time() != null)
                apiBaseInfoVO.setCreateTime(sdf.format(apiBaseInfo.getCreate_time()));
            if (apiBaseInfo.getUpdate_time() != null)
                apiBaseInfoVO.setUpdateTime(sdf.format(apiBaseInfo.getUpdate_time()));
            apiBaseInfoVOS.add(apiBaseInfoVO);
        }
        return ServerResponse.createBySuccess(apiBaseInfoVOS);
    }

    /**
     * 更新api的状态
     *
     * @param id
     * @param status
     * @return
     */
    public ServerResponse updateStatus(Integer id, Integer status) {
        logger.info(id + "更新状态为" + status);
        try {
            apiMapper.updateStatus(id, status);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            logger.info(e.getStackTrace().toString());
            return ServerResponse.createByError();
        }
    }

    /**
     * 新建api
     *
     * @param api
     * @return
     */
    public ServerResponse insertApi(Api api) {
        logger.info("新建api");
        try {
            api.setCreateTime(new Timestamp(System.currentTimeMillis()));
            apiMapper.insertSelective(api);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    /**
     * 更新Api
     *
     * @param api
     * @return
     */
    public ServerResponse updateApi(Api api) {
        logger.info("更新Api");
        try {
            api.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            apiMapper.updateByPrimaryKeySelective(api);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    /**
     * api_id获取api的详细信息
     *
     * @param id
     * @return
     */
    public ServerResponse<edu.zju.ccnt.health.api.model.Api> getInfo(Integer id) {
        logger.info("获取id为" + id + "的Api的详细信息");
        try {
            Api api = apiMapper.selectByPrimaryKey(id);
            return ServerResponse.createBySuccess(api);
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }
}
