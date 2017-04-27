package com.lte.admin.common.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class MethodAccessLogInterceptor implements MethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MethodAccessLogInterceptor.class);

	/**
	 * {@inheritDoc}
	 */
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		long startTime = System.currentTimeMillis();
		String invokeNo = MDC.get("invokeNo");
		String paramStr = "";
		if (invokeNo == null) {
			MDC.put("invokeNo", UUID.randomUUID().toString().replace("-", ""));
		}

		if (invocation.getArguments() != null && invocation.getArguments().length > 0) {
			for (int p = 0; p < invocation.getArguments().length; p++) {
				if (invocation.getArguments()[p] != null) {
					paramStr += "," + invocation.getArguments()[p].getClass().getName() + ":"
							+ JSON.toJSONString(invocation.getArguments()[p]);
				} else {
					paramStr += ",NULL";
				}
			}
		}
		logger.info(invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName()
				+ "-input：{}", StringUtils.isEmpty(paramStr) ? "NULL" : paramStr.substring(1));
		Method method = invocation.getMethod();
		Object target = invocation.getThis();
		Object result = null;
		String methodName = target.getClass().getSimpleName() + "." + method.getName();
		try {
			result = invocation.proceed();
		} catch (RuntimeException t) {
			logger.error("服务拦截器调用" + methodName + "异常！", t);
			throw t;
		} finally {
			long endTime = System.currentTimeMillis();
			logger.info(
					invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName()
							+ "-output：{}" + "|调用耗时" + (endTime - startTime) + "ms",
					result != null ? (result.getClass().getName() + ":" + JSON.toJSONString(result)) : "NULL");
		}
		return result;
	}
}
