package com.alipay.huoer.k8sjavaclientfabric8springboot.crd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 22:27
 */
@Data
public class HuoerSpec implements KubernetesResource {
    /** Total number of available pods (ready for at least minReadySeconds) targeted by this deployment. */
    @JsonProperty("availableReplicas")
    private Integer availableReplicas;
    /** Total number of non-terminated pods targeted by this deployment that have the desired template spec. */
    @JsonProperty("updatedReplicas")
    private Integer updatedReplicas;
    /** Total number of non-terminated pods targeted by this deployment (their labels match the selector). */
    @JsonProperty("replicas")
    private Integer replicas;
}
