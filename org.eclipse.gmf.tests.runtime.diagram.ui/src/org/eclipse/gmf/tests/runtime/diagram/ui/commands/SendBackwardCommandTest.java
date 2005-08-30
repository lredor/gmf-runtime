/***************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2004.  All Rights Reserved.
 *
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 **************************************************************************/

package org.eclipse.gmf.tests.runtime.diagram.ui.commands;

import org.eclipse.core.runtime.NullProgressMonitor;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.internal.commands.SendBackwardCommand;
import com.ibm.xtools.notation.View;


/**
 * Test the SendBackward ZOrder command
 *
 * @author jschofie
 */
public class SendBackwardCommandTest
	extends CommandTestFixture {

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.tests.runtime.diagram.ui.commands.CommandTestFixture#createCommand()
	 */
	protected ICommand createCommand() {
		return new SendBackwardCommand(noteView);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.tests.runtime.diagram.ui.commands.CommandTestFixture#testDoExecute()
	 */
	public void testDoExecute() {
		assertEquals(getDiagram().getChildren().size(), 1);
		View firstNote  = (View)getDiagram().getChildren().get(0);

		ICommand zorderCommand = new SendBackwardCommand(firstNote);
		zorderCommand.execute(new NullProgressMonitor());

		assertEquals(getDiagram().getChildren().get(0), firstNote);
	}

	public void testLastEntry() {
		createView();
		assertEquals(getDiagram().getChildren().size(), 2);
		View firstNote  = (View)getDiagram().getChildren().get(0);
		View secondNote = (View)getDiagram().getChildren().get(1);

		ICommand zorderCommand = new SendBackwardCommand(secondNote);
		zorderCommand.execute(new NullProgressMonitor());

		assertEquals(getDiagram().getChildren().get(0), secondNote);
		assertEquals(getDiagram().getChildren().get(1), firstNote);
	}
	
	public void testMiddleEntry() {
		createView();
		createView();
		assertEquals(getDiagram().getChildren().size(), 3);
		View firstNote  = (View)getDiagram().getChildren().get(0);
		View secondNote = (View)getDiagram().getChildren().get(1);
		View thirdNote  = (View)getDiagram().getChildren().get(2);

		ICommand zorderCommand = new SendBackwardCommand(secondNote);
		zorderCommand.execute(new NullProgressMonitor());

		assertEquals(getDiagram().getChildren().get(0), secondNote);
		assertEquals(getDiagram().getChildren().get(1), firstNote);
		assertEquals(getDiagram().getChildren().get(2), thirdNote);
	}
	
	public void testSendToBack() {
		createView();
		createView();
		assertEquals(getDiagram().getChildren().size(), 3);
		View firstNote  = (View)getDiagram().getChildren().get(0);
		View secondNote = (View)getDiagram().getChildren().get(1);
		View thirdNote  = (View)getDiagram().getChildren().get(2);

		ICommand zorderCommand = new SendBackwardCommand(thirdNote);
		zorderCommand.execute(new NullProgressMonitor());

		assertEquals(getDiagram().getChildren().get(0), firstNote);
		assertEquals(getDiagram().getChildren().get(1), thirdNote);
		assertEquals(getDiagram().getChildren().get(2), secondNote);
		
		zorderCommand = new SendBackwardCommand(thirdNote);
		zorderCommand.execute(new NullProgressMonitor());
		
		assertEquals(getDiagram().getChildren().get(0), thirdNote);
		assertEquals(getDiagram().getChildren().get(1), firstNote);
		assertEquals(getDiagram().getChildren().get(2), secondNote);
	}
}
