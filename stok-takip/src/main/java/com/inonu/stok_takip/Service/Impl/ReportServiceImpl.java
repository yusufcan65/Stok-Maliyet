package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Enum.ReportType;
import com.inonu.stok_takip.Repositoriy.ReportRepository;
import com.inonu.stok_takip.Service.MaterialExitService;
import com.inonu.stok_takip.Service.ReportService;
import com.inonu.stok_takip.Service.TicketSalesDetailService;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Response.ReportResponse;
import com.inonu.stok_takip.dto.Response.TicketSalesDetailResponse;
import com.inonu.stok_takip.entitiy.Report;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {


    private final ReportRepository reportRepository;
    private final TicketSalesDetailService ticketSalesDetailService;
    private final MaterialExitService materialExitService;

    public ReportServiceImpl(ReportRepository reportRepository,
                             TicketSalesDetailService ticketSalesDetailService,
                             MaterialExitService materialExitService) {
        this.reportRepository = reportRepository;
        this.ticketSalesDetailService = ticketSalesDetailService;
        this.materialExitService = materialExitService;
    }


    @Override
    public ReportResponse createReport(LocalDate date, ReportType reportType) {

        switch (reportType){
            case DAILY:
                return calculateDailyReport(date);
            case MONTHLY:
                return calculateMonthlyReport(date);
            case YEARLY:
                return calculateYearlyReport(date);
            default:
                throw new IllegalArgumentException("Geçersiz report type");
        }

    }

    @Override
    public List<ReportResponse> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return mapToResponseList(reports);
    }

    @Override
    public ReportResponse calculateDailyReport(LocalDate date){

        Integer totalPerson = materialExitService.numberMealsInDay(date);
        Integer ticketQuantity = ticketSalesDetailService.getTicketCountByDay(date); // girilen güne ait toplam fiş sayısını verir
        Double totalAmount = materialExitService.getNonCleaningMaterialExitsByDate(date);
        Double personCost = totalAmount / totalPerson;
        Double ticketCost = totalAmount / ticketQuantity;

        Report report = new Report();
        report.setReportType(ReportType.DAILY);// rapor tipi günlük veya aylık
        report.setTicketQuantity(ticketQuantity); // satılan fiş sayısı
        report.setReportCreateDate(LocalDate.now()); // raporun oluşturulma tarihi
        report.setTotalPersonQuantity(totalPerson); // yapılan yemek sayısı veya toplam mevcut
        report.setTotalMaterialPrice(totalAmount);  // toplam harcanan malzeme tutarı
        report.setTotalCleanPrice(0.0); // kullanılan temizlik malzemelerinin toplam tutarı  -- bu sadece aylık raporda yer alacak günlük bazda alındığında 0 olarak otomatik kayıt edilecek
        report.setAveragePersonCost(personCost);  //  mevcuda göre 1 porsiyon yemek maliyeti
        report.setAverageTicketCost(ticketCost); // SATILAN FİŞ SAYISINA GÖRE 1 PORSİYON YEMEK MALİYETİ

        Report savedReport = reportRepository.save(report);


        return mapToResponse(savedReport);

    }

    @Override
    public ReportResponse calculateMonthlyReport(LocalDate date){
        Integer totalPerson = materialExitService.numberMealsInMonth(date);
        Integer ticketCount = ticketSalesDetailService.getTicketCountByMonth(date);
        Double totalMaterialPrice = materialExitService.getMaterialsByMonthAndYear(date);
        Double personCost = totalMaterialPrice / totalPerson;
        Double ticketCost = totalMaterialPrice / ticketCount;


        Report report = new Report();
        report.setReportType(ReportType.MONTHLY);
        report.setTicketQuantity(ticketCount);
        report.setReportCreateDate(LocalDate.now());
        report.setTotalPersonQuantity(totalPerson);
        report.setTotalMaterialPrice(totalMaterialPrice);
        report.setTotalCleanPrice(0.0);
        report.setAveragePersonCost(personCost);
        report.setAverageTicketCost(ticketCost);

        Report savedReport = reportRepository.save(report);


        return mapToResponse(savedReport);
    }

    @Override
    public ReportResponse calculateYearlyReport(LocalDate date) {
        Integer totalPerson = materialExitService.numberMealsInYear(date);
        Integer ticketCount = ticketSalesDetailService.getTicketCountByYear(date);
        Double totalMaterialPrice = materialExitService.getMaterialsByYear(date);
        Double personCost = totalMaterialPrice / totalPerson;
        Double ticketCost = totalMaterialPrice / ticketCount;


        Report report = new Report();
        report.setReportType(ReportType.YEARLY);
        report.setTicketQuantity(ticketCount);
        report.setReportCreateDate(LocalDate.now());
        report.setTotalPersonQuantity(totalPerson);
        report.setTotalMaterialPrice(totalMaterialPrice);
        report.setTotalCleanPrice(0.0);
        report.setAveragePersonCost(personCost);
        report.setAverageTicketCost(ticketCost);

        Report savedReport = reportRepository.save(report);

        return mapToResponse(savedReport);
    }


    @Override
    public ReportResponse getReportByDate(LocalDate date,ReportType reportType){
        Report report = reportRepository.findByReportCreateDateAndReportType(date,reportType).orElseThrow(()->new RuntimeException("Report not found"));
        return mapToResponse(report);
    }

    private ReportResponse mapToResponse(Report report){
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setReportType(report.getReportType());
        response.setAveragePersonCost(report.getAveragePersonCost());
        response.setTicketQuantity(report.getTicketQuantity());
        response.setReportCreateDate(report.getReportCreateDate());
        response.setAverageTicketCost(report.getAverageTicketCost());
        response.setTotalCleanPrice(report.getTotalCleanPrice());
        response.setTotalPersonQuantity(report.getTotalPersonQuantity());
        response.setTotalMaterialPrice(report.getTotalMaterialPrice());
        response.setTotalTicketPrice(report.getTotalTicketPrice());


        return response;
    }

    private List<ReportResponse> mapToResponseList(List<Report> reports) {
        List<ReportResponse> responseList = reports.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }
}
