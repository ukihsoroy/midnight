package io.alpha.support;

import java.io.OutputStream;

/**
 * A factory for creating MultiOutputStream objects.
 */
public interface IMultiOutputStream {

	/**
	 * Builds the output stream.
	 *
	 * @param params the params
	 * @return the output stream
	 */
	OutputStream buildOutputStream(Integer... params);

}
