/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package https.unsecured.api;



import https.CertificateManager;
import https.SignCertResponse;
import https.SignMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/certs")
public class CertificateSign {
  private static Logger LOG = LoggerFactory.getLogger(CertificateSign.class);
  private static CertificateManager certMan;

  public static void init(CertificateManager instance) {
    certMan = instance;
  }

  /**
   * Signs agent certificate
   * @response.representation.200.doc This API is invoked by Ambari agent running
   *  on a cluster to register with the server.
   * @response.representation.200.mediaType application/json
   * @response.representation.406.doc Error in register message format
   * @response.representation.408.doc Request Timed out
   * @param message Register message
   * @throws Exception
   */
  @Path("{hostName}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public SignCertResponse signAgentCrt(@PathParam("hostName") String hostname,
                                       SignMessage message, @Context HttpServletRequest req) {
    return certMan.signAgentCrt(hostname, message.getCsr(), message.getPassphrase());
  }
}
