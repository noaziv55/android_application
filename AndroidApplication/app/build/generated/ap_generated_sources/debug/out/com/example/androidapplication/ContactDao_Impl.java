package com.example.androidapplication;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.androidapplication.entities.Contact;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contact> __insertionAdapterOfContact;

  private final EntityInsertionAdapter<Contact> __insertionAdapterOfContact_1;

  private final EntityDeletionOrUpdateAdapter<Contact> __updateAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ContactDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Contact` (`id`,`ContactOfUser`,`name`,`profileImg`,`last`,`lastdate`,`server`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getContactOfUser() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContactOfUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.getProfileImg());
        if (value.getLast() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLast());
        }
        if (value.getLastdate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLastdate());
        }
        if (value.getServer() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getServer());
        }
      }
    };
    this.__insertionAdapterOfContact_1 = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Contact` (`id`,`ContactOfUser`,`name`,`profileImg`,`last`,`lastdate`,`server`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getContactOfUser() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContactOfUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.getProfileImg());
        if (value.getLast() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLast());
        }
        if (value.getLastdate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLastdate());
        }
        if (value.getServer() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getServer());
        }
      }
    };
    this.__updateAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Contact` SET `id` = ?,`ContactOfUser` = ?,`name` = ?,`profileImg` = ?,`last` = ?,`lastdate` = ?,`server` = ? WHERE `ContactOfUser` = ? AND `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getContactOfUser() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContactOfUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.getProfileImg());
        if (value.getLast() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLast());
        }
        if (value.getLastdate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLastdate());
        }
        if (value.getServer() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getServer());
        }
        if (value.getContactOfUser() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContactOfUser());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM contact WHERE ContactOfUser=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Contact> contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact_1.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContact.handleMultiple(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final String username) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    if (username == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, username);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Contact> index(final String username) {
    final String _sql = "SELECT * FROM contact WHERE ContactOfUser = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfContactOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "ContactOfUser");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfProfileImg = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImg");
      final int _cursorIndexOfLast = CursorUtil.getColumnIndexOrThrow(_cursor, "last");
      final int _cursorIndexOfLastdate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastdate");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contact _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpContactOfUser;
        if (_cursor.isNull(_cursorIndexOfContactOfUser)) {
          _tmpContactOfUser = null;
        } else {
          _tmpContactOfUser = _cursor.getString(_cursorIndexOfContactOfUser);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpProfileImg;
        _tmpProfileImg = _cursor.getInt(_cursorIndexOfProfileImg);
        final String _tmpLast;
        if (_cursor.isNull(_cursorIndexOfLast)) {
          _tmpLast = null;
        } else {
          _tmpLast = _cursor.getString(_cursorIndexOfLast);
        }
        final String _tmpLastdate;
        if (_cursor.isNull(_cursorIndexOfLastdate)) {
          _tmpLastdate = null;
        } else {
          _tmpLastdate = _cursor.getString(_cursorIndexOfLastdate);
        }
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _item = new Contact(_tmpContactOfUser,_tmpId,_tmpName,_tmpProfileImg,_tmpLast,_tmpLastdate,_tmpServer);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Contact get(final String contactName) {
    final String _sql = "SELECT * FROM contact WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (contactName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, contactName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfContactOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "ContactOfUser");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfProfileImg = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImg");
      final int _cursorIndexOfLast = CursorUtil.getColumnIndexOrThrow(_cursor, "last");
      final int _cursorIndexOfLastdate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastdate");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final Contact _result;
      if(_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpContactOfUser;
        if (_cursor.isNull(_cursorIndexOfContactOfUser)) {
          _tmpContactOfUser = null;
        } else {
          _tmpContactOfUser = _cursor.getString(_cursorIndexOfContactOfUser);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpProfileImg;
        _tmpProfileImg = _cursor.getInt(_cursorIndexOfProfileImg);
        final String _tmpLast;
        if (_cursor.isNull(_cursorIndexOfLast)) {
          _tmpLast = null;
        } else {
          _tmpLast = _cursor.getString(_cursorIndexOfLast);
        }
        final String _tmpLastdate;
        if (_cursor.isNull(_cursorIndexOfLastdate)) {
          _tmpLastdate = null;
        } else {
          _tmpLastdate = _cursor.getString(_cursorIndexOfLastdate);
        }
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _result = new Contact(_tmpContactOfUser,_tmpId,_tmpName,_tmpProfileImg,_tmpLast,_tmpLastdate,_tmpServer);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
