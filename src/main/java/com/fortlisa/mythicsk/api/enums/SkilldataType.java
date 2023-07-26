package com.fortlisa.mythicsk.api.enums;

import ch.njol.util.StringUtils;
import javax.annotation.Nullable;
import java.util.*;

public enum SkilldataType {
    MetadataTypeInt("int", Integer.class),
    MetadataTypeFloat("float", Float.class),
    MetadataTypeString("string", String.class);

    final String name;
    final Class<?> typeClass;
    SkilldataType(String name, Class<?> type) {
        this.name = name;
        this.typeClass = type;
    }

    private static final Map<String, SkilldataType> BY_NAME = new HashMap<String, SkilldataType>();
    static {
        for (SkilldataType type : SkilldataType.values()) {
            BY_NAME.put(type.name, type);
        }
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getTypeClass() {
        return this.typeClass;
    }

    public static String getNames() {
        List<String> names = new ArrayList<>(BY_NAME.keySet());
        Collections.sort(names);
        return StringUtils.join(names, ", ");
    }

    @Nullable
    public static SkilldataType fromName(String name) {
        String s = name.toLowerCase(Locale.ROOT);
        if (BY_NAME.containsKey(s)) {
            return BY_NAME.get(s);
        }
        return null;
    }
}
