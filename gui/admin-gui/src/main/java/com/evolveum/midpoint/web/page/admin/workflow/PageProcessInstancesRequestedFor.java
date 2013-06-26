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

package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.web.page.PageBase;
import org.apache.wicket.model.IModel;

/**
 * Created with IntelliJ IDEA.
 * User: mederly
 * Date: 28.9.2012
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class PageProcessInstancesRequestedFor extends PageProcessInstances {

    protected IModel<String> createPageTitleModel() {
        return createStringResource("PageProcessInstancesRequestedFor.title");
    }

    public PageProcessInstancesRequestedFor() {
        super(false, true);
    }

    @Override
    public PageBase reinitialize() {
        return new PageProcessInstancesRequestedFor();
    }
}
