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
        //metoda zmienja dane w fragmiecie
        result.setNotificationUri(getContext().getContentResolver(), uri);

        return result;
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

        SQLiteDatabase db;
        long insertId;
        Uri returnUri;

        switch (sUriMatcher.match(uri)){
            case WORD:
                db = mDBHelper.getWritableDatabase();
                insertId = db.insert(Contract.Word.TABLE_NAME, null, values);
                returnUri = Contract.Word.buildWordUri(insertId);

                break;

            case CATEGORY:
                db = mDBHelper.getWritableDatabase();
                insertId = db.insert(Contract.Category.TABLE_NAME, null, values);
                returnUri = Contract.Category.buildCategoryUri(insertId);

                break;

            case STATUS:
                db = mDBHelper.getWritableDatabase();
                insertId = db.insert(Contract.Status.TABLE_NAME, null, values);
                returnUri = Contract.Status.buildStatusUri(insertId);

                break;

            default:
                throw new IllegalStateException( "Unknown Uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db;
        int deleteRows; //liczba skasowanych wierszy

        switch (sUriMatcher.match(uri)){
            case WORD:
                db = mDBHelper.getWritableDatabase();
                deleteRows = db.delete(Contract.Word.TABLE_NAME, selection, selectionArgs);

                break;

            case CATEGORY:
                db = mDBHelper.getWritableDatabase();
                deleteRows = db.delete(Contract.Category.TABLE_NAME, selection, selectionArgs);

                break;

            case STATUS:
                db = mDBHelper.getWritableDatabase();
                deleteRows = db.delete(Contract.Status.TABLE_NAME, selection, selectionArgs);

                break;

            default:
                throw new IllegalStateException(" Unknown Uri "+ uri);
        }

        getContext().getContentResolver().notifyChange(uri, null, false);
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db;
        int updateRows; //liczba skasowanych wierszy

        switch (sUriMatcher.match(uri)){
            case WORD:
                db = mDBHelper.getWritableDatabase();
                updateRows = db.update(Contract.Word.TABLE_NAME, values, selection, selectionArgs);

                break;

            case CATEGORY:
                db = mDBHelper.getWritableDatabase();
                updateRows = db.update(Contract.Category.TABLE_NAME, values, selection, selectionArgs);

                break;

            case STATUS:
                db = mDBHelper.getWritableDatabase();
                updateRows = db.update(Contract.Status.TABLE_NAME, values, selection, selectionArgs);

                break;

            default:
                throw new IllegalStateException(" Unknown Uri "+ uri);
        }

        getContext().getContentResolver().notifyChange(uri, null, false);
        return updateRows;
    }
}
