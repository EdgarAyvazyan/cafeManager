package com.sfl.cafemanager.rest.controller;

import com.sfl.cafemanager.configuration.security.jwt.domain.UserPrincipal;
import com.sfl.cafemanager.rest.model.Table;
import com.sfl.cafemanager.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tables")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    /**
     * @return Created table with id.
     */
    @PostMapping
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_MANAGER)")
    public Table createTable() {
        return tableService.createTable();
    }

    /**
     * @param tableId  which table.
     * @param waiterId whom to be assigned.
     * @return updated Table.
     */
    @PutMapping("/{tableId}/waiter/{waiterId}")
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_MANAGER)")
    public Table assignWaiterToTable(@PathVariable Long tableId, @PathVariable Long waiterId) {
        return tableService.assignWaiter(tableId, waiterId);
    }

    /**
     * @return All tables from DB.
     */
    @GetMapping
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_MANAGER)")
    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    /**
     * @param user to get current user id.
     * @return All tables from DB for current waiter.
     */
    @GetMapping("/my")
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_WAITER)")
    public List<Table> getAllTablesAssignedToWaiter(@AuthenticationPrincipal UserPrincipal user) {
        return tableService.getAllTablesAssignedToWaiter(user.getId());
    }
}