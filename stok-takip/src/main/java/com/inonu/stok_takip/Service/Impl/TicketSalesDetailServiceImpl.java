package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.TicketSalesDetailRepository;
import com.inonu.stok_takip.Service.TicketSalesDetailService;
import com.inonu.stok_takip.Service.TicketTypeService;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.TicketSalesDetailCreateRequest;
import com.inonu.stok_takip.dto.Response.TicketSalesDetailResponse;
import com.inonu.stok_takip.entitiy.TicketSalesDetail;
import com.inonu.stok_takip.entitiy.TicketType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketSalesDetailServiceImpl implements TicketSalesDetailService {

    private final TicketSalesDetailRepository ticketSalesDetailRepository;
    private final TicketTypeService ticketTypeService;

    public TicketSalesDetailServiceImpl(TicketSalesDetailRepository ticketSalesDetailRepository,
                                        TicketTypeService ticketTypeService) {
        this.ticketSalesDetailRepository = ticketSalesDetailRepository;
        this.ticketTypeService = ticketTypeService;
    }


    @Override
    public List<TicketSalesDetailResponse> getAll() {
        List<TicketSalesDetail> ticketSalesDetails = ticketSalesDetailRepository.findAll();
        return mapToResponseList(ticketSalesDetails);
    }

    @Override
    public TicketSalesDetailResponse addTicket(TicketSalesDetailCreateRequest request) {

        TicketType ticketType = ticketTypeService.getTicketTypeById(request.ticketTypeId());
        TicketSalesDetail ticketSalesDetail = new TicketSalesDetail();
        ticketSalesDetail.setQuantity(request.quantity());
       // ticketSalesDetail.setTotalPrice(ticketType.getUnitPrice() * request.quantity());
        ticketSalesDetail.setTicketDate(request.ticketDate());
        ticketSalesDetail.setTicketType(ticketType);

        TicketSalesDetail toSave = ticketSalesDetailRepository.save(ticketSalesDetail);
        return mapToResponse(toSave);
    }

    @Override
    public TicketSalesDetail getTicketSalesDetailById(Long id) {
        return ticketSalesDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("ticketSalesDetail not found"));
    }

    @Override
    public List<TicketSalesDetailResponse> getTicketByDate(DateRequest dateRequest) {
        List<TicketSalesDetail> tickets = ticketSalesDetailRepository.findBySaleDateBetween(dateRequest.startDate(), dateRequest.endDate());
        return mapToResponseList(tickets);
    }

   /* @Override
    public Double calculateTicketValue(List<TicketSalesDetailResponse> ticketResponseList) {
        Double totalPrice = 0.0;
        for (TicketSalesDetailResponse ticketResponse : ticketResponseList) {
            totalPrice += ticketResponse.getTotalPrice();
        }
        return totalPrice;
    }
*/
    @Override
    public int calculateTicketQuantity(List<TicketSalesDetailResponse> ticketResponseList) {
        int ticketQuantity = 0;
        for (TicketSalesDetailResponse ticketResponse : ticketResponseList) {
            ticketQuantity += ticketResponse.getQuantity();
        }
        return ticketQuantity;
    }

    @Override
    public Integer getTicketCountByDay(LocalDate dayDate) {
        Integer ticketCount = ticketSalesDetailRepository.findTotalTicketSalesByDate(dayDate);
        if (ticketCount == null) {
            throw new RuntimeException("ticketCount is zero for a day");
        }
        return ticketCount;

    }
    @Override
    public Integer getTicketCountByMonth(LocalDate monthDate) {
        Integer ticketCount = ticketSalesDetailRepository.findTotalTicketSalesByMonth(monthDate);
        if(ticketCount == null){
            throw new RuntimeException("ticketCount is zero for a month");
        }
        return ticketCount;
    }

    public Integer getTicketCountByYear(LocalDate yearDate) {
        Integer ticketCount = ticketSalesDetailRepository.findTotalTicketSalesByYear(yearDate);
        if(ticketCount == null){
            throw new RuntimeException("ticketCount is zero for a year");
        }
        return ticketCount;
    }

    private TicketSalesDetailResponse mapToResponse (TicketSalesDetail ticketSalesDetail) {
        TicketSalesDetailResponse ticketSalesDetailResponse = new TicketSalesDetailResponse(
                ticketSalesDetail.getId(),
                ticketSalesDetail.getQuantity(),
                ticketSalesDetail.getTicketDate(),
                ticketSalesDetail.getTicketType().getId()
        );
        return ticketSalesDetailResponse;
    }
    private List<TicketSalesDetailResponse> mapToResponseList (List<TicketSalesDetail> ticketSalesDetailList) {
        List<TicketSalesDetailResponse> ticketSalesDetailResponseList = ticketSalesDetailList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ticketSalesDetailResponseList;
    }
}
