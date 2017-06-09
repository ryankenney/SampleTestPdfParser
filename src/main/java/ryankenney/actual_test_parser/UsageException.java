package ryankenney.actual_test_parser;

@SuppressWarnings("serial")
public class UsageException extends RuntimeException {

	public UsageException() {
		super();
	}

	public UsageException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsageException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsageException(Throwable cause) {
		super(cause);
	}

	public UsageException(String string) {
	}

}
