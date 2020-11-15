package ru.netology.manager;

import java.util.*;
import java.util.stream.Collectors;

public class FileOpenManager {
    private Map<String, String> extensionsMap = new HashMap<>();

    public void register(String programName, String extension) {
        extension = extension.toLowerCase();

        extensionsMap.put(extension, programName);
    }

    public String getProgramNameForExtension(String extension) {
        extension = extension.toLowerCase();

        if (extensionsMap.containsKey(extension)) {
            return extensionsMap.get(extension);
        }

        return null;
    }

    public void removeExtension(String extension) {
        extension = extension.toLowerCase();

        extensionsMap.remove(extension);
    }

    public List<String> getRegisteredExtensions() {
        return extensionsMap.keySet()
                .stream()
                .sorted()
                .collect(Collectors.<String>toList());
    }

    public List<String> getProgramNames() {
        Set<String> programNamesSet = new HashSet<String>(extensionsMap.values());

        return programNamesSet
                .stream()
                .sorted()
                .collect(Collectors.<String>toList());
    }
}
