package com.javaseekersback.service;

import com.javaseekersback.service.constants.PathConstants;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestCase {

    @Test
    public void test() {
        Path path = Path.of(PathConstants.CONFIGURER_ABSOLUTE_PATH, PathConstants.CONFIGURER_SCHEMA_PATH, "view/RPT_UNSCHEDULED_LEAVE_VW.xml");
        boolean exists = Files.exists(path);
        boolean regularFile = Files.isRegularFile(path);
        assertEquals(true, exists);
    }
}
