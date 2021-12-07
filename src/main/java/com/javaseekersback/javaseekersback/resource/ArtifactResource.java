package com.javaseekersback.javaseekersback.resource;

import com.javaseekersback.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.javaseekersback.api.model.response.ControlArtifactsResponse;
import com.javaseekersback.javaseekersback.service.impl.ArtifactsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Slf4j
@Api(protocols = "http")
@Path("/services/artifact")
public class ArtifactResource {

    private final ArtifactsService artifactsService;

    @Inject
    public ArtifactResource(ArtifactsService artifactsService) {
        this.artifactsService = artifactsService;
    }

    @GET
    @Path("/artifactList/{client}/{module}/{controlName}")
    @Valid
    @ApiOperation(value = "Get Control File Artifacts", response = ControlArtifactsResponse.class)
    public ControlArtifactsResponse getArtifacts(@PathParam("client") String client, @PathParam("module") Integer module,
                                                 @PathParam("controlName") String controlName) {

        log.info("getArtifacts client={} module={} controlName={}", client, module, controlName);

        ControlArtifactRequest request = new ControlArtifactRequest(client, module, controlName);

        return artifactsService.get(request);
    }
}
