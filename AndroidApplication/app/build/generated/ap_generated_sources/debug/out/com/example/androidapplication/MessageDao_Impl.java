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
import com.example.androidapplication.entities.Message;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage_1;

  private final EntityDeletionOrUpdateAdapter<Message> __deletionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __updateAdapterOfMessage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MessageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message` (`id`,`to`,`from`,`content`,`created`,`sent`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getTo() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTo());
        }
        if (value.getFrom() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFrom());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        final int _tmp = value.isSent() ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__insertionAdapterOfMessage_1 = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Message` (`id`,`to`,`from`,`content`,`created`,`sent`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getTo() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTo());
        }
        if (value.getFrom() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFrom());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        final int _tmp = value.isSent() ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Message` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Message` SET `id` = ?,`to` = ?,`from` = ?,`content` = ?,`created` = ?,`sent` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getTo() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTo());
        }
        if (value.getFrom() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFrom());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        final int _tmp = value.isSent() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM message WHERE ((`from`=? AND `to`=?) OR (`from`=? AND `to`=? ))";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Message> messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage_1.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMessage.handleMultiple(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMessage.handleMultiple(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final String from, final String to) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    if (from == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, from);
    }
    _argIndex = 2;
    if (to == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, to);
    }
    _argIndex = 3;
    if (from == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, from);
    }
    _argIndex = 4;
    if (to == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, to);
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
  public List<Message> index(final String from, final String to) {
    final String _sql = "SELECT * FROM message WHERE ((`from`=? AND `to`=?) OR (`from`=? AND `to`=? ))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (from == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, from);
    }
    _argIndex = 2;
    if (to == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, to);
    }
    _argIndex = 3;
    if (to == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, to);
    }
    _argIndex = 4;
    if (from == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, from);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
      final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTo;
        if (_cursor.isNull(_cursorIndexOfTo)) {
          _tmpTo = null;
        } else {
          _tmpTo = _cursor.getString(_cursorIndexOfTo);
        }
        final String _tmpFrom;
        if (_cursor.isNull(_cursorIndexOfFrom)) {
          _tmpFrom = null;
        } else {
          _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        _item = new Message(_tmpId,_tmpTo,_tmpFrom,_tmpContent,_tmpCreated,_tmpSent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getAllData() {
    final String _sql = "SELECT * FROM message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
      final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTo;
        if (_cursor.isNull(_cursorIndexOfTo)) {
          _tmpTo = null;
        } else {
          _tmpTo = _cursor.getString(_cursorIndexOfTo);
        }
        final String _tmpFrom;
        if (_cursor.isNull(_cursorIndexOfFrom)) {
          _tmpFrom = null;
        } else {
          _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        _item = new Message(_tmpId,_tmpTo,_tmpFrom,_tmpContent,_tmpCreated,_tmpSent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Message get(final int id) {
    final String _sql = "SELECT * FROM message WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
      final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final Message _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTo;
        if (_cursor.isNull(_cursorIndexOfTo)) {
          _tmpTo = null;
        } else {
          _tmpTo = _cursor.getString(_cursorIndexOfTo);
        }
        final String _tmpFrom;
        if (_cursor.isNull(_cursorIndexOfFrom)) {
          _tmpFrom = null;
        } else {
          _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        _result = new Message(_tmpId,_tmpTo,_tmpFrom,_tmpContent,_tmpCreated,_tmpSent);
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
