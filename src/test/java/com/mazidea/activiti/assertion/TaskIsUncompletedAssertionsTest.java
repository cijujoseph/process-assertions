/*******************************************************************************
 * Copyright 2013 Tiese Barrell
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
package com.mazidea.activiti.assertion;

import static com.mazidea.activiti.assertion.ProcessAssert.assertTaskUncompleted;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

/**
 * Tests for assertions that test a task is uncompleted.
 * 
 * @author Tiese Barrell
 * 
 */
@ContextConfiguration("classpath:application-context.xml")
public class TaskIsUncompletedAssertionsTest extends AbstractProcessAssertTest {

  private static final String SINGLE_USER_TASK = "singleUserTask";

  private static final String USER_TASK_1 = "userTask1";

  private static final String USER_TASK_2 = "userTask2";

  @Test
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_TWO_USER_TASKS_BPMN)
  public void testTaskUncompletedForTaskObject() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_TWO_USER_TASKS);
    Task task1 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_1).singleResult();
    assertTaskUncompleted(activitiRule, task1);

    // Move on to task two and test
    taskService.complete(task1.getId());
    Task task2 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_2).singleResult();
    assertTaskUncompleted(activitiRule, task2);
  }

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_TWO_USER_TASKS_BPMN)
  public void testTaskUncompletedForTaskObjectForCompletedTask() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_TWO_USER_TASKS);
    Task task1 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_1).singleResult();
    taskService.complete(task1.getId());

    assertTaskUncompleted(activitiRule, task1);
    fail("Expected exception for task1 that is now completed");
  }

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_SINGLE_USER_TASK_BPMN)
  public void testTaskUncompletedFailureForNullTaskObject() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_SINGLE_USER_TASK);
    Task nullTask = null;
    assertTaskUncompleted(activitiRule, nullTask);
    fail("Expected exception for null task object");
  }

  @Test
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_TWO_USER_TASKS_BPMN)
  public void testTaskUncompletedForTaskId() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_TWO_USER_TASKS);
    Task task1 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_1).singleResult();
    assertTaskUncompleted(activitiRule, task1.getId());

    // Move on to task two and test
    taskService.complete(task1.getId());
    Task task2 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_2).singleResult();
    assertTaskUncompleted(activitiRule, task2.getId());
  }

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_TWO_USER_TASKS_BPMN)
  public void testTaskUncompletedForTaskIdForCompletedTask() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_TWO_USER_TASKS);
    Task task1 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_1).singleResult();
    taskService.complete(task1.getId());

    assertTaskUncompleted(activitiRule, task1.getId());
    fail("Expected exception for task1 that is now completed");
  }

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_SINGLE_USER_TASK_BPMN)
  public void testTaskUncompletedFailureForNullTaskId() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_SINGLE_USER_TASK);
    String nullString = null;
    assertTaskUncompleted(activitiRule, nullString);
    fail("Expected exception for null task id");
  }

  @Test
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_TWO_USER_TASKS_BPMN)
  public void testTaskUncompletedForProcessInstanceObjectAndKey() throws Exception {
    final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(TEST_PROCESS_TWO_USER_TASKS);
    assertNotNull(processInstance);
    assertTaskUncompleted(activitiRule, processInstance, USER_TASK_1);

    // Move on to task two and test
    Task task1 = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(USER_TASK_1).singleResult();
    taskService.complete(task1.getId());

    assertTaskUncompleted(activitiRule, processInstance, USER_TASK_2);

    try {
      assertTaskUncompleted(activitiRule, processInstance, USER_TASK_1);
      fail("Expected exception for task1 that is now completed");
    } catch (AssertionError e) {
      // no-op
    }
  }

  // Marker

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_SINGLE_USER_TASK_BPMN)
  @Ignore
  public void testTaskUncompletedFailureForTaskObjectOnlyWhenCompleted() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_SINGLE_USER_TASK);
    Task task = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(SINGLE_USER_TASK).singleResult();
    taskService.complete(task.getId(), Collections.<String, Object> emptyMap());
    assertTaskUncompleted(activitiRule, task);
  }

  @Test(expected = AssertionError.class)
  @Deployment(resources = DIAGRAMS_TEST_PROCESS_STRAIGHT_THROUGH_BPMN)
  @Ignore
  public void testTaskUncompletedFailureForTaskObjectOnly() throws Exception {
    runtimeService.startProcessInstanceByKey(TEST_PROCESS_STRAIGHT_THROUGH);
    Task task = activitiRule.getTaskService().createTaskQuery().taskDefinitionKey(SINGLE_USER_TASK).singleResult();
    assertTaskUncompleted(activitiRule, task);
  }

  // @Test
  // @Deployment(resources = DIAGRAMS_TEST_PROCESS_SINGLE_USER_TASK_BPMN)
  // public void testTaskUncompletedForObject() throws Exception {
  // ProcessInstance processInstance =
  // runtimeService.startProcessInstanceByKey(TEST_PROCESS_SINGLE_USER_TASK);
  // assertTaskUncompleted(activitiRule, processInstance, "singleTask");
  // }
  //
  // @Test
  // @Deployment(resources = DIAGRAMS_TEST_PROCESS_SINGLE_USER_TASK_BPMN)
  // public void testProcessActiveForId() throws Exception {
  // ProcessInstance processInstance =
  // runtimeService.startProcessInstanceByKey(TEST_PROCESS_SINGLE_USER_TASK);
  // assertProcessActive(activitiRule, processInstance.getId());
  // }
  //
  // @Test(expected = AssertionError.class)
  // @Deployment(resources = DIAGRAMS_TEST_PROCESS_STRAIGHT_THROUGH_BPMN)
  // public void testProcessActiveFailureForObject() throws Exception {
  // ProcessInstance processInstance =
  // runtimeService.startProcessInstanceByKey(TEST_PROCESS_STRAIGHT_THROUGH);
  // assertProcessActive(activitiRule, processInstance);
  // }
  //
  // @Test(expected = AssertionError.class)
  // @Deployment(resources = DIAGRAMS_TEST_PROCESS_STRAIGHT_THROUGH_BPMN)
  // public void testProcessActiveFailureForId() throws Exception {
  // ProcessInstance processInstance =
  // runtimeService.startProcessInstanceByKey(TEST_PROCESS_STRAIGHT_THROUGH);
  // assertProcessActive(activitiRule, processInstance.getId());
  // }

}