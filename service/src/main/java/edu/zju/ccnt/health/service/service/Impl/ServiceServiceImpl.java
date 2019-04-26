package edu.zju.ccnt.health.service.service.Impl;

import edu.zju.ccnt.health.service.service.IServiceService;
import edu.zju.ccnt.health.service.vo.BaseInfoVo;
import edu.zju.ccnt.health.service.vo.SearchInfoVo;
import edu.zju.ccnt.health.service.dao.ServiceMapper;
import edu.zju.ccnt.health.service.dto.BaseInfoDTO;
import edu.zju.ccnt.health.service.response.ServerResponse;
import edu.zju.ccnt.health.service.service.IServiceCategoryFeign;
import edu.zju.ccnt.health.service.utils.FtpUtils;
import edu.zju.ccnt.health.service.utils.ImageIdUtils;
import lombok.extern.slf4j.Slf4j;
import edu.zju.ccnt.health.service.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zth
 * 2019.1.2
 */
@org.springframework.stereotype.Service("iServiceService")
@Slf4j
public class ServiceServiceImpl implements IServiceService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private IServiceCategoryFeign iServiceCategoryFeign;

    /**
     *
     * @param type
     * @param categoryId
     * @param serviceName
     * @param checked
     * @param sort
     * @param page
     * @param limit
     * @return 根据查询条件获得的服务列表
     */
    public ServerResponse<SearchInfoVo> listService(Integer type, Integer categoryId, String serviceName, Integer checked, String sort, Integer page, Integer limit){
        logger.info("查询服务列表");
        List<Integer> categoryids;
        if(categoryId==-1)  categoryids = new ArrayList<>();
        else categoryids = iServiceCategoryFeign.getsubcategory(categoryId);
        List<BaseInfoDTO> baseInfoDTOList = serviceMapper.searchByCondition(type,categoryids,serviceName,checked,sort,page,limit);
        SearchInfoVo searchInfoVo = new SearchInfoVo();
        List<BaseInfoVo> baseInfoVoList = new ArrayList<>();
        for(BaseInfoDTO baseInfoDTO:baseInfoDTOList) {
            BaseInfoVo baseInfoVo = new BaseInfoVo();
            baseInfoVo.setId(baseInfoDTO.getId());
            baseInfoVo.setServiceName(baseInfoDTO.getService_name());
            if(baseInfoDTO.getCategory_id()!=null) {
//                logger.info("category_id是"+baseInfoDTO.getCategory_id());
                baseInfoVo.setServiceCategory(iServiceCategoryFeign.getname(baseInfoDTO.getCategory_id()));
            }else {
//                logger.info("category是null");
                baseInfoVo.setServiceCategory("");
            }
            baseInfoVo.setChecked(baseInfoDTO.getChecked());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            if(baseInfoDTO.getCreate_time()!=null)
            baseInfoVo.setCreateTime(sdf.format(baseInfoDTO.getCreate_time()));
            baseInfoVo.setOwner("ccnt实验室");
            baseInfoVoList.add(baseInfoVo);
        }
        searchInfoVo.setList(baseInfoVoList);
        searchInfoVo.setTotal(serviceMapper.searchtotalByCondition(type,categoryids,serviceName,checked));
        return ServerResponse.createBySuccess(searchInfoVo);
    }


    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basepath}")
    private String basepath;
    /**
     * @param uploadFile
     * @return 上传图片是否成功
     */
    public ServerResponse insertImg(MultipartFile uploadFile){
        String oldName = uploadFile.getOriginalFilename();
        logger.info("上传图片"+oldName);
        String imgName = ImageIdUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
        String filepath = new String("/serviceImages");
        String imgUrl = new String("http://192.168.12.110/images/serviceImages/")+imgName;
        try {
            InputStream input = uploadFile.getInputStream();
            boolean result = FtpUtils.uploadFile(host,port,username,password,basepath,filepath,imgName,input);
            if(result) {
                return ServerResponse.createBySuccess(imgUrl);
            }
            else return ServerResponse.createByErrorMessage("图片上传失败");
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByErrorMessage("图片上传失败");
        }
    }

    /**
     * 这个暂时废弃
     * @param type
     * @param userId
     * @param categoryId
     * @param serviceName
     * @param checked
     * @param serviceImg
     * @param price
     * @param introduction
     * @param detailintroduction
     * @param format
     * @return
     */
    public ServerResponse<Integer> createService(Integer type,Integer userId,Integer categoryId,String serviceName, Integer checked,String serviceImg,BigDecimal price,String introduction,String detailintroduction,String format){
        logger.info("创建服务");
        Service service = new Service();
        service.setIsOther(type);
        service.setUserId(userId);
        service.setCategoryId(categoryId);
        service.setServiceName(serviceName);
        service.setChecked(checked);
        service.setPrice(price);
        service.setServiceImg(serviceImg);
        service.setIntroduction(introduction);
        service.setDetailIntroduction(detailintroduction);
        service.setCreateTime(new Timestamp(System.currentTimeMillis()));
        service.setFormat(format);
        try{
            Integer servcieId = serviceMapper.insertSelective(service);
            return ServerResponse.createBySuccess(1);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("新建服务失败");
        }

    }

    public ServerResponse<Integer> updateChecked(Integer serviceId,Integer checked){
        logger.info("更改服务状态");
        try{
            serviceMapper.updateChecked(serviceId,checked);
            return ServerResponse.createBySuccess(1);
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    public ServerResponse<Integer> unckeck(Integer serviceId,String remarks){
        try{
            serviceMapper.uncheck(serviceId,4,remarks);
            return ServerResponse.createBySuccess(1);
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    public ServerResponse<Service> getInfo(Integer serviceId){
        logger.info(serviceId+"获取服务信息");
        try {
            Service service = serviceMapper.selectByPrimaryKey(serviceId);
            return ServerResponse.createBySuccess(service);
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    public ServerResponse updateService(Service service){
        logger.info("更新服务信息"+service.toString());
        try {
            int result = serviceMapper.updateByPrimaryKeySelective(service);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    public ServerResponse insertService(Service service){
        logger.info("新建服务");
        try {
            service.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int result = serviceMapper.insertSelective(service);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createByError();
        }
    }

    public ServerResponse<Map<String,Integer>> getUncheck(){
        log.info("获取未审核的服务数量");
        Map<String,Integer> result = new HashMap<>();
        Integer number = serviceMapper.getUnckeckNum(1);
        result.put("online",number);
        number = serviceMapper.getUnckeckNum(0);
        result.put("offline",number);
        return ServerResponse.createBySuccess(result);
    }
}
