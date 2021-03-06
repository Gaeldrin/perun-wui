package cz.metacentrum.perun.wui.setAffiliation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import cz.metacentrum.perun.wui.client.PerunPlaceManager;
import cz.metacentrum.perun.wui.client.PerunRootPresenter;
import cz.metacentrum.perun.wui.client.resources.ExceptionLogger;
import cz.metacentrum.perun.wui.client.resources.PerunResources;
import cz.metacentrum.perun.wui.client.utils.Utils;
import cz.metacentrum.perun.wui.pages.*;
import cz.metacentrum.perun.wui.setAffiliation.client.resources.PerunSetAffiliationPlaceTokens;
import cz.metacentrum.perun.wui.setAffiliation.client.resources.PerunSetAffiliationResources;
import cz.metacentrum.perun.wui.setAffiliation.pages.affiliation.AffiliationPresenter;
import cz.metacentrum.perun.wui.setAffiliation.pages.affiliation.AffiliationView;


/**
 * Entry point class and GWTP module for Perun WUI Set Affiliation.
 *
 * @author Pavel Zlámal <zlamal@cesnet.cz>
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class PerunSetAffiliation extends AbstractPresenterModule implements EntryPoint {

	@Override
	protected void configure() {

		install(new DefaultModule.Builder().placeManager(PerunPlaceManager.class).build());

		// make sure app is embedded in a correct DIV
		bind(RootPresenter.class).to(PerunRootPresenter.class).asEagerSingleton();

		// Main Application must bind generic Presenter and custom View !!
		bindPresenter(PerunSetAffiliationPresenter.class, PerunSetAffiliationPresenter.MyView.class, PerunSetAffiliationView.class, PerunSetAffiliationPresenter.MyProxy.class);

		// bind app-specific pages
		bindPresenter(AffiliationPresenter.class, AffiliationPresenter.MyView.class, AffiliationView.class, AffiliationPresenter.MyProxy.class);

		// pre-defined places
		bindConstant().annotatedWith(DefaultPlace.class).to(PerunSetAffiliationPlaceTokens.AFFILIATION);
		bindConstant().annotatedWith(ErrorPlace.class).to(PerunSetAffiliationPlaceTokens.NOT_FOUND);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(PerunSetAffiliationPlaceTokens.UNAUTHORIZED);

		// generic pages
		bindPresenter(NotAuthorizedPresenter.class, NotAuthorizedPresenter.MyView.class, NotAuthorizedView.class, NotAuthorizedPresenter.MyProxy.class);
		bindPresenter(NotFoundPresenter.class, NotFoundPresenter.MyView.class, NotFoundView.class, NotFoundPresenter.MyProxy.class);
		bindPresenter(LogoutPresenter.class, LogoutPresenter.MyView.class, LogoutView.class, LogoutPresenter.MyProxy.class);
		bindPresenter(NotUserPresenter.class, NotUserPresenter.MyView.class, NotUserView.class, NotUserPresenter.MyProxy.class);

	}

	@Override
	public void onModuleLoad() {

		ExceptionLogger exceptionHandler = new ExceptionLogger();
		GWT.setUncaughtExceptionHandler(exceptionHandler);

		try {

			// set default for Growl plugin
			Utils.getDefaultNotifyOptions().makeDefault();

			// ensure injecting custom CSS styles of PerunWui
			PerunResources.INSTANCE.gss().ensureInjected();

			PerunSetAffiliationResources.INSTANCE.gss().ensureInjected();

		} catch (Exception ex) {
			exceptionHandler.onUncaughtException(ex);
		}

	}

}