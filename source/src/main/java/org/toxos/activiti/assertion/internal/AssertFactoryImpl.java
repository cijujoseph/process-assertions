/*******************************************************************************
 * Copyright 2014 Tiese Barrell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.toxos.activiti.assertion.internal;

import org.toxos.activiti.assertion.ProcessAssertConfiguration;

/**
 * Default implementation of {@link AssertFactory}.
 * 
 * @author Tiese Barrell
 * 
 */
public class AssertFactoryImpl implements AssertFactory {

    @Override
    public ProcessInstanceAssertable getProcessInstanceAssertable(final ProcessAssertConfiguration configuration) {
        return new ProcessInstanceAssert(configuration);
    }

    @Override
    public EndEventAssertable getEndEventAssertable(final ProcessAssertConfiguration configuration) {
        return new EndEventAssert(configuration);
    }

    @Override
    public TaskInstanceAssertable getTaskInstanceAssertable(final ProcessAssertConfiguration configuration) {
        return new TaskInstanceAssert(configuration);
    }

    @Override
    public HistoricVariableInstanceAssertable getHistoricVariableInstanceAssertable(ProcessAssertConfiguration configuration) {
        return new HistoricVariableInstanceAssert(configuration);
    }

}
