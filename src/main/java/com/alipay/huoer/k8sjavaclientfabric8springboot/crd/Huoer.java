package com.alipay.huoer.k8sjavaclientfabric8springboot.crd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionBuilder;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 20:45
 */
@Data
public class Huoer extends CustomResource {
    public static final String CRD_GROUP = "huoer.com";
    public static final String CRD_SINGULAR_NAME = "huoer";
    public static final String CRD_PLURAL_NAME = "huoers";
    public static final String CRD_NAME = CRD_PLURAL_NAME + "." + CRD_GROUP;
    public static final String CRD_KIND = "Huoer";
    public static final String CRD_SCOPE = "Namespaced";
    public static final String CRD_SHORT_NAME = "hr";
    public static final String CRD_VERSION = "v1beta1";
    public static final String CRD_API_VERSION = "apiextensions.k8s.io/" + CRD_VERSION;

    public static CustomResourceDefinition HUOER_CRD = new CustomResourceDefinitionBuilder()
            .withApiVersion(CRD_API_VERSION)
            .withNewMetadata()
            .withName(CRD_NAME)
            .endMetadata()

            .withNewSpec()
            .withGroup(CRD_GROUP)
            .withVersion(CRD_VERSION)
            .withScope(CRD_SCOPE)
            .withNewNames()
            .withKind(CRD_KIND)
            .withShortNames(CRD_SHORT_NAME)
            .withSingular(CRD_SINGULAR_NAME)
            .withPlural(CRD_PLURAL_NAME)
            .endNames()
            .endSpec()

            .withNewStatus()
            .withNewAcceptedNames()
            .addToShortNames(new String[]{"availableReplicas", "replicas", "updatedReplicas"})
            .endAcceptedNames()
            .endStatus()
            .build();

        @JsonProperty("spec")
        private HuoerSpec spec;
        @JsonProperty("status")
        private HuoerStatus status = new HuoerStatus();
}
