package com.github.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
@ComponentScan(
		{
				"com.xxl.job.admin.core.conf",
				"com.xxl.job.admin.service",
				"com.xxl.job.admin.dao",
				"com.xxl.job.admin.controller",
				"com.github.xxl.job.admin.config"
		})
public class XxlJobAdminApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlJobAdminApplication.class, args);
	}

}