package com.guestlogix.infrastructure;

import java.util.List;

public interface CsvLoader {

    <T> List<T> loadObjectList(Class<T> type, String fileName);
}
