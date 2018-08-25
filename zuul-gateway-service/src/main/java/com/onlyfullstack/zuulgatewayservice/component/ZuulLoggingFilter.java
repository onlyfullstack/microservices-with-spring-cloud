package com.onlyfullstack.zuulgatewayservice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
	@Override
	public Object run() throws ZuulException {

		logger.info("*************##############******** printing logs : ");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre"; // 3 types re vailable - pre - to execute before processing the request, post- after processing the req and error
	}
}
