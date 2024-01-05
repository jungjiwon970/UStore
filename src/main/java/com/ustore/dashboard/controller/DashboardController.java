package com.ustore.dashboard.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ustore.dashboard.dto.DashboardDto;
import com.ustore.dashboard.service.DashboardService;
import com.ustore.products.dto.StockDto;

@Controller
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired DashboardService service;
// ----------------------------------------------------dash board 임시 -------------------------------------------------------------
	
	@GetMapping(value = "/accountingdashboard")
	public String dashboardList() {
		
		
		
		
		return "accounting/dashboard";
	}
	
	@GetMapping(value = "/accounting/dashboard/produtctlist")
	@ResponseBody ArrayList<DashboardDto> accountingDashboard(){
		
		
		ArrayList<DashboardDto> DashDto = service.accountingDashboard();
		
		logger.info("DashDto : "+ DashDto);
		
		
		return DashDto;
	}
	
	@GetMapping(value = "/accounting/dashboard/produtctdaylist")
	@ResponseBody
	public ArrayList<DashboardDto> accountingDashbaordDay(@RequestParam("selectedDate") String selectedDate){
		
		logger.info("selectedDate :" +selectedDate);
		
		String firstSelectedDate = selectedDate.substring(0,10) ;
		String lastSelectedDate = selectedDate.substring(13,23);
		
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate = LocalDate.parse(firstSelectedDate, formatter);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String TrfirstSelectedDate = birthdate.format(outputFormatter);
		
DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate2 = LocalDate.parse(lastSelectedDate, formatter);
		
		DateTimeFormatter outputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String TrlastSelectedDate = birthdate2.format(outputFormatter);
		
		logger.info("TrlastSelectedDate : "+TrlastSelectedDate);
		logger.info("TrfirstSelectedDate : "+TrfirstSelectedDate);
		
		return service.accountingDashboardDay(TrfirstSelectedDate,TrlastSelectedDate);
	}
	
}
