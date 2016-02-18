package org.toxos.activiti.assertions.integration;

import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.toxos.activiti.assertion.ProcessAssert;
import org.toxos.activiti.assertions.integration.common.configuration.ActivitiTestConfiguration;
import org.toxos.activiti.assertions.integration.common.test.AbstractProcessAssertionsTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivitiTestConfiguration.class })
public class ProcessAssertionsTest extends AbstractProcessAssertionsTest {

    @Test
    public void testProcessAssertionsBinaryCompatibilityError() {
        final ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess");

        ProcessAssert.assertProcessActive(instance);
        ProcessAssert.assertTaskUncompleted(instance, "usertask1");
        System.out.println("EXECUTED TEST");
    }
}
