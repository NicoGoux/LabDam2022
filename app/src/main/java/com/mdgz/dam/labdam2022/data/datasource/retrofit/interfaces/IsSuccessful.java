package com.mdgz.dam.labdam2022.data.datasource.retrofit.interfaces;

import com.mdgz.dam.labdam2022.data.OnResult;

import java.io.IOException;

import retrofit2.Response;

public interface IsSuccessful<T> {
    void isSuccessful(final Response<T> response) throws IOException;
}
