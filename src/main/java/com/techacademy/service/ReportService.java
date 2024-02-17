package com.techacademy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.constants.ErrorKinds;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // 管理者用日報一覧表示処理
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    // 一般ユーザの日報一覧表示処理
    public List<Report> findByEmployee(Employee employee) {
        return reportRepository.findByEmployee(employee);
    }

 // ログイン中の従業員で同じ日付の日報取得
    public List<Report> findByEmployeeAndReportDate(Employee employee, LocalDate reportDate) {
        return reportRepository.findByEmployeeAndReportDate(employee, reportDate);
    }

    // 日報保存
    @Transactional
    public ErrorKinds save(Report report, Employee employee) {

        report.setEmployee(employee);
        LocalDateTime now = LocalDateTime.now();
        report.setCreatedAt(now);
        report.setUpdatedAt(now);

        reportRepository.save(report);
        return ErrorKinds.SUCCESS;
    }

}
