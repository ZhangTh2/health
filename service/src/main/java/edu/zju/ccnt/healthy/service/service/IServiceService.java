package edu.zju.ccnt.healthy.service.service;

import edu.zju.ccnt.healthy.service.model.Service;
import edu.zju.ccnt.healthy.service.response.ServerResponse;
import edu.zju.ccnt.healthy.service.vo.SearchInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zth
 * @Date 2019.4.10
 *
 *
 * @checked：
 *  0：未审核，其他已审核
 *  1: 未上架
 *  2: 正在出售
 *  3: 已下架，可重新上架
 *  4: 审核未通过
 *
 * @is_other:
 *  0:线下服务
 *  1：线上服务
 *
 * @userId:
 *  0:ccnt实验室
 *  其它：对应的服务所有用户id
 */
public interface IServiceService {

    /**
     *
     * @param type
     * @param categoryId
     * @param serviceName
     * @param checked
     * @param sort
     * @param page
     * @param limit
     * @return根据查询条件返回服务简略信息列表
     */
    ServerResponse<SearchInfoVo> listService(Integer type, Integer categoryId, String serviceName, Integer checked, String sort, Integer page, Integer limit);

    /**
     * 将图片上传到ftp服务器
     * @param uploadFile
     * @return
     */
    ServerResponse insertImg( MultipartFile uploadFile);

    /**
     *
     * @param type
     * @param categoryId
     * @param serviceName
     * @param checked
     * @param price
     * @param introduction
     * @param detailintroduction
     * @return 新建服务是否成功
     */
    ServerResponse<Integer> createService(Integer type, Integer userId, Integer categoryId, String serviceName, Integer checked, String serviceImg, BigDecimal price, String introduction, String detailintroduction,String format);

    /**
     * 除了审核不通过之外的checked信息更新
     * @param serviceId
     * @param checked
     * @return
     */
    ServerResponse<Integer> updateChecked(Integer serviceId,Integer checked);

    /**
     * 对于审核不通过的服务要添加备注：因何不通过
     * @param serviceId
     * @param remarks
     * @return
     */
    ServerResponse<Integer> unckeck(Integer serviceId,String remarks);

    /**
     * 由id获取服务的详情信息
     * @param serviceId
     * @return
     */
    ServerResponse<Service> getInfo(Integer serviceId);

    /**
     * 更新服务信息
     * @param service
     * @return
     */
    ServerResponse updateService(Service service);

    /**
     * 新建服务-v2 现在用这个接口
     * @param service
     * @return
     */
    ServerResponse insertService(Service service);

    /**
     * 获取未审核的服务的数量
     * @return online:num offline:num
     */
    ServerResponse<Map<String,Integer> > getUncheck();
}
