/******************************************************************************
 * Copyright (c) 2000-2016 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.designer.AST.TTCN3.templates;

import org.eclipse.titan.designer.AST.IType.Type_type;
import org.eclipse.titan.designer.AST.TTCN3.Expected_Value_type;
import org.eclipse.titan.designer.parsers.CompilationTimeStamp;

/**
 * Represents a template for the subset matching mechanism.
 * 
 * <p>Example 1: 
 * <p> type set    of integer SoI;
 * <p> template SoI t_1 := subset ( 1,2,? );
 * 
 * <p>Example 2:
 * <p>type set    of integer SoI;
 * <p>template SoI t_SoI1 := {1, 2, (6..9)};
 * <p>template subset(all from t_SOI1) length(2);
 * 
 * @author Kristof Szabados
 * */
public final class SubsetMatch_Template extends CompositeTemplate {

	public SubsetMatch_Template(final ListOfTemplates templates) {
		super(templates);
	}

	@Override
	public Template_type getTemplatetype() {
		return Template_type.SUBSET_MATCH;
	}

	@Override
	public String getTemplateTypeName() {
		if (isErroneous) {
			return "erroneous subset match";
		}

		return "subset match";
	}

	@Override
	public Type_type getExpressionReturntype(final CompilationTimeStamp timestamp, final Expected_Value_type expectedValue) {
		if (getIsErroneous(timestamp)) {
			return Type_type.TYPE_UNDEFINED;
		}

		return Type_type.TYPE_SET_OF;
	}

	@Override
	public void checkSpecificValue(final CompilationTimeStamp timestamp, final boolean allowOmit) {
		getLocation().reportSemanticError("A specific value expected instead of a subset match");
	}

	@Override
	protected void checkTemplateSpecificLengthRestriction(final CompilationTimeStamp timestamp, final Type_type typeType) {
		if (Type_type.TYPE_SET_OF.equals(typeType)) {
			lengthRestriction.checkNofElements(timestamp, getNofTemplatesNotAnyornone(timestamp), false, true, true, this);
		}
	}

	@Override
	protected String getNameForStringRep() {
		return "subset";
	}
}
