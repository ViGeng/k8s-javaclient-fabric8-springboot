package com.alipay.huoer.k8sjavaclientfabric8springboot.controller;

import com.alipay.huoer.k8sjavaclientfabric8springboot.crd.Huoer;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import java.util.logging.Logger;

/**
 * @author: weigeng.gw
 * @version: 2019/6/16 20:23
 */
public class HuoerController  {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private KubernetesClient client;

    public HuoerController(KubernetesClient client) {
        this.client = client;
    }

    public void run() {
        createCRD();
        watchPods();
        watchDeployment();
    }

    public CustomResourceDefinition createCRD() {
        // list crds named Huoer.CRD_NAME from client
        CustomResourceDefinition huoerCrd = client.customResourceDefinitions().withName(Huoer.CRD_NAME).get();
        if (huoerCrd != null) {
            logger.info("Found CRD: " + huoerCrd.getMetadata().getSelfLink());
        } else {
            huoerCrd = Huoer.HUOER_CRD;
            client.customResourceDefinitions().create(huoerCrd);
            logger.info("HuoerCRD created");
        }
        return huoerCrd;
    }

    public void watchPods() {
        client.pods().watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod resource) {
                logger.info(action + ":" + resource);
            }

            @Override
            public void onClose(KubernetesClientException cause) {
                logger.info(cause.getMessage());
            }
        });
    }

    public void watchDeployment() {
        Deployment dp = new Deployment();
        client.apps().deployments().watch(new Watcher<Deployment>() {
            @Override
            public void eventReceived(Action action, Deployment resource) {
                logger.info(action + ":" + resource);
            }

            @Override
            public void onClose(KubernetesClientException cause) {

            }
        });
    }
}
