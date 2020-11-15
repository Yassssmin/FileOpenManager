package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    private String IntelliJIDEAProgram;
    private String AdobePhotoshopProgram;
    private String GoogleChromeProgram;

    private String projExtension;
    private String jpegExtension;
    private String psdExtension;
    private String htmlExtension;

    @BeforeEach
    void setUp() {
        IntelliJIDEAProgram = "IntelliJ IDEA";
        AdobePhotoshopProgram = "AdobePhotoshop";
        GoogleChromeProgram = "Google Chrome";

        projExtension = ".proj";
        jpegExtension = ".jpg";
        psdExtension = ".psd";
        htmlExtension = ".html";
    }

    @Nested
    class Empty {
        @Mock
        private Map<String, String> extensionsMap;
        private FileOpenManager manager;

        @BeforeEach
        void setUp() throws NoSuchFieldException {
            manager = new FileOpenManager();
            extensionsMap = new HashMap<>();

            FieldSetter.setField(manager, manager.getClass().getDeclaredField("extensionsMap"), extensionsMap);
        }

        @Test
        void shouldRegister() {
            manager.register(GoogleChromeProgram, htmlExtension);

            HashMap<String, String> expected = new HashMap<>();
            expected.put(htmlExtension, GoogleChromeProgram);

            assertEquals(expected, extensionsMap);
        }

        @Test
        void shouldGetProgramNameForExtension() {
            String actual = manager.getProgramNameForExtension(htmlExtension);

            assertEquals(null, actual);
        }

        @Test
        void shouldRemoveExtension() {
            manager.removeExtension(htmlExtension);
        }

        @Test
        void shouldGetRegisteredExtensions() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getRegisteredExtensions();

            assertIterableEquals(expected, actual);
        }

        @Test
        void shouldGetProgramNames() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getProgramNames();

            assertIterableEquals(expected, actual);
        }
    }

    @Nested
    class SingleItem {
        @Mock
        private Map<String, String> extensionsMap;
        private FileOpenManager manager;

        @BeforeEach
        void setUp() throws NoSuchFieldException {
            manager = new FileOpenManager();
            extensionsMap = new HashMap<>();

            FieldSetter.setField(manager, manager.getClass().getDeclaredField("extensionsMap"), extensionsMap);

            extensionsMap.put(htmlExtension, GoogleChromeProgram);
        }

        @Test
        void shouldRegister() {
            manager.register(IntelliJIDEAProgram, projExtension);

            HashMap<String, String> expected = new HashMap<>();
            expected.put(htmlExtension, GoogleChromeProgram);
            expected.put(projExtension, IntelliJIDEAProgram);

            assertEquals(expected, extensionsMap);
        }

        @Test
        void shouldGetProgramNameForExtension() {
            String expected = GoogleChromeProgram;
            String actual = manager.getProgramNameForExtension(htmlExtension);

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotGetProgramNameForInvalidExtension() {
            String expected = null;
            String actual = manager.getProgramNameForExtension(psdExtension);

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveExtension() {
            manager.removeExtension(htmlExtension);

            assertFalse(extensionsMap.containsKey(htmlExtension));
        }

        @Test
        void shouldGetRegisteredExtensions() {
            List<String> expected = new ArrayList<>();
            expected.add(htmlExtension);

            List<String> actual = manager.getRegisteredExtensions();

            assertIterableEquals(expected, actual);
        }

        @Test
        void shouldGetProgramNames() {
            List<String> expected = new ArrayList<>();
            expected.add(GoogleChromeProgram);

            List<String> actual = manager.getProgramNames();

            assertIterableEquals(expected, actual);
        }
    }

    @Nested
    class MultipleItems {
        @Mock
        private Map<String, String> extensionsMap;
        private FileOpenManager manager;

        @BeforeEach
        void setUp() throws NoSuchFieldException {
            manager = new FileOpenManager();
            extensionsMap = new HashMap<>();

            FieldSetter.setField(manager, manager.getClass().getDeclaredField("extensionsMap"), extensionsMap);

            extensionsMap.put(htmlExtension, GoogleChromeProgram);
            extensionsMap.put(psdExtension, AdobePhotoshopProgram);
            extensionsMap.put(jpegExtension, AdobePhotoshopProgram);
        }

        @Test
        void shouldRegister() {
            manager.register(IntelliJIDEAProgram, projExtension);

            HashMap<String, String> expected = new HashMap<>();
            expected.put(htmlExtension, GoogleChromeProgram);
            expected.put(psdExtension, AdobePhotoshopProgram);
            expected.put(jpegExtension, AdobePhotoshopProgram);
            expected.put(projExtension, IntelliJIDEAProgram);

            assertEquals(expected, extensionsMap);
        }

        @Test
        void shouldGetProgramNameForExtension() {
            String expected = GoogleChromeProgram;
            String actual = manager.getProgramNameForExtension(htmlExtension);

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotGetProgramNameForInvalidExtension() {
            String expected = null;
            String actual = manager.getProgramNameForExtension(projExtension);

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveExtension() {
            manager.removeExtension(htmlExtension);

            assertFalse(extensionsMap.containsKey(htmlExtension));
        }

        @Test
        void shouldGetRegisteredExtensions() {
            List<String> expected = new ArrayList<>();
            expected.add(htmlExtension);
            expected.add(jpegExtension);
            expected.add(psdExtension);

            List<String> actual = manager.getRegisteredExtensions();

            assertIterableEquals(expected, actual);
        }

        @Test
        void shouldGetProgramNames() {
            List<String> expected = new ArrayList<>();
            expected.add(AdobePhotoshopProgram);
            expected.add(GoogleChromeProgram);

            List<String> actual = manager.getProgramNames();

            assertIterableEquals(expected, actual);
        }
    }
}