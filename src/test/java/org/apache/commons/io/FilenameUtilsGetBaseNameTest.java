package org.apache.commons.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilenameUtilsGetBaseNameTest {

    @Test
    public void testGetBaseName_NullInput() {
        assertNull(FilenameUtils.getBaseName(null));
    }

    @Test
    public void testGetBaseName_NoSeparator() {
        assertEquals("noseperator", FilenameUtils.getBaseName("noseperator.inthispath"));
    }

    @Test
    public void testGetBaseName_WithSeparator() {
        assertEquals("c", FilenameUtils.getBaseName("a/b/c.txt"));
        assertEquals("c", FilenameUtils.getBaseName("a/b/c"));
        assertEquals("", FilenameUtils.getBaseName("a/b/c/"));
        assertEquals("c", FilenameUtils.getBaseName("a\\b\\c"));
        assertEquals("file.txt", FilenameUtils.getBaseName("file.txt.bak"));
    }

    @Test
    public void testGetBaseName_WithNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getBaseName("fil\u0000e.txt.bak"));
    }
}
