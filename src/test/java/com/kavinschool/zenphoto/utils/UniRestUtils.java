/*
 * Author: Kangeyan Passoubady
 * 1.0
 */
package com.kavinschool.zenphoto.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The Class UniRestUtils.
 */
public final class UniRestUtils {

	/** The Constant APP_TYPE_JSON. */
	public static final String APP_TYPE_JSON = "application/json";

	/** The Constant ENCODING_TYPE. */
	public static final String ENCODING_TYPE = "UTF-8";

	/** The Constant CONTENT_TYPE. */
	public static final String CONTENT_TYPE = "Content-Type";

	/** The Constant ACCEPT. */
	public static final String ACCEPT = "accept";

	/**
	 * The Enum REST_OPS.
	 */
	public static enum REST_OPS {

		/** The get. */
		GET,
		/** The delete. */
		DELETE,
		/** The post. */
		POST,
		/** The put. */
		PUT,
		/** The patch. */
		PATCH
	};

	/**
	 * The Enum REST_AUTH.
	 */
	public static enum REST_AUTH {

		/** The api. */
		API,
		/** The basic. */
		BASIC
	};

	/**
	 * Instantiates a new uni rest utils.
	 */
	private UniRestUtils() {
	}

	/**
	 * Gets the api using query string.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param queryString
	 *            the query string
	 * @param apiKey
	 *            the api key
	 * @param apiValue
	 *            the api value
	 * @return the api using query string
	 */
	public static String getApiUsingQueryString(final String restUri, final Map<String, String> queryString,
			final String apiKey, final String apiValue) {
		return UniRestUtils.getAuth(restUri, queryString, apiKey, apiValue, REST_AUTH.API);
	}

	/**
	 * Gets the auth.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param queryString
	 *            the query string
	 * @param authKey
	 *            the auth key
	 * @param authValue
	 *            the auth value
	 * @param authType
	 *            the auth type
	 * @return the auth
	 */
	public static String getAuth(final String restUri, final Map<String, String> queryString, final String authKey,
			final String authValue, final REST_AUTH authType) {
		UniRestUtils.writeLog("getAuth Started..");
		Unirest.setHttpClient(UniRestUtils.getHttpClient());

		HttpResponse<JsonNode> response = null;
		GetRequest request = null;
		try {
			request = Unirest.get(restUri).header(UniRestUtils.ACCEPT, UniRestUtils.APP_TYPE_JSON)
					.header(UniRestUtils.CONTENT_TYPE, UniRestUtils.APP_TYPE_JSON);

			switch (authType) {
			case BASIC:
				request.basicAuth(authKey, authValue);
				break;
			case API:
				request.header(authKey, authValue);
				break;
			default:
				break;
			}

			if (queryString != null) {
				final Set<Entry<String, String>> entrySet = queryString.entrySet();

				for (final Entry<String, String> entry : entrySet) {
					request.queryString(entry.getKey(), entry.getValue());
				}
			}

			response = request.asJson();

		} catch (final UnirestException e) {
			UniRestUtils.writeLog(e.getMessage());

			return null;
		}
		final JsonNode resBody = response.getBody();
		UniRestUtils.writeLog(resBody.toString());
		try {
			Unirest.shutdown();
		} catch (final IOException e) {
			UniRestUtils.writeLog(e.getMessage());

		}
		UniRestUtils.writeLog("getAuth Ended..");
		return resBody.toString();
	}

	/**
	 * Gets the basic using query string.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param queryString
	 *            the query string
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the basic using query string
	 */
	public static String getBasicUsingQueryString(final String restUri, final Map<String, String> queryString,
			final String userName, final String password) {
		return UniRestUtils.getAuth(restUri, queryString, userName, password, REST_AUTH.BASIC);
	}

