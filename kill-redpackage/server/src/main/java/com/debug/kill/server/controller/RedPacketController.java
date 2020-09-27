package com.debug.kill.server.controller;

import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import com.debug.kill.server.entity.RedPacketDto;
import com.debug.kill.server.service.IRedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@RestController
public class RedPacketController {
	private static final String prefix = "/red/packet";
	@Resource
	IRedPacketService redPacketService;


	/**
	 * 发红包
	 *
	 * @param dto    红包金额，红包数量，userId
	 * @param result
	 * @return 红包Id
	 */
	@RequestMapping(value = prefix + "/hand/out", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse handout(@Validated @RequestBody RedPacketDto dto, BindingResult result){
		// 参数校验
		if(result.hasErrors()){
			return new BaseResponse(StatusCode.InvalidParams);
		}
		BaseResponse response = new BaseResponse(StatusCode.Success);
		try {
			// 获得红包id
			String redId = redPacketService.handOut(dto);
			response.setData(redId);
		} catch (Exception e) {
			log.error("发红包异常：dto={}", dto, e.fillInStackTrace());
			e.printStackTrace();
			response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
		}
		return response;
	}

	/**
	 * 抢红包
	 *
	 * @param userId
	 * @param redId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = prefix + "/rob", method = RequestMethod.GET)
	public BaseResponse rob(@RequestParam Integer userId, @RequestParam String redId) throws Exception{
		BaseResponse response = new BaseResponse(StatusCode.Success);
		try {
			BigDecimal result = redPacketService.rob(userId, redId);
			if(result != null){
				response.setData(result);
			}else{
				response = new BaseResponse(StatusCode.Fail.getCode(), "红包已经被抢完");
			}
		} catch (Exception e) {
			response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
		}
		return response;
	}
}
