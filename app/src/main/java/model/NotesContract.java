package model;

import android.provider.BaseColumns;

public class NotesContract {

    public static abstract class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME ="Notes";

        public static final String ID = "id";
        public static final String DURATION = "duration";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String SECONDS = "seconds";
        public static final String STATE = "state";
        public static final String PRIORITY = "priority";
        public static final String GRP = "name_group";
        public static final String DES = "description";
    }
}
