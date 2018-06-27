package com.masteringselenium.accessKeys;

public enum OperatingSystem {

    WINDOWS("windows"),
    OSX("mac"),
    LINUX("linux");

    private String operatingSystemName;

    OperatingSystem(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    String getOperatingSystemType() {
        return operatingSystemName;
    }

    public static OperatingSystem getCurrentOperatingSystem() {
        String currentOperatingSystemName = System.getProperties().getProperty("os.name");
        for (OperatingSystem operatingSystemName : values()) {
            if (currentOperatingSystemName.toLowerCase().contains(operatingSystemName.getOperatingSystemType())) {
                return operatingSystemName;
            }
        }

        throw new IllegalArgumentException("Unrecognised operating system name '" + currentOperatingSystemName + "'");
    }
}