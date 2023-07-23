package org.apache.commons.io;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsGetPrefixTest {

    @Test
    public void testGetPrefix_NullInput() {
        assertNull(FilenameUtils.getPrefix(null));
        assertNull(FilenameUtils.getPrefix(":"));
        assertNull(FilenameUtils.getPrefix("1:\\a\\b\\c.txt"));
        assertNull(FilenameUtils.getPrefix("1:"));
        assertNull(FilenameUtils.getPrefix("1:a"));
        assertNull(FilenameUtils.getPrefix("\\\\\\a\\b\\c.txt"));
        assertNull(FilenameUtils.getPrefix("\\\\a"));
    }

    @Test
    public void testGetPrefix_EmptyInput() {
        assertEquals("", FilenameUtils.getPrefix(""));
        assertEquals("\\", FilenameUtils.getPrefix("\\"));
    }

    @Test
    public void testGetPrefix_Windows() {
        if (SystemUtils.IS_OS_WINDOWS) {
            assertEquals("C:", FilenameUtils.getPrefix("C:"));
        }
    }

    @Test
    public void testGetPrefix_OtherPlatforms() {
        if (SystemUtils.IS_OS_LINUX) {
            assertEquals("", FilenameUtils.getPrefix("C:"));
        }
    }

    @Test
    public void testGetPrefix_WithPrefix() {
        assertEquals("C:\\", FilenameUtils.getPrefix("C:\\"));
        assertEquals("//server/", FilenameUtils.getPrefix("//server/"));
        assertEquals("~/", FilenameUtils.getPrefix("~"));
        assertEquals("~/", FilenameUtils.getPrefix("~/"));
        assertEquals("~user/", FilenameUtils.getPrefix("~user"));
        assertEquals("~user/", FilenameUtils.getPrefix("~user/"));
    }

    @Test
    public void testGetPrefix_WindowsPaths() {
        assertEquals("", FilenameUtils.getPrefix("a\\b\\c.txt"));
        assertEquals("\\", FilenameUtils.getPrefix("\\a\\b\\c.txt"));
        assertEquals("C:\\", FilenameUtils.getPrefix("C:\\a\\b\\c.txt"));
        assertEquals("\\\\server\\", FilenameUtils.getPrefix("\\\\server\\a\\b\\c.txt"));
    }

    @Test
    public void testGetPrefix_UnixPaths() {
        assertEquals("", FilenameUtils.getPrefix("a/b/c.txt"));
        assertEquals("/", FilenameUtils.getPrefix("/a/b/c.txt"));
        assertEquals("C:/", FilenameUtils.getPrefix("C:/a/b/c.txt"));
        assertEquals("//server/", FilenameUtils.getPrefix("//server/a/b/c.txt"));
        assertEquals("~/", FilenameUtils.getPrefix("~/a/b/c.txt"));
        assertEquals("~user/", FilenameUtils.getPrefix("~user/a/b/c.txt"));
    }

    @Test
    public void testGetPrefix_UnixPathsStartingWithTilde() {
        assertEquals("", FilenameUtils.getPrefix("a\\b\\c.txt"));
        assertEquals("\\", FilenameUtils.getPrefix("\\a\\b\\c.txt"));
        assertEquals("~\\", FilenameUtils.getPrefix("~\\a\\b\\c.txt"));
        assertEquals("~user\\", FilenameUtils.getPrefix("~user\\a\\b\\c.txt"));
    }

    @Test
    public void testGetPrefix_ForbiddenCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getPrefix("~u\u0000ser\\a\\b\\c.txt"));
    }
}
