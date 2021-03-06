/******************************************************************************
 * Copyright (c) 2000-2016 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.executor.executors.single;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.titan.executor.tabpages.maincontroller.SingleMainControllerTab;

/**
 * @author Kristof Szabados
 * */
public final class LaunchShortcut extends org.eclipse.titan.executor.executors.LaunchShortcut {
	
	@Override
	protected String getConfigurationId() {
		return "org.eclipse.titan.executor.executors.single.LaunchConfigurationDelegate";
	}

	@Override
	protected String getDialogTitle() {
		return "Select single mode execution configuration";
	}

	@Override
	public boolean initLaunchConfiguration(ILaunchConfigurationWorkingCopy configuration, IProject project, String configFilePath) {
		return SingleMainControllerTab.initLaunchConfiguration(configuration, project, configFilePath);
	}
}
