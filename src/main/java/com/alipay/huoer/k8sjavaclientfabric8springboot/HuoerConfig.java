package com.alipay.huoer.k8sjavaclientfabric8springboot;

import com.alipay.huoer.k8sjavaclientfabric8springboot.controller.HuoerController;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 20:24
 */
@Configuration
public class HuoerConfig {

    private String MASTER_URL = "http://localhost:12000";

    @Bean
    public KubernetesClient kubernetesClient() {
        Config config = new ConfigBuilder().withMasterUrl(MASTER_URL).build();
        return new DefaultKubernetesClient(config);
    }

    @Bean
    public HuoerController huoerController(KubernetesClient client) {
        return new HuoerController(client);
    }


}
