package com.alipay.huoer.k8sjavaclientfabric8springboot.controller;

import com.alipay.huoer.k8sjavaclientfabric8springboot.crd.DonealbeHuoer;
import com.alipay.huoer.k8sjavaclientfabric8springboot.crd.Huoer;
import com.alipay.huoer.k8sjavaclientfabric8springboot.crd.HuoerList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;

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


    static {
        KubernetesDeserializer.registerCustomKind(Huoer.CRD_GROUP + "/" + Huoer.CRD_VERSION, Huoer.CRD_KIND, Huoer.class);
    }

    public void run() {
        CustomResourceDefinition huoerCRD = createCRD();
//        watchPods();
        watchDeployment();
        watchHuoer(huoerCRD);
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

    public void watchHuoer(CustomResourceDefinition HuoerCRD) {
        MixedOperation<Huoer, HuoerList, DonealbeHuoer, Resource<Huoer, DonealbeHuoer>> huoerClient = client.customResources(HuoerCRD, Huoer.class, HuoerList.class, DonealbeHuoer.class);
//        work
        client.customResourceDefinitions().watch(new Watcher<CustomResourceDefinition>() {
            @Override
            public void eventReceived(Action action, CustomResourceDefinition resource) {
                logger.info("CRD event happened");
                logger.info(action + resource.toString());
            }

            @Override
            public void onClose(KubernetesClientException cause) {

            }
        });
//        do not work
//        huoerClient.watch(new Watcher<Huoer>() {
//            @Override
//            public void eventReceived(Action action, Huoer resource) {
//                logger.info("Huoer event happened");
//                logger.info(action + resource.toString());
//            }
//
//            @Override
//            public void onClose(KubernetesClientException cause) {
//
//            }
//        });

    }
}
