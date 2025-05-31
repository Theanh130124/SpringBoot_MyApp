package com.theanh1301.myapp.controller;


import com.theanh1301.myapp.dto.request.PermissionRequest;
import com.theanh1301.myapp.dto.response.PermissionResponse;
import com.theanh1301.myapp.mapper.PermissionMapper;
import com.theanh1301.myapp.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ApiPermissionController {

    PermissionService permissionService;
    PermissionMapper permissionMapper;



    @GetMapping
    public List<PermissionResponse> getAllPermissions() {
       return permissionService.getAllPermission();
    }



    @PostMapping
    public PermissionResponse addPermission(@RequestBody PermissionRequest request){
        return permissionService.createPermission(request);
    }


    @DeleteMapping("/{permissionId}")
    public String deletePermission(@PathVariable String permissionId){
         permissionService.deletePermission(permissionId);
         return " Xóa thành công permission:" +permissionId;
    }

}