	/**
	 * Gets the http client.
	 *
	 * @return the http client
	 */
	public static CloseableHttpClient getHttpClient() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			UniRestUtils.writeLog(e.getMessage());
		}

		final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);

		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	/**
	 * Gets the using api key.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param apiKey
	 *            the api key
	 * @param apiValue
	 *            the api value
	 * @return the using api key
	 */
	public static String getUsingApiKey(final String restUri, final String apiKey, final String apiValue) {
		return UniRestUtils.getApiUsingQueryString(restUri, null, apiKey, apiValue);
	}

	/**
	 * Gets the using basic auth.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the using basic auth
	 */
	public static String getUsingBasicAuth(final String restUri, final String userName, final String password) {
		return UniRestUtils.getBasicUsingQueryString(restUri, null, userName, password);
	}

	/**
	 * Patch data text.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchDataText(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		UniRestUtils.writeLog("patchDataText Started..");
		HttpResponse<JsonNode> request = null;

		try {
			request = Unirest.patch(restUri).basicAuth(userName, password)
					.header(UniRestUtils.ACCEPT, UniRestUtils.APP_TYPE_JSON)
					.header(UniRestUtils.CONTENT_TYPE, UniRestUtils.APP_TYPE_JSON).body(jsonInputText).asJson();
		} catch (final UnirestException e) {
			UniRestUtils.writeLog(e.getMessage());
			return null;
		}

		final JsonNode resBody = request.getBody();
		UniRestUtils.writeLog("resBody:" + resBody.toString());
		try {
			Unirest.shutdown();
		} catch (final IOException e) {
			UniRestUtils.writeLog(e.getMessage());

		}
		UniRestUtils.writeLog("patchJsonDataUsingFile Ended..");
		return resBody.toString();
	}

	/**
	 * Patch json data using text.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchJsonDataUsingText(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		return UniRestUtils.patchDataText(restUri, jsonInputText, userName, password);
	}

	/**
	 * Patch json text using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchJsonTextUsingBasic(final String restUri, final String jsonInputText,
			final String userName, final String password) {

		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.BASIC, REST_OPS.PATCH);
	}

	/**
	 * Patch json text using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchJsonTextUsingApi(final String restUri, final String jsonInputText, final String userName,
			final String password) {

		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.API, REST_OPS.PATCH);
	}

	/**
	 * Patch json file using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchJsonFileUsingApi(final String restUri, final FileInputStream jsonFileInputStream,
			final String userName, final String password) {

		return UniRestUtils.opAuth(restUri, UniRestUtils.readFile(jsonFileInputStream), userName, password,
				REST_AUTH.API, REST_OPS.PATCH);
	}

	/**
	 * Patch json file using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String patchJsonFileUsingBasic(final String restUri, final FileInputStream jsonFileInputStream,
			final String userName, final String password) {

		return UniRestUtils.opAuth(restUri, UniRestUtils.readFile(jsonFileInputStream), userName, password,
				REST_AUTH.BASIC, REST_OPS.PATCH);
	}

	/**
	 * Post json data using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param apiKey
	 *            the api key
	 * @param apiValue
	 *            the api value
	 * @return the string
	 */
	public static String postJsonDataUsingApi(final String restUri, final String jsonInputText, final String apiKey,
			final String apiValue) {
		return UniRestUtils.opAuth(restUri, jsonInputText, apiKey, apiValue, REST_AUTH.API, REST_OPS.POST);
	}

	/**
	 * Post json data using file.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String postJsonDataUsingFile(final String restUri, final FileInputStream jsonFileInputStream,
			final String userName, final String password) {
		return UniRestUtils.postUsingBasic(restUri, UniRestUtils.readFile(jsonFileInputStream), userName, password);
	}

	/**
	 * Post json data using text.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String postJsonDataUsingText(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.BASIC, REST_OPS.POST);
	}

	/**
	 * Post using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String postUsingBasic(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.BASIC, REST_OPS.POST);
	}

	/**
	 * Delete auth.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param authKey
	 *            the auth key
	 * @param authValue
	 *            the auth value
	 * @param typeAuth
	 *            the type auth
	 * @return the int
	 */
	public static int deleteAuth(final String restUri, final String authKey, final String authValue,
			final REST_AUTH typeAuth) {
		UniRestUtils.writeLog("deleteAuth Started..");
		Unirest.setHttpClient(UniRestUtils.getHttpClient());
		HttpResponse<JsonNode> response = null;
		HttpRequestWithBody request = null;
		try {
			request = Unirest.delete(restUri).header(UniRestUtils.ACCEPT, UniRestUtils.APP_TYPE_JSON)
					.header(UniRestUtils.CONTENT_TYPE, UniRestUtils.APP_TYPE_JSON);

			switch (typeAuth) {
			case API:
				request.header(authKey, authValue);
				break;
			case BASIC:
				request.basicAuth(authKey, authValue);
				break;
			default:
				break;
			}

			response = request.asJson();
		} catch (final UnirestException e) {
			UniRestUtils.writeLog(e.getMessage());
			return 0;
		}
		final int status = response.getStatus();
		UniRestUtils.writeLog("status:" + status);
		try {
			Unirest.shutdown();
		} catch (final IOException e) {
			UniRestUtils.writeLog(e.getMessage());

		}
		UniRestUtils.writeLog("deleteAuth Ended..");
		return status;
	}

	/**
	 * Delete using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the int
	 */
	public static int deleteUsingBasic(final String restUri, final String userName, final String password) {
		return UniRestUtils.deleteAuth(restUri, userName, password, REST_AUTH.BASIC);
	}

	/**
	 * Delete using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the int
	 */
	public static int deleteUsingApi(final String restUri, final String userName, final String password) {
		return UniRestUtils.deleteAuth(restUri, userName, password, REST_AUTH.API);
	}

	/**
	 * Op auth.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param authKey
	 *            the auth key
	 * @param authValue
	 *            the auth value
	 * @param typeAuth
	 *            the type auth
	 * @param operation
	 *            the operation
	 * @return the string
	 */
	public static String opAuth(final String restUri, final String jsonInputText, final String authKey,
			final String authValue, final REST_AUTH typeAuth, final REST_OPS operation) {
		UniRestUtils.writeLog("opAuth Started..");
		Unirest.setHttpClient(UniRestUtils.getHttpClient());
		HttpResponse<JsonNode> response = null;
		HttpRequestWithBody request = null;

		try {
			switch (operation) {
			case POST:
				request = Unirest.post(restUri);
				break;
			case PUT:
				request = Unirest.put(restUri);
				break;
			case PATCH:
				request = Unirest.put(restUri);
				break;
			default:
				UniRestUtils.writeLog("Unknown Operation!!");
				return null;
			}

			request.header(UniRestUtils.ACCEPT, UniRestUtils.APP_TYPE_JSON).header(UniRestUtils.CONTENT_TYPE,
					UniRestUtils.APP_TYPE_JSON);

			switch (typeAuth) {
			case API:
				request.header(authKey, authValue);
				break;
			case BASIC:
				request.basicAuth(authKey, authValue);
				break;
			default:
				UniRestUtils.writeLog("Unknown Authentication");
				return null;
			}

			response = request.body(jsonInputText).asJson();
		} catch (final UnirestException e) {
			UniRestUtils.writeLog(e.getMessage());
			return null;
		}
		final JsonNode resBody = response.getBody();
		UniRestUtils.writeLog(resBody.toString());
		try {
			Unirest.shutdown();
		} catch (final IOException e) {
			UniRestUtils.writeLog(e.getMessage());

		}
		UniRestUtils.writeLog("opAuth Ended..");
		return resBody.toString();
	}

	/**
	 * Put json data using text.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String putJsonDataUsingText(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.BASIC, REST_OPS.PUT);
	}

	/**
	 * Put json text using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String putJsonTextUsingBasic(final String restUri, final String jsonInputText, final String userName,
			final String password) {
		return UniRestUtils.opAuth(restUri, jsonInputText, userName, password, REST_AUTH.BASIC, REST_OPS.PUT);
	}

	/**
	 * Put json text using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonInputText
	 *            the json input text
	 * @param apiKey
	 *            the api key
	 * @param apiVal
	 *            the api val
	 * @return the string
	 */
	public static String putJsonTextUsingApi(final String restUri, final String jsonInputText, final String apiKey,
			final String apiVal) {
		return UniRestUtils.opAuth(restUri, jsonInputText, apiKey, apiVal, REST_AUTH.API, REST_OPS.PUT);
	}

	/**
	 * Put json file using basic.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the string
	 */
	public static String putJsonFileUsingBasic(final String restUri, final FileInputStream jsonFileInputStream,
			final String userName, final String password) {

		return UniRestUtils.putJsonTextUsingBasic(restUri, UniRestUtils.readFile(jsonFileInputStream), userName,
				password);
	}

	/**
	 * Put json file using api.
	 *
	 * @param restUri
	 *            the rest uri
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @param apiKey
	 *            the api key
	 * @param apiVal
	 *            the api val
	 * @return the string
	 */
	public static String putJsonFileUsingApi(final String restUri, final FileInputStream jsonFileInputStream,
			final String apiKey, final String apiVal) {
		return UniRestUtils.putJsonTextUsingApi(restUri, UniRestUtils.readFile(jsonFileInputStream), apiKey, apiVal);
	}

	/**
	 * Read file.<br>
	 * wrapper method for readJsonFromUrl
	 *
	 * @param jsonFileInputStream
	 *            the json file input stream
	 * @return the string
	 */
	public static String readFile(final FileInputStream jsonFileInputStream) {
		UniRestUtils.writeLog("Reading JSON File Started..");

		String jsonInputText = null;

		try {
			jsonInputText = JsonUtil.readJsonFromUrl(jsonFileInputStream);
		} catch (final IOException e) {
			UniRestUtils.writeLog(e.getMessage());
			return null;
		}

		UniRestUtils.writeLog("jsonText:" + jsonInputText);
		UniRestUtils.writeLog("Reading JSON File Ended..");
		return jsonInputText;
	}

	/**
	 * Write log.
	 *
	 * @param message
	 *            the message
	 */
	private static void writeLog(final String message) {
		System.out.println(message);
	}

	/**
	 * Gets the response.
	 *
	 * @param restUri the rest uri
	 * @return the response
	 */
	public static HttpResponse<JsonNode> getResponse(final String restUri) {
		writeLog("getResponse Started..");
		Unirest.setHttpClient(UniRestUtils.getHttpClient());
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(restUri).header("accept", "application/json")
					.header("Content-Type", "application/json").asJson();
		} catch (final UnirestException ex) {
			writeLog(ex.getMessage());
		}
		return response;
	}
}
