package com.ustore.products.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ustore.products.dao.OrderDao;
import com.ustore.products.dto.OrderDto;
import com.ustore.products.service.OrderService;

@Controller
public class OrderController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService service;
	@Autowired OrderDao dao;

	@GetMapping(value = "/order/list") //발주 화면 뿌려주는 컨트롤러 
		public String order(Model model, HttpSession session) {
		
		
		ArrayList<OrderDto> list = service.list(); //발주 리스트 
	
	
		
		String birthdateString  = (String) session.getAttribute("birthdate");
		if (birthdateString == null) {
		    birthdateString = "01/01/2100";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedBirthdate = birthdate.format(outputFormatter);
		
		
		
		
		logger.info("날짜 ? : "+formattedBirthdate);
		
		
		model.addAttribute("list",list);
		logger.info("list : "+list);
		
		ArrayList<OrderDto> list3 = service.driveList(formattedBirthdate); // 배달기사 리스트
		model.addAttribute("list3",list3);
				logger.info("list3 : "+list3);
				
				ArrayList<OrderDto> list4 = service.driverSch(formattedBirthdate);
				model.addAttribute("list4",list4);
		
		
		
		
		return "products/order";
	}
	@PostMapping("/order/ordercart/insert") // 물품 장바구니에 추가하는 컨트롤러 
    @ResponseBody
    public String ordercartInsert(@RequestParam Map<String, String> params, HttpSession session,@RequestParam("firstbirthdate")String firstbirthdate,Model model) {
     
		logger.info("firstbirthdate : "+firstbirthdate);
		
		
		String birthdate = params.get("birthdate");
        session.setAttribute("birthdate", birthdate);
        

        logger.info("birthdate :"+birthdate);
        logger.info("params : "+params);
        
        
        

        boolean checkPro = service.checkProduct(params);

        if (checkPro) {
            service.orderCartInsert(params);
            return "success"; 
        } else {
            return "alreadyAdded"; 
        }
    }
	
	@GetMapping("/order/ordercart/list") // 발주 장바구니 리스트  ajax 요청
	@ResponseBody
	public ResponseEntity<ArrayList<OrderDto>> getOrderList(HttpSession session,Model model) {
	   
		
		boolean orderCheck = service.orderCartCheck();	
	    logger.info("장바구니에 값이 있나?  없으면 false : "+orderCheck);
	    if (orderCheck==false) {
	      
	        return ResponseEntity.noContent().build(); // return 하지 않음 x
	    }
		ArrayList<OrderDto> orderList = service.orderList();
		
		
		
		
		
		String birthdateString  = (String) session.getAttribute("birthdate");
		if (birthdateString == null) {
		    birthdateString = "01/01/2100";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedBirthdate = birthdate.format(outputFormatter);
		
		ArrayList<OrderDto> list3 = service.driveList(formattedBirthdate); // 배달기사 리스트
		model.addAttribute("list3",list3);
				logger.info("list3 : "+list3);
	    
	  
	  
	    

	    
	    logger.info("장바구니 리스트:"+orderList);
	    return ResponseEntity.ok(orderList);
	}
	@GetMapping(value = "/order/ordercartdate/list")
	public String orderCartDate(HttpSession session,Model model) {
	
		
		
		return "";
	}
		
		
		
		
	
	
	
	@PostMapping("/order/ordercart/delete") //장바구니 삭제 
	@ResponseBody
	public ResponseEntity<String> ordercartDelete(@RequestParam String productName) {
		
	    service.ordercartDelete(productName);
	    logger.info("삭제 컨트롤러 들어옴");
	    logger.info("이름::"+productName);
	    return ResponseEntity.ok("삭제 성공");
	}
		
	
	@PostMapping("/order/insert") //발주자 (사원	id 필요 emp_idx)
	@ResponseBody
	public String orderInsert(@RequestParam String driverIdx,@RequestParam String empIdx,HttpSession session,@RequestParam String resourceIdx) {
		
		
		boolean orderCheck = service.orderCartCheck();
	    logger.info("장바구니에 값이 있나?  없으면 false : "+orderCheck);
	    logger.info("empIdx 값 찍히는지 : "+empIdx);
	    logger.info("resourceIdx : "+resourceIdx);
		
		String birthdateString  = (String) session.getAttribute("birthdate");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedBirthdate = birthdate.format(outputFormatter);
		
		logger.info("선택한 날짜  : "+formattedBirthdate);
		logger.info("선택한 배송기사 : "+ driverIdx);
		
		service.orderInsert(formattedBirthdate,driverIdx,empIdx,resourceIdx); // 여기에 emp_idx 필요 
		ArrayList<OrderDto> ProductCount = service.orderCartSelect();
		
		
		
		logger.info("상품 개수: "+ProductCount);
		
		for (OrderDto orderDto : ProductCount) {
		    String productId = orderDto.getProductId();
		    logger.info("상품 ID: " + productId);
		    
		    service.orderHistoryInsert(productId);
		}
		
		
		service.orderDelete();
		
		
		return "발주 성공!";
	}
	
	//--------------------------------------order_list---------------------------------------
	
	
	@GetMapping("/orderlist/list")
	public String orderList(Model model) {
		
		ArrayList<OrderDto> orderlist = service.orderHistoryList();
		model.addAttribute("orderlist",orderlist);
		
		
		
		return "products/order_list";
	}
	@PostMapping("/orderlist/modallist")
	 @ResponseBody
	 public ArrayList<OrderDto> orderListModal(@RequestParam String orderIdx) {
		
		logger.info("발주 번호 : "+orderIdx);
		
		
		ArrayList<OrderDto> orderListModal= service.orderListModal(orderIdx);
		logger.info("리스트에 값이 담기는지 확인 : "+orderListModal);
		
		
		
		return orderListModal;
	}
	
	@GetMapping(value = "order/orderdriver/list")
	@ResponseBody
	public ArrayList<OrderDto> driverList(HttpSession session,Model model){
		
		String birthdateString  = (String) session.getAttribute("birthdate");
		if (birthdateString == null) {
		    birthdateString = "01/01/2100";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedBirthdate = birthdate.format(outputFormatter);
		
		
		ArrayList<OrderDto> list3 = service.driveList(formattedBirthdate); // 배달기사 리스트

		
		return list3;
	}
	// --------------------------검색------------------------------------
	
//@GetMapping(value = "/order/orderlist/search")
//	@ResponseBody
//	public ArrayList<OrderDto> orderListSearch(@RequestParam("searchValue")String searchValue){
		
	//	logger.info("searchValue : "+searchValue);
		
	//	return service.orderListSearch(searchValue);
//	}
	
	

}


