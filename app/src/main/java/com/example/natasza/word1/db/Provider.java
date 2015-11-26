package com.example.natasza.word1.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Natasza on 2015-10-25.
 */
public class Provider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int WORD = 101;
    private static final int STATUS = 202;
    private static final int CATEGORY = 303;

    private DBHelper mDBHelper;


    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String autority = Contract.AUTHORITY;

        matcher.addURI(autority, Contract.PATH_WORD, WORD);
        matcher.addURI(autority, Contract.PATH_CATEGORY, CATEGORY);
        matcher.addURI(autority, Contract.PATH_STATUS, STATUS);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor result;
        SQLiteDatabase db;
        db = mDBHelper.getReadableDatabase();

        switch (sUriMatcher.match(uri)){

            case WORD:
                result = db.query(
                    Contract.Word.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
            break;

            case CATEGORY:
                result = db.query(
                        Contract.Category.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case STATUS:
                result = db.query(
                        Contract.Status.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            default:
                throw new
                        IllegalStateException( "Unknown Uri " + uri);
        }

        result.setNo
        return null;
    }

    @Override
    public String getType(Uri uri) {

        switch (sUriMatcher.match(uri)){

            case WORD:
                return Contract.Word.CONTENT_TYPE;
            case CATEGORY:
                return Contract.Category.CONTENT_TYPE;
            case STATUS:
                return Contract.Status.CONTENT_TYPE;

            default:
                throw new IllegalStateException("Unknow Uri " + uri );
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
