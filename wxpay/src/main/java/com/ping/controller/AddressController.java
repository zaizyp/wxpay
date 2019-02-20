package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxDeliveryAddress;
import com.ping.service.IAddressService;
import com.ping.util.WXResult;

/**
 * 收货地址
 * <p>Title: AddressController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月20日下午4:30:31
 * @version 1.0
 */

@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	private IAddressService addressService;
	
	//收货地址列表页面
	@RequestMapping("/list")
	public String addressList(HttpSession session,Model model){
		
		Long userId = (Long)session.getAttribute("user_id");
		List<WxDeliveryAddress> addressList = addressService.getAddressList(userId);
		model.addAttribute("addressList", addressList);
		
		return "index/address_list";
	}
	
	//获取收货地址信息
	@RequestMapping("/getlist")
	@ResponseBody
	public List<WxDeliveryAddress> getAddressList(HttpSession session){
		
		Long userId = (Long)session.getAttribute("user_id");
		List<WxDeliveryAddress> addressList = addressService.getAddressList(userId);
		
		return addressList;
	}
	
	//收货地址添加页面
	@RequestMapping("/addPage")
	public String addPage(){
		return "index/address_add";
	}
	
	//收货地址编辑页面
	@RequestMapping("/edit")
	public String addressEdit(Long addressId,HttpSession session,Model model){

		Long userId = (Long)session.getAttribute("user_id");
		WxDeliveryAddress address = addressService.getAddress(addressId, userId);
		if (address==null) {
			return "redirect:/address/list";
		}
		model.addAttribute("address", address);
		return "index/address_edit";
	}
	
	
	//获取用户默认收货地址
	@RequestMapping("/default")
	@ResponseBody
	public WxDeliveryAddress defaultAddress(HttpSession session){
		
		Long userId = (Long)session.getAttribute("user_id");
		WxDeliveryAddress address = addressService.getDefaultAddress(userId);
		
		return address;
	}
	
	//添加收货地址
	@RequestMapping("/add")
	@ResponseBody
	public WXResult addAddress(HttpSession session,
			WxDeliveryAddress address,String addressInfo){
		
		//判断数据有效性
		if(address.getName()!=null&&address.getName().length()>0&&
			address.getTelphone()!=null&&address.getTelphone().length()>0&&
			addressInfo!=null&&addressInfo.length()>0){
			//解析地址信息
			String[] split = addressInfo.split(",");
			if(split.length!=3)
				return WXResult.build(500, "添加失败");
			
			address.setProvince(split[0]);
			address.setCity(split[1]);
			address.setArea(split[2]);
			Long userId = (Long)session.getAttribute("user_id");
			WXResult result = addressService.addAddress(address, userId);
			return result;
		}
		return WXResult.build(500, "添加失败！");
	}
	
	//更新收货地址
	@RequestMapping("update")
	@ResponseBody
	public WXResult updateAddress(HttpSession session,
			WxDeliveryAddress address,String addressInfo){
		//判断数据有效性
		if(address.getId()!=null&&
			address.getName()!=null&&address.getName().length()>0&&
			address.getTelphone()!=null&&address.getTelphone().length()>0&&
			addressInfo!=null&&addressInfo.length()>0){
			//解析地址信息
			String[] split = addressInfo.split(",");
			if(split.length!=3)
				return WXResult.build(500, "更新失败");
			address.setProvince(split[0]);
			address.setCity(split[1]);
			address.setArea(split[2]);
			Long userId = (Long)session.getAttribute("user_id");
			WXResult result = addressService.updateAddress(address, userId);
			return result;
		}
		System.out.println(address.toString());
		return WXResult.build(500, "更新失败！");
	}
	
	//删除收货地址
	@RequestMapping("delete")
	@ResponseBody
	public WXResult deleteAddress(HttpSession session,Long addressId){
		
		Long userId = (Long)session.getAttribute("user_id");
		WXResult result = addressService.deleteAddress(addressId, userId);
		return result;
	}
}
