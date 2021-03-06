/*
MIT License (MIT)
Copyright (c) 2011 François LAROCHE

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cz.metacentrum.perun.wui.widgets.recaptcha;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This file was originally taken from https://github.com/pandurangpatil/gwt-recaptcha
 *
 * @author Pandurang Patil
 *
 */
public class ResponseEvent extends GwtEvent<ResponseHandler> {

	private String response;
	
	
	/**
	 * @param response
	 */
	public ResponseEvent(String response) {
		super();
		this.response = response;
	}

	/**
	 * @return the response
	 */
	public final String getResponse() {
		return response;
	}

	public static Type<ResponseHandler>	TYPE	= new Type<ResponseHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ResponseHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ResponseHandler handler) {
		handler.onResponse(this);
	}

}
