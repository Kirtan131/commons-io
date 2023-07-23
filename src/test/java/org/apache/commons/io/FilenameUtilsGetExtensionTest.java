package org.apache.commons.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FilenameUtilsGetExtensionTest {

    @Test
    public void testGetExtension_NullInput() {
        assertNull(FilenameUtils.getExtension(null));
    }

    @Test
    public void testGetExtension_NoExtension() {
        assertEquals("", FilenameUtils.getExtension("README"));
        assertEquals("", FilenameUtils.getExtension("a.b/c"));
        assertEquals("", FilenameUtils.getExtension("a/b/c"));
        assertEquals("", FilenameUtils.getExtension("a.b\\c"));
        assertEquals("", FilenameUtils.getExtension("a\\b\\c"));
        assertEquals("", FilenameUtils.getExtension("C:\\temp\\foo.bar\\README"));
    }

    @Test
    public void testGetExtension_WithExtension() {
        assertEquals("ext", FilenameUtils.getExtension("file.ext"));
        assertEquals("com", FilenameUtils.getExtension("domain.dot.com"));
        assertEquals("jpeg", FilenameUtils.getExtension("image.jpeg"));
        assertEquals("txt", FilenameUtils.getExtension("a.b/c.txt"));
        assertEquals("txt", FilenameUtils.getExtension("a.b\\c.txt"));
        assertEquals("ext", FilenameUtils.getExtension("../filename.ext"));
    }

    @Test
    public void testGetExtension_ForbiddenCharacter() {
        if (FilenameUtils.isSystemWindows()) {
            // Special case handling for NTFS ADS names
            try {
                FilenameUtils.getExtension("foo.exe:bar.txt");
                throw new AssertionError("Expected Exception");
            } catch (final IllegalArgumentException e) {
                assertEquals("NTFS ADS separator (':') in file name is forbidden.", e.getMessage());
            }
        } else {
            // Upwards compatibility:
            assertEquals("txt", FilenameUtils.getExtension("foo.exe:bar.txt"));
        }
    }
}
