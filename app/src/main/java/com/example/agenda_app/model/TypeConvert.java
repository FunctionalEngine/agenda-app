package com.example.agenda_app.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TypeConvert {
    private static final String TAG="StateConvert";
    @TypeConverter
    public static State toState(String value){
        if (value.equals("COMPLETE")){
            return State.COMPLETE;
        } else if(value.equals("INCOMPLETE")){
            return State.INCOMPLETE;
        }else {
            return State.DOING;
        }
    }

    @TypeConverter
    public static String fromState(State value){
        if (value == State.COMPLETE){
            return "COMPLETE";
        } else if(value == State.INCOMPLETE){
            return "INCOMPLETE";
        }else {
            return "DOING";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static long fromLocalDateTime(LocalDateTime dateTime){
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDateTime toLocalDateTime(long epoch){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch),ZoneId.systemDefault());
    }
}
