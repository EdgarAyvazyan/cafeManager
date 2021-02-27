package com.sfl.cafemanager.service;


import com.sfl.cafemanager.rest.model.Table;

import java.util.List;

public interface TableService {

    Table createTable();

    Table assignWaiter(Long tableId, Long waiterId);

    List<Table> getAllTables();

    List<Table> getAllTablesAssignedToWaiter(Long waiterId);
}
