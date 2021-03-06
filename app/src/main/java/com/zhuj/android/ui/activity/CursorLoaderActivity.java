package com.zhuj.android.ui.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;


public class CursorLoaderActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoaderManager.getInstance(this).initLoader(0, null, this);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                this,   // Parent activity context
                Uri.EMPTY,        // Table to query
                new String[]{"mProjection"},     // Projection to return
                null,            // No selection clause
                null,            // No selection arguments
                null);             // Default sort order
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
