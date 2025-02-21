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
package org.operaton.bpm.engine.rest;

import org.operaton.bpm.engine.rest.dto.CountResultDto;
import org.operaton.bpm.engine.rest.dto.runtime.CaseExecutionDto;
import org.operaton.bpm.engine.rest.dto.runtime.CaseExecutionQueryDto;
import org.operaton.bpm.engine.rest.sub.runtime.CaseExecutionResource;
import org.operaton.bpm.engine.runtime.CaseExecutionQuery;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;

/**
 *
 * @author Roman Smirnov
 *
 */
@Produces(MediaType.APPLICATION_JSON)
public interface CaseExecutionRestService {

  public static final String PATH = "/case-execution";

  @Path("/{id}")
  CaseExecutionResource getCaseExecution(@PathParam("id") String caseExecutionId);

  /**
   * Exposes the {@link CaseExecutionQuery} interface as a REST service.
   *
   * @param uriInfo
   * @param firstResult
   * @param maxResults
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<CaseExecutionDto> getCaseExecutions(@Context UriInfo uriInfo,
      @QueryParam("firstResult") Integer firstResult,
      @QueryParam("maxResults") Integer maxResults);

  /**
   * Expects the same parameters as
   * {@link CaseExecutionRestService#getCaseExecutions(UriInfo, Integer, Integer)} (as a JSON message body)
   * and allows for any number of variable checks.
   *
   * @param query
   * @param firstResult
   * @param maxResults
   * @return
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  List<CaseExecutionDto> queryCaseExecutions(CaseExecutionQueryDto query,
      @QueryParam("firstResult") Integer firstResult,
      @QueryParam("maxResults") Integer maxResults);

  @GET
  @Path("/count")
  @Produces(MediaType.APPLICATION_JSON)
  CountResultDto getCaseExecutionsCount(@Context UriInfo uriInfo);

  @POST
  @Path("/count")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  CountResultDto queryCaseExecutionsCount(CaseExecutionQueryDto query);

}
