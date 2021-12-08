package com.javaseekersback.service;

import com.javaseekersback.api.model.enums.ArtifactType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ArtifactTypeMatcherTest {

    private final ArtifactTypeMatcher matcher = new ArtifactTypeMatcher();

    @Parameterized.Parameter
    public String artifactPath;

    @Parameterized.Parameter(1)
    public ArtifactType expectedType;

    @Parameterized.Parameters(name = "artifactPath: {0}, Expected Type: {1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"", ArtifactType.UNDEFINED},
                {"newType/some.xml", ArtifactType.UNDEFINED},
                {"modules/26/dashboards/EMPTYSystem.json", ArtifactType.DASHBOARD},
                {"modules/26/decisionTrees/AsphaltTree.json", ArtifactType.DECISION_TREE},
                {"foreignkey/fk.xml", ArtifactType.FOREIGN_KEY},
                {"function/fk.xml", ArtifactType.FUNCTION},
                {"groovy/CUSTOM_1000562_.groovy", ArtifactType.GROOVY},
                {"modules/3/importConfigs/Import_Small_Culvert_Buffer.json", ArtifactType.IMPORT_CONFIG},
                {"javascript/CUSTOM_1000403_.js", ArtifactType.JAVA_SCRIPT},
                {"jobs/job.json", ArtifactType.JOB},
                {"modules/26/layers/Root/Layers/Roadway Segment/boundLayer.json", ArtifactType.LAYER},
                {"modules/26/maps/Root/Maps/Pavement Performance/Current Performance.json", ArtifactType.MAP},
                {"modules/26/menus/module.json", ArtifactType.MENU},
                {"mview/NET_EXT_BEFORE_CONCUR.xml", ArtifactType.M_VIEW},
                {"modules/26/models/upperTree.json", ArtifactType.MODEL},
                {"procedure/DS_EDITABLE_COLUMNS.xml", ArtifactType.PROCEDURE},
                {"modules/popups/3_sign_support_inventory_add.json", ArtifactType.POPUP_WINDOW},
                {"report/Historical_Network_Performance_Trend.json", ArtifactType.REPORT},
                {"modules/26/securityRoles/Administrator.json", ArtifactType.SECURITY_ROLE},
                {"sequence/S_SETUP_SIGN_SUP_USE_TYPE.xml", ArtifactType.SEQUENCE},
                {"modules/3/setupData/SETUP_SIGN_PROGRAM.xml", ArtifactType.SETUP_DATA},
                {"table/table.xml", ArtifactType.TABLE},
                {"modules/26/treatments/pmsTreatments.json", ArtifactType.TREATMENTS},
                {"type/SESSION_VARS.xml", ArtifactType.TYPE},
                {"trigger/COMMENTS_DET_TRG.xml", ArtifactType.TRIGGER},
                {"modules/view/TUNNEL_ASSETS_VW.xml", ArtifactType.VIEW},
                {"modules/2/windows/dashboard.json", ArtifactType.WINDOW}
        });
    }

    @Test
    public void matchByPath() {
        assertEquals(expectedType, matcher.matchByPath(artifactPath));
    }
}