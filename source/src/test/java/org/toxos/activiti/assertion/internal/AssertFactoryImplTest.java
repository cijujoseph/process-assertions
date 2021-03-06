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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.toxos.activiti.assertion.ProcessAssertConfiguration;

/**
 * Tests for {@link AssertFactoryImpl}.
 * 
 * @author Tiese Barrell
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AssertFactoryImplTest {

    private AssertFactory classUnderTest;

    @Mock
    private ProcessAssertConfiguration processAssertConfigurationMock;

    @Before
    public void before() throws Exception {
        classUnderTest = new AssertFactoryImpl();
    }

    @Test
    public void testGetProcessInstanceAssertable() throws Exception {
        final ProcessInstanceAssertable result = classUnderTest.getProcessInstanceAssertable(processAssertConfigurationMock);
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testGetTaskInstanceAssertable() throws Exception {
        final TaskInstanceAssertable result = classUnderTest.getTaskInstanceAssertable(processAssertConfigurationMock);
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testGetEndEventInstanceAssertable() throws Exception {
        final EndEventAssertable result = classUnderTest.getEndEventAssertable(processAssertConfigurationMock);
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testGetHistoricVariableInstanceAssertable() throws Exception {
        final HistoricVariableInstanceAssertable result = classUnderTest.getHistoricVariableInstanceAssertable(processAssertConfigurationMock);
        assertThat(result, is(notNullValue()));
    }

}
