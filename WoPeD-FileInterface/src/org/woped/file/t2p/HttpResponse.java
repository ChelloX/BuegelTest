package org.woped.file.t2p;

public class HttpResponse {
	int responseCode;
	String body;

	public HttpResponse(int code, String body) {
		this.responseCode = code;
		this.body = body;
	}

	public String getBody () {
		return body;
	}
}
