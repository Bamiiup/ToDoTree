package aaandrey.todotree.service;

@SuppressWarnings("serial")
public class ServiceValidationException extends RuntimeException {
	public ServiceValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public static ServiceValidationException entityNotExist(Class<?> entityClass, Long id) {
		return new ServiceValidationException(
				String.format("%s doesn't exist with this id: %s", entityClass.getSimpleName(), id));
	}

	public static ServiceValidationException fieldIsNullOrEmpty(Class<?> entityClass, String fieldName, Long id) {
		return new ServiceValidationException(
				String.format("%s with id: %s has empty or null field named '%s'", entityClass.getSimpleName(), id, fieldName));
	}

	public static ServiceValidationException endBeforeStart(Class<?> entityClass, String startFieldName,
			String endFieldName, Long id) {
		return new ServiceValidationException(
				String.format("%s with id: %s has end field named '%s' before start field named '%s'",
						entityClass.getSimpleName(), id, endFieldName, startFieldName));
	}

	public static ServiceValidationException numberConstraintViolation(Class<?> entityClass, String fieldName,
			int leftConstraint, Long id) {
		return new ServiceValidationException(
				String.format("%s with id: %s violate left constraint with number %d for filed named '%s'",
						entityClass.getSimpleName(), id, leftConstraint, fieldName));
	}

	public static ServiceValidationException invalidFieldValue(Class<?> entityClass, String fieldName, Object fieldValue,
			Long id) {
		return new ServiceValidationException(String.format("%s width id: %s has invalid '%s' field value '%s'",
				entityClass.getSimpleName(), id, fieldName, fieldValue));
	}
}
