package com.demo.mudang2.src.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminDao adminDao;
    private final AdminProvider adminProvider;

    @Autowired
    public AdminService(AdminDao adminDao, AdminProvider adminProvider) {
        this.adminDao = adminDao;
        this.adminProvider = adminProvider;
    }
}