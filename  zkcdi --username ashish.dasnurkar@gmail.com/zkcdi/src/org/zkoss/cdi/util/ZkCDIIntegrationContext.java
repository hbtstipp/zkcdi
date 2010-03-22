/* ZkCDIIntegrationContext.java
{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jan 31, 2010 10:30:14 AM, Created by ashish
}}IS_NOTE

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.cdi.util;

import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.xel.impl.ExecutionResolver;

/**
 * @author ashish
 *
 */
public class ZkCDIIntegrationContext {

	private static ThreadLocal<Component> components = new ThreadLocal<Component>();

	public static Component getContextComponent() {
		return components.get();
	}

	/**
	 * Sets context component which is later required for ZK component injection
	 * @param component
	 */
	public static void setContextComponent(Component component) {
		components.set(component);
	}
	
	/**
	 * Clears context component from ZK CDI integration context that was set prior to ZK component injection
	 */
	public static void clearContextComponent() {
		components.remove();
	}
	
	/**
	 * Sets self context component which is later required for ZK event processing using CDI event notificatio model
	 * @param component
	 */
	public static void setSelfContextComponent(Component component) {
		final Execution exec = Executions.getCurrent();
		final VariableResolver vresolver = exec.getVariableResolver();
		((ExecutionResolver)vresolver).setSelf(component);
	}
	
	/**
	 * Returns current self context component
	 * @returns component
	 */
	public static Component getSelfContextComponent() {
		final Execution exec = Executions.getCurrent();
		final VariableResolver vresolver = exec.getVariableResolver();
		return (Component) ((ExecutionResolver)vresolver).getSelf();
	}

}
