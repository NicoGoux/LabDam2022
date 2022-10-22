package com.mdgz.dam.labdam2022.utilities;

import androidx.room.TypeConverter;

import java.time.Instant;

public class Converters {
    @TypeConverter
    public static Long fromInstant(Instant value) {
        return (value == null ? null : value.toEpochMilli());
    }

    @TypeConverter
    public static Instant dateToInstant(Long value) {
        return (value == null ? null : Instant.ofEpochMilli(value));
    }
}
