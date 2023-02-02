package com.mdgz.dam.labdam2022.data.datasource.room;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Converters {
    @TypeConverter
    public static Long fromDate(Date value) {
        return (value == null ? null : value.toInstant().toEpochMilli());
    }

    @TypeConverter
    public static Date dateFromLong(Long value) {
        return (value == null ? null : Date.from(Instant.ofEpochMilli(value)));
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
