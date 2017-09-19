package com.chenchen.factory.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEARCH".
*/
public class SearchDao extends AbstractDao<Search, Long> {

    public static final String TABLENAME = "SEARCH";

    /**
     * Properties of entity Search.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Timastamp = new Property(2, long.class, "timastamp", false, "TIMASTAMP");
    };


    public SearchDao(DaoConfig config) {
        super(config);
    }
    
    public SearchDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"CONTENT\" TEXT," + // 1: content
                "\"TIMASTAMP\" INTEGER NOT NULL );"); // 2: timastamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Search entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindLong(3, entity.getTimastamp());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Search entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindLong(3, entity.getTimastamp());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Search readEntity(Cursor cursor, int offset) {
        Search entity = new Search( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content
            cursor.getLong(offset + 2) // timastamp
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Search entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTimastamp(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Search entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Search entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}