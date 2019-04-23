package edu.zju.ccnt.healthservicecategory.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceVo1 extends ServiceVo{
    private List<ServiceVo> children = new ArrayList<>();
}
