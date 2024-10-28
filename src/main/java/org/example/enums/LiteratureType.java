package org.example.enums;

import java.util.*;
import java.util.stream.Stream;

public enum LiteratureType {
    PICTURE_BOOKS("BI", 3f, 1.5f),
    COMICS("TE", 3f, 1.5f),
    LYRICS("LYRIK", 6f, 3f),
    FICTION("SKØN", 1.7f, 0.85f),
    NONFICTION("FAG", 1f, 0.5f);

    public final String type;
    public final float pointsPerPage;
    public final float pointsPerMinute;
    private static final Map<String, LiteratureType> BY_TYPE = new HashMap<>();

    static {
        for (LiteratureType lt : values()) {
            BY_TYPE.put(lt.type, lt);
        }
    }

    LiteratureType(String type, float pointsPerPage, float pointsPerMinute) {
        this.type = type;
        this.pointsPerPage = pointsPerPage;
        this.pointsPerMinute = pointsPerMinute;
    }

    public String getType() {
        return type;
    }

    public static float valueOfPointsPerPage(String type) {
        return BY_TYPE.get(type).pointsPerPage;
    }

    public static float valueOfPointsPerMinute(String type) {
        return BY_TYPE.get(type).pointsPerMinute;
    }

    public static List<String> typesAsList() {
        return Stream.of(LiteratureType.values()).map(LiteratureType::getType).toList();
    }
}
