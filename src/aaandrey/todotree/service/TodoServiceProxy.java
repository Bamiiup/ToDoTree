package aaandrey.todotree.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import aaandrey.todotree.service.annotation.Transactional;
import aaandrey.todotree.service.utils.TransactionUtils;

@Component("todoServiceProxy")
public class TodoServiceProxy {
	@Resource(name = "transactionUtils")
	private TransactionUtils transactionUtils;

	public ITodoService createTransactionalProxy(ITodoService todoService) {

		return (ITodoService) Proxy.newProxyInstance(ITodoService.class.getClassLoader(),
				new java.lang.Class<?>[] { ITodoService.class }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (needProxy(method)) {
							return proxyMethod(proxy, method, args);
						} else {
							return method.invoke(todoService, args);
						}
					}

					private boolean needProxy(Method method) {
						try {

							Method todoServiceMethod = todoService.getClass().getMethod(method.getName(),
									method.getParameterTypes());

							return Stream.of(todoServiceMethod.getAnnotations()).filter(annotation -> {
								return annotation.annotationType().equals(Transactional.class);
							}).findAny().isPresent();

						} catch (NoSuchMethodException | SecurityException e) {
							throw new RuntimeException(e);
						}

					}

					private Object proxyMethod(Object proxy, Method method, Object[] args) {
						return transactionUtils.performInsideTransaction(entityManager -> {

							try {
								Field entityManagerField = todoService.getClass().getDeclaredField("entityManager");
								entityManagerField.setAccessible(true);
								entityManagerField.set(todoService, entityManager);

								Object result = method.invoke(todoService, args);

								entityManagerField.set(todoService, null);
								entityManagerField.setAccessible(false);

								return result;
							} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
									| NoSuchFieldException | SecurityException e) {
								throw new RuntimeException(e);
							}

						});
					}
				});
	}
}
