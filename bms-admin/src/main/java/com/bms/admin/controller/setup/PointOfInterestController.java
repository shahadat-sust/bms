package com.bms.admin.controller.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.LabelValueModel;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.PointOfInterestData;
import com.bms.service.data.provider.ProviderPointOfInterestData;
import com.bms.service.data.provider.ProviderTypeData;
import com.bms.service.soa.provider.IPointOfInterestService;
import com.bms.service.soa.provider.IProviderTypeService;

@Controller
@RequestMapping(value = "/pointofinterest")
@Scope("request")
public class PointOfInterestController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IPointOfInterestService pointOfInterestService;
	private IProviderTypeService providerTypeService;
	
	@ModelAttribute("providerTypeList")
	public List<LabelValueModel<Long>> getProviderTypeList() throws BmsSqlException, BmsException {
		List<LabelValueModel<Long>> labelValueModels = new ArrayList<LabelValueModel<Long>>();
		List<ProviderTypeData> providerTypeDatas = providerTypeService.getAllProviderTypes();
		if(providerTypeDatas != null && providerTypeDatas.size() > 0) {
			Collections.sort(providerTypeDatas, new Comparator<ProviderTypeData>() {
				@Override
				public int compare(ProviderTypeData o1, ProviderTypeData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			
			for(ProviderTypeData providerTypeData : providerTypeDatas) {
				labelValueModels.add(new LabelValueModel<Long>(providerTypeData.getName(), providerTypeData.getId()));
			}
		}
		return labelValueModels;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pointOfInterest(Model model) throws BmsSqlException, BmsException {
		List<PointOfInterestData> pointOfInterestList = pointOfInterestService.getAllPointOfInterests();
		model.addAttribute("pointOfInterestList", pointOfInterestList);
		return "setup/pointofinterest";
	}
	
	@RequestMapping(value = "/fetch/{pointOfInterestId}/{providerTypeId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PointOfInterestData> getPointOfInterestList(@PathVariable long pointOfInterestId, @PathVariable long providerTypeId, @PathVariable int sortOrder) {
		ResponseModel<PointOfInterestData> responseModel = new ResponseModel<PointOfInterestData>();
		try {
			if(pointOfInterestId > 0) {
				PointOfInterestData pointOfInterestData = pointOfInterestService.getPointOfInterestById(pointOfInterestId);
				responseModel.addData(pointOfInterestData);
			} else {
				List<PointOfInterestData> pointOfInterestList = null;
				if(providerTypeId > 0) {
					pointOfInterestList = pointOfInterestService.getPointOfInterestsByProviderTypeId(providerTypeId);
				} else {
					pointOfInterestList = pointOfInterestService.getAllPointOfInterests();
				}
				
				if (pointOfInterestList != null && pointOfInterestList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(pointOfInterestList, new Comparator<PointOfInterestData>() {
							@Override
							public int compare(PointOfInterestData o1, PointOfInterestData o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(pointOfInterestList, new Comparator<PointOfInterestData>() {
							@Override
							public int compare(PointOfInterestData o1, PointOfInterestData o2) {
								return o2.getName().compareTo(o1.getName());
							}
						});
					}
				}
				
				responseModel.addDatas(pointOfInterestList);
			}
			responseModel.setStatus(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PointOfInterestData> createPointOfInterest(@RequestBody PointOfInterestData pointOfInterestData) {
		ResponseModel<PointOfInterestData> responseModel = new ResponseModel<PointOfInterestData>();
		try {
			boolean isAvailable = pointOfInterestService.isAvailable(pointOfInterestData.getId(), pointOfInterestData.getName(), pointOfInterestData.getProviderTypeId());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and provider type" }, Locale.getDefault()));
				return responseModel;
			}
			
			long pointOfInterestId = pointOfInterestService.create(pointOfInterestData, getLoginUserData().getId());
			if(pointOfInterestId > 0) {
				PointOfInterestData data = pointOfInterestService.getPointOfInterestById(pointOfInterestId);
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PointOfInterestData> updatePointOfInterest(@RequestBody PointOfInterestData pointOfInterestData) {
		ResponseModel<PointOfInterestData> responseModel = new ResponseModel<PointOfInterestData>();
		try {
			boolean isAvailable = pointOfInterestService.isAvailable(pointOfInterestData.getId(), pointOfInterestData.getName(), pointOfInterestData.getProviderTypeId());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and provider type" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = pointOfInterestService.update(pointOfInterestData, getLoginUserData().getId());
			if(status) {
				PointOfInterestData data = pointOfInterestService.getPointOfInterestById(pointOfInterestData.getId());
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{pointOfInterestId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PointOfInterestData> deletePointOfInterest(@PathVariable long pointOfInterestId) {
		ResponseModel<PointOfInterestData> responseModel = new ResponseModel<PointOfInterestData>();
		try {
			responseModel.setStatus(pointOfInterestService.delete(pointOfInterestId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("pointOfInterestService")
	public void setPointOfInterestService(IPointOfInterestService pointOfInterestService) {
		this.pointOfInterestService = pointOfInterestService;
	}
	
	@Autowired
	@Qualifier("providerTypeService")
	public void setProviderTypeService(IProviderTypeService providerTypeService) {
		this.providerTypeService = providerTypeService;
	}
}
