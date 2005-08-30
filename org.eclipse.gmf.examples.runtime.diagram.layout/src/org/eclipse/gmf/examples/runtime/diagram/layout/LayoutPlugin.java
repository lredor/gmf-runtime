/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * � Copyright IBM Corporation 2005. All Rights Reserved. 
 */
package org.eclipse.gmf.examples.runtime.diagram.layout;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class LayoutPlugin
	extends AbstractUIPlugin {

	/**
	 * The shared plug-in instance
	 */
	private static LayoutPlugin plugin;
 
	/**
	 * The plug-in resource bundle
	 */
	private ResourceBundle resourceBundle;

	/**
	 * Constructor
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#AbstractUIPlugin()
	 */
	public LayoutPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle
				.getBundle("org.eclipse.gmf.examples.runtime.diagram.layout.LayoutPluginResources"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static LayoutPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = LayoutPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null ? bundle.getString(key) : key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
