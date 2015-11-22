package com.example.natasza.word1.db;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import com.example.natasza.word1.BuildConfig;



/**
 * Created by Natasza on 2015-10-25.
 ***********************************
 *
 * A contract class defines constants that help applications work with the content URIs, column names,
 * intent actions, and other features of a content provider. Contract classes are not included
 * automatically with a provider; the provider's developer has to define them and then make them
 * available to other developers.*/

public final class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Contract() {}

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_WORD = "word";
    public static final String PATH_CATEGORY = "category";
    public static final String PATH_STATUS = "status";


    public static final class Word implements BaseColumns {

        //sciezka do ENTRY
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WORD).build();

        //sciezka do ogolnej DB  w apce
        public static final String CONTENT_TYPE = "vnd.android.cursor.der/" + AUTHORITY + "/" + PATH_WORD;

        public static final String TABLE_NAME = "word";

        //_ID zawsze. z tej zmiennej korzysta android

        public static final String _ID = "_id";
        public static final String ID_WORDS = "id_words";
        public static final String TRANSLATION = "translation";
        public static final String ID_KATEGORY = "id_kategory";
        public static final String ID_STATUS = "id_status";


        //budowanie URI dla tabeli WORD
        public static Uri buildDisciplinesUri (long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class Kategory implements BaseColumns {

        public  static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORY).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.der/" + AUTHORITY + "/" + PATH_CATEGORY;
        public static final String TABLE_NAME = "kategory";
        public static final String _ID = "_id";
        public static final String ID_KATEGORY = "id_kategory";
        public static final String KATEGORY = "kategory";

    }



}
