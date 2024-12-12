/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
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
package org.operaton.bpm.engine.test.junit5.registerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.operaton.bpm.engine.RuntimeService;
import org.operaton.bpm.engine.TaskService;
import org.operaton.bpm.engine.task.Task;
import org.operaton.bpm.engine.test.Deployment;
import org.operaton.bpm.engine.test.junit5.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

class RegisterProcessEngineExtensionTest {

  @RegisterExtension
  ProcessEngineExtension extension = ProcessEngineExtension.builder().build();

  @Test
  @Deployment
  void registeredExtensionUsageExample() {
    RuntimeService runtimeService = extension.getProcessEngine()
        .getRuntimeService();
    runtimeService.startProcessInstanceByKey("registeredExtensionUsage");

    TaskService taskService = extension
        .getProcessEngine()
        .getTaskService();

    Task task = taskService
        .createTaskQuery()
        .singleResult();
    assertEquals("Test something", task.getName());
    taskService.complete(task.getId());
    assertEquals(0, runtimeService.createProcessInstanceQuery().list().size());
  }

}
