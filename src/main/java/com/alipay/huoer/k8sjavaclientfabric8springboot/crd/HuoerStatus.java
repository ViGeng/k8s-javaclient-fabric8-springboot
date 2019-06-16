package com.alipay.huoer.k8sjavaclientfabric8springboot.crd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import lombok.Data;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 22:27
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class HuoerStatus {
    /** The number of desired pods */
    @JsonProperty("replicas")
    private Integer replicas;
    @JsonProperty("template")
    private PodTemplateSpec podTemplateSpec;
    @JsonProperty("selector")
    private LabelSelector labelSelector;
}
