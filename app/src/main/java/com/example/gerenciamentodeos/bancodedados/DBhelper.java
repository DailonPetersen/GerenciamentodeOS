package com.example.gerenciamentodeos.bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class DBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GerenciaOS.db";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // se nao tiver esse @NotNull, nao funciona
    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        db.execSQL( ScriptDDL.createTableOrdemServico() );
        db.execSQL( ScriptDDL.createTableCliente() );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
