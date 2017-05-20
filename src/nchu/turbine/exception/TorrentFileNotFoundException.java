package nchu.turbine.exception;

public class TorrentFileNotFoundException extends TurbineException{

	public TorrentFileNotFoundException() {
		super();
	}

	public TorrentFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TorrentFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TorrentFileNotFoundException(String message) {
		super(message);
	}

	public TorrentFileNotFoundException(Throwable cause) {
		super(cause);
	}

}
