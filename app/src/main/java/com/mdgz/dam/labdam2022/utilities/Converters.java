package com.mdgz.dam.labdam2022.utilities;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.util.UUID;

public class Converters {
    @TypeConverter
    public static Long fromInstant(Instant value) {
        return (value == null ? null : value.toEpochMilli());
    }

    @TypeConverter
    public static Instant dateToInstant(Long value) {
        return (value == null ? null : Instant.ofEpochMilli(value));
    }

    @TypeConverter
    public static String fromUUID(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public static UUID uuidFromString(String string) {
        return UUID.fromString(string);
    }
}
