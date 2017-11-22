package aaandrey.todotree.service;

@SuppressWarnings("serial")
public class ServiceAuthorizationException extends RuntimeException {
	public enum ServiceOperation {
		create, get, update, remove
	}

	public ServiceAuthorizationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceAuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceAuthorizationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceAuthorizationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceAuthorizationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public static ServiceAuthorizationException entityAccessDenied(Class<?> entityClass, Long id,
			ServiceOperation serviceOperation) {
		return new ServiceAuthorizationException(
				String.format("Access for %s with id: %s to %s is denied", entityClass.getSimpleName(), id, serviceOperation));
	}
}
