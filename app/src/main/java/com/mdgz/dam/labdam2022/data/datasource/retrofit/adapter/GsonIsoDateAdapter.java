package com.mdgz.dam.labdam2022.data.datasource.retrofit.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 *  Gson JsonSerializer + JsonDeserializer that can handle ISO 8601 translation into Date objects.
 */
public final class GsonIsoDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    public GsonIsoDateAdapter() {
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(src.toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.plusSeconds(1);

        String dateFormatAsString = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
        return new JsonPrimitive(dateFormatAsString);
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date date = deserializeToDate(json);
        if (typeOfT == Date.class) {
            return date;
        } else if (typeOfT == Timestamp.class) {
            return new Timestamp(date.getTime());
        } else if (typeOfT == java.sql.Date.class) {
            return new java.sql.Date(date.getTime());
        } else {
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + typeOfT);
        }
    }

    private Date deserializeToDate(JsonElement json) {
        LocalDateTime localDateTime = LocalDateTime.parse(json.getAsString());
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}