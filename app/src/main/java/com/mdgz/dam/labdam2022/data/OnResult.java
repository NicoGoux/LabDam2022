package com.mdgz.dam.labdam2022.data;

/**
 * Interfaz genérica para respuestas asíncronas.
 * Asegurarse de volver al ui thread antes de tocar las vistas.
 * */
public interface OnResult<T> {
    void onSuccess(final T result);

    void onError(final Throwable exception);
}
