package db;

public class DbIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DbIntegrityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DbIntegrityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DbIntegrityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
