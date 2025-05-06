package com.example.DevCollege.entity;

public enum Qualification {
    BE("B.E"),
    BTECH("B.Tech"),
    DIPLOMA("Diploma"),
    ME("M.E"),
    MTECH("M.Tech"),
    MPHIL("M.Phil."),
    MS("MS"),
    BBA("BBA"),
    BCOM("BCom"),
    BSC("BSc"),
    MSC("MSc"),
    BCA("BCA"),
    MCA("MCA"),
    LLB("LLB"),
    MBBS("MBBS"),
    MBA("MBA");

    private final String qualificationName;

    // Constructor to assign qualification name
    Qualification(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    // Getter method to retrieve the qualification name
    public String getQualificationName() {
        return qualificationName;
    }

    //method to check if a string matches any qualification
    public static boolean isValidQualification(String tag) {
        for (Qualification qualification : values()) {
            if (qualification.getQualificationName().equalsIgnoreCase(tag)) {
                return true;
            }
        }
        return false;
    }
}
