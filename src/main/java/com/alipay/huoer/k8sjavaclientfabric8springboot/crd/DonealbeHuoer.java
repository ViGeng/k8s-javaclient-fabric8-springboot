package com.alipay.huoer.k8sjavaclientfabric8springboot.crd;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 22:48
 */
public class DonealbeHuoer extends CustomResourceDoneable<Huoer> {
    public DonealbeHuoer(Huoer resource, Function<Huoer, Huoer> function) {
        super(resource, function);
    }
}
