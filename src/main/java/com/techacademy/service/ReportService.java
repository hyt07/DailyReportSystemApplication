package com.techacademy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    // ログイン中の従業員で同じ日付の日報検索
    public List<Report> findByEmployeeAndReportDate(Employee employee, LocalDate reportDate) {
        return reportRepository.findByEmployeeAndReportDate(employee, reportDate);
    }

    // 日報1件検索
    public Report findById(Integer id) {
        Optional<Report> option = reportRepository.findById(id);
        // 取得できなかった場合ほnull
        Report report = option.orElse(null);

        return report;
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

    // 日報削除
    @Transactional
    public void delete(Integer id) {
        Report report = findById(id);
        LocalDateTime now = LocalDateTime.now();
        report.setUpdatedAt(now);
        report.setDeleteFlg(true);
    }

}
