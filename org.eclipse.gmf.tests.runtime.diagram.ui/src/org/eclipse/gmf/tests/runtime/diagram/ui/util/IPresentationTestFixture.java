/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.tests.runtime.diagram.ui.util;


import org.eclipse.core.resources.IProject;
import org.eclipse.gef.commands.CommandStack;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import com.ibm.xtools.notation.Diagram;
import com.ibm.xtools.notation.Edge;

/**
 * @author choang
 *
 * All fixture should implement this interface.  See com.ibm.xtools.presentation.umlvisualizer.util.UmlVisualizerTestFixture for and example 
 * example
 */

public interface IPresentationTestFixture
{
	public Diagram getDiagram();
	/**
	 * Returns the diagramFile.
	 * @return IFile
	 */
	//public IFile getDiagramFile();

	/**
	 * Returns the editor.
	 * @return IDiagramWorkbenchPart
	 */
	public IDiagramWorkbenchPart getDiagramWorkbenchPart();

	/**
	 * Returns the project.
	 * @return IProject
	 */
	public IProject getProject();
	
	/**
	 * Method getCommandStack.
	 * @return CommandStack  Command stack for the diagram editor
	 */
	public CommandStack getCommandStack();	
	/**
	 * Returns the connectView for test
	 * If the test doesn't have a connector view it returns null
	 * @return IConnectorView
	 */
	public Edge getConnectorView();
	/**
	 * Will setup all the data for the fixture
	 * such as creating the diagram and project, and
	 * all the tests data.
	 * and opening it the diagram diagram
	 */
	 public void setup() throws Exception;
	 public void openDiagram() throws Exception;
	 public boolean closeDiagram();
	
    /**
     * Method getdiagramEditPart
     * Helper method that returns the <code>DiagramEditPart</code>
     * for the diagram type that we are using for the tests.  Note that
     * for each different type of diagram their is an corresponding DiagrmEditPart
     * class
     * 
     * 
     */	
	 public DiagramEditPart getDiagramEditPart();
     	 
	
	
	/**
	 * Will clean up all the resources create during setup
	 * and the test ensuring that all the project resources
	 * have been deleted.
	 * 
	 */
	public void tearDown() throws Exception;

	/** Clears the diaplay's event queue. */
	void flushEventQueue();
    
     /**
     * Method createRelationship will create and return a  relationship between the <code>sourceEditPart</code>
     * and the <code>targetEditPart</code> 
     * @param sourceEditPart
     * @param targetEditPart
     * @param elementTypeInfo a create relationship element kind of this type element info.
     * @return IGraphicalEditPart The graphical editpart that will be created to control the interactions
     * between the view and the figure world
     */
    // public IGraphicalEditPart createRelationship(final IGraphicalEditPart sourceEditPart,final IGraphicalEditPart targetEditPart, IElementTypeInfo elementTypeInfo);
   
	/**
	 * Gets the preferences hint that is to be used to find the appropriate
	 * preference store from which to retrieve diagram preference values. The
	 * preference hint is mapped to a preference store in the preference
	 * registry <@link DiagramPreferencesRegistry>.
	 * 
	 * @return the preferences hint
	 */
	public PreferencesHint getPreferencesHint();

}
