package cz.metacentrum.perun.wui.json.managers;

import cz.metacentrum.perun.wui.json.JsonClient;
import cz.metacentrum.perun.wui.json.JsonEvents;

/**
 * Manager with standard callbacks to Perun's API (UsersManager).
 * <p/>
 * Each callback returns unique ID used to make call. Such call can be removed
 * while processing to prevent any further actions based on it's {@link cz.metacentrum.perun.wui.json.JsonEvents JsonEvents}.
 *
 * @author Pavel Zlámal <zlamal@cesnet.cz>
 */
public class UsersManager {

	private static final String USERS_MANAGER = "usersManager/";

	/**
	 * Check if provided login in selected namespace is available.
	 *
	 * @param namespace namespace to check in
	 * @param login login to check for
	 * @param events Events done on callback
	 *
	 * @return int unique ID of callback
	 */
	public static int isLoginAvailable(String namespace, String login, JsonEvents events) {

		JsonClient client = new JsonClient(12000);
		client.put("login", login);
		client.put("loginNamespace", namespace);
		return client.getData(USERS_MANAGER + "isLoginAvailable", events);

	}


}