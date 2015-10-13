/*
 * Copyright (c) 2010-2013 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.midpoint.web.page.admin.configuration.component;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.evolveum.midpoint.web.component.ObjectPolicyConfigurationEditor;
import com.evolveum.midpoint.web.component.util.SimplePanel;
import com.evolveum.midpoint.web.page.admin.configuration.dto.AEPlevel;
import com.evolveum.midpoint.web.page.admin.configuration.dto.ObjectPolicyConfigurationTypeDto;
import com.evolveum.midpoint.web.page.admin.configuration.dto.SystemConfigurationDto;
import com.evolveum.midpoint.web.page.admin.dto.ObjectViewDto;
import com.evolveum.midpoint.web.util.InfoTooltipBehavior;
import com.evolveum.midpoint.web.util.WebMiscUtil;

/**
 * @author lazyman
 */
public class SystemConfigPanel extends SimplePanel<SystemConfigurationDto> {

    private static final String ID_GLOBAL_PASSWORD_POLICY_CHOOSER = "passwordPolicyChooser";
    private static final String ID_GLOBAL_SECURITY_POLICY_CHOOSER = "securityPolicyChooser";
    private static final String ID_OBJECT_POLICY_EDITOR = "objectPolicyEditor";
    private static final String ID_GLOBAL_AEP = "aepChooser";
    private static final String ID_CLEANUP_AUDIT_RECORDS = "auditRecordsCleanup";
    private static final String ID_CLEANUP_CLOSED_TASKS = "closedTasksCleanup";
    private static final String ID_CLEANUP_AUDIT_RECORDS_TOOLTIP = "auditRecordsCleanupTooltip";
    private static final String ID_CLEANUP_CLOSED_TASKS_TOOLTIP = "closedTasksCleanupTooltip";

    private static final String ID_EXPERIMENTAL_CODE_CHECKBOX = "experimentalCodeCheckbox";
    
    
    private static final String ID_MAIN_FORM = "mainForm";

 


    public SystemConfigPanel(String id, IModel<SystemConfigurationDto> model) {
        super(id, model);

        setOutputMarkupId(true);
    }

    @Override
    protected void initLayout(){

        ChooseTypePanel passPolicyChoosePanel = new ChooseTypePanel(ID_GLOBAL_PASSWORD_POLICY_CHOOSER,
                new PropertyModel<ObjectViewDto>(getModel(), SystemConfigurationDto.F_PASSWORD_POLICY));

        ChooseTypePanel securityPolicyChoosePanel = new ChooseTypePanel(ID_GLOBAL_SECURITY_POLICY_CHOOSER,
                new PropertyModel<ObjectViewDto>(getModel(), SystemConfigurationDto.F_SECURITY_POLICY));
        add(passPolicyChoosePanel);
        add(securityPolicyChoosePanel);

        ObjectPolicyConfigurationEditor objectPolicyEditor = new ObjectPolicyConfigurationEditor(ID_OBJECT_POLICY_EDITOR,
                new PropertyModel<List<ObjectPolicyConfigurationTypeDto>>(getModel(), SystemConfigurationDto.F_OBJECT_POLICY_LIST));
        add(objectPolicyEditor);

        DropDownChoice<AEPlevel> aepLevel = new DropDownChoice<>(ID_GLOBAL_AEP,
                new PropertyModel<AEPlevel>(getModel(), SystemConfigurationDto.F_AEP_LEVEL),
                WebMiscUtil.createReadonlyModelFromEnum(AEPlevel.class),
                new EnumChoiceRenderer<AEPlevel>(SystemConfigPanel.this));
        aepLevel.setOutputMarkupId(true);
        if(aepLevel.getModel().getObject() == null){
            aepLevel.getModel().setObject(null);
        }
        aepLevel.add(new EmptyOnChangeAjaxFormUpdatingBehavior());
        add(aepLevel);
        

        TextField<String> auditRecordsField = WebMiscUtil.createAjaxTextField(ID_CLEANUP_AUDIT_RECORDS, new PropertyModel<String>(getModel(), SystemConfigurationDto.F_AUDIT_CLEANUP));
        
        TextField<String> closedTasksField = WebMiscUtil.createAjaxTextField(ID_CLEANUP_CLOSED_TASKS, new PropertyModel<String>(getModel(), SystemConfigurationDto.F_TASK_CLEANUP));
        
        add(auditRecordsField);
        add(closedTasksField);

        createTooltip(ID_CLEANUP_AUDIT_RECORDS_TOOLTIP);
        createTooltip(ID_CLEANUP_CLOSED_TASKS_TOOLTIP);

        
        CheckBox experimentalCodeCheck = WebMiscUtil.createAjaxCheckBox(ID_EXPERIMENTAL_CODE_CHECKBOX, new PropertyModel<Boolean>(getModel(), SystemConfigurationDto.F_ENABLE_EXPERIMENTAL_CODE));
        add(experimentalCodeCheck);
       
    
    }

   

    private void createTooltip(String id) {
        Label tooltip = new Label(id);
        tooltip.add(new InfoTooltipBehavior());
        add(tooltip);
    }
}
