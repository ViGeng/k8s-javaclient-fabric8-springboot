package com.alipay.huoer.k8sjavaclientfabric8springboot;

import com.alipay.huoer.k8sjavaclientfabric8springboot.controller.HuoerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class K8sJavaclientFabric8SpringbootApplication implements CommandLineRunner {

	@Autowired
	private HuoerController controller;

	public static void main(String[] args) {
		SpringApplication.run(K8sJavaclientFabric8SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		controller.run();
	}
}
