/*
 * Created on Mar 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.gmf.tests.runtime.diagram.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.Assert;

import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.DiagramUtil;
import org.eclipse.gmf.runtime.diagram.ui.properties.Properties;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import com.ibm.xtools.notation.Diagram;
import com.ibm.xtools.notation.Edge;
import com.ibm.xtools.notation.Node;


/**
 * @author sshaw
 *
 * Utility class for creating simple diagrams for testing
 */
public class DiagramCreator {

	static public Diagram createSimpleDiagram(PreferencesHint preferencesHint) {
		Diagram diagram = createEmptyDiagram(preferencesHint);
		createNodes(diagram, preferencesHint);

		return diagram;
	}

	static public Diagram createEmptyDiagram(final PreferencesHint preferencesHint) {
		final Diagram[] dgmContainer = new Diagram[1];

		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {

						public Object run() {
							Diagram diagram = DiagramUtil.createDiagram(null, "Freeform", preferencesHint); //$NON-NLS-1$
							Assert.isNotNull(diagram);
							dgmContainer[0] = diagram;
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					Trace.trace(DiagramUIPlugin.getInstance(),
						DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
						"MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}
		});

		return dgmContainer[0];
	}

	static public List createNodes(final Diagram diagram, final PreferencesHint preferencesHint) {

		final List list = new ArrayList(2);
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {

						public Object run() {
							Node note1 = DiagramUtil.createNode(diagram,
								null, Properties.NOTE, preferencesHint); //$NON-NLS-1$
							Assert.isNotNull(note1, "Note1 creation failed"); //$NON-NLS-1$
							Assert.isTrue(diagram.getChildren().get(0) == note1,
								"Note1 is not inserted in diagram"); //$NON-NLS-1$

							Node note2 = DiagramUtil.createNode(diagram,
								null, Properties.NOTE, preferencesHint); //$NON-NLS-1$
							Assert.isNotNull(note2, "Note2 creation failed"); //$NON-NLS-1$
							Assert.isTrue(diagram.getChildren().get(1) == note2,
								"Note2 is not inserted in diagram"); //$NON-NLS-1$

							Edge noteAttachment = DiagramUtil.createEdge(
								note1, note2, null, Properties.NOTEATTACHMENT, preferencesHint); //$NON-NLS-1$
							Assert.isNotNull(noteAttachment,
								"NoteAttachment creation failed"); //$NON-NLS-1$
							Assert.isTrue(diagram.getEdges().get(0) == noteAttachment,
								"NoteAttachment is not inserted in diagram"); //$NON-NLS-1$
							Assert.isTrue(note1.getSourceEdges().get(0) == noteAttachment,
								"NoteAttachment is not inserted in note1"); //$NON-NLS-1$
							Assert.isTrue(note2.getTargetEdges().get(0) == noteAttachment, 
								"NoteAttachment is not inserted in note2"); //$NON-NLS-1$
							Assert.isTrue(noteAttachment.getSource() == note1,
								"Note1 is not source for noteattachment"); //$NON-NLS-1$
							Assert.isTrue(noteAttachment.getTarget() == note2, 
								"Note2 is not target for noteattachment"); //$NON-NLS-1$

							list.add(note1);
							list.add(note2);
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					Trace.trace(DiagramUIPlugin.getInstance(),
						DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
						"MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}
		});

		return list;
	}
}
