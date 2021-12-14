package com.bakery.bakery.util;

import java.util.List;

public interface ConvertImpl<T, TRQ, TRS> {
    T convertToEntity(TRQ request);
    TRS convertToResponse(T entity);
    List<TRS> convertToListResponse(List<T> entities);
}
