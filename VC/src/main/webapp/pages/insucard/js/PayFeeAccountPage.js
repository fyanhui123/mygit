 var payFeeAccount = {
		 isOrNotData : [{
		  		"label" : "否",
				 "value" : "1"
			 },{
				 "label" : "是",
				 "value" : "0"
			} ],
			
		cardTypeData : [{
	  		"label" : "折",
			 "value" : "1"
	      },{
	  		"label" : "借记卡",
			 "value" : "2"
		 },{
	  		"label" : "贷记卡",
			 "value" : "3"
		 },{
		    "label" : "准贷记卡",
		    "value" : "4"
		} ],
		
		
		initPage: function(){
			/**
			 *开户行自动完成
			 */
			$("#bankInfoDtoPayeeBankLocationCode").taAutoComplete({
				source : "/visa/pubJson/queryPubBanklocationsList.do?bankCode="+$("#bankInfoDtoPayeeBankCode").val()+"&areaCode="+$("#bankInfoDtoPayeeAreaCode").val(),				
				minLength : 0,
				height : 210,
				width : 220
			});
			
			$("#bankInfoDtoPayeeBankCode").blur(function(){
				var bankCode=$("#bankInfoDtoPayeeBankCode").val();
				var areaCode=$("#bankInfoDtoPayeeAreaCode").val();
				if(bankCode!='' || areaCode!=''){
				  $("#bankInfoDtoPayeeBankLocationCode").taAutoComplete({
					source : "/visa/pubJson/queryPubBanklocationsList.do?bankCode="+bankCode+"&areaCode="+areaCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				}
			});
			
			/**
			 *银行名称自动完成
			 */
			$("#bankInfoDtoPayeeBankCode").taAutoComplete({
				source : "/visa/pubJson/queryPubBanksList.do",
				minLength : 1,
				height : 210,
				width : 220
			});
			$("#bankInfoDtoPayeeAreaCode").blur(function(){
				var bankCode=$("#bankInfoDtoPayeeBankCode").val();
				var areaCode=$("#bankInfoDtoPayeeAreaCode").val();
				if(bankCode!='' || areaCode!=''){
				  $("#bankInfoDtoPayeeBankLocationCode").taAutoComplete({
					source : "/visa/pubJson/queryPubBanklocationsList.do?bankCode="+bankCode+"&areaCode="+areaCode,
					minLength :0,
					height : 210,
					width : 220
				});
				}
			});
			/**
			 *区域自动完成
			 */
			$("#bankInfoDtoPayeeAreaCode").taAutoComplete({
				source :"/visa/pubJson/queryPubStandardareasList.do",
				minLength : 2,
				height : 210,
				width : 220
			});
			
			/*
			//银行卡折类型
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});
			
			//加急标志
			bankInfoDtoFastFlag
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});
			//公/私标志
			bankInfoDtoCorpInd
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});
			//短信通知
			bankInfoDtoSmsFlag
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});
			//邮件通知
			bankInfoDtoMailFlag
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});
			//证件类型
			bankInfoDtoCertType
			$("#bankInfoDtoCardType").dropDownList( {
				data : payFeeAccount.cardTypeData
			});*/
			
		
				$("#bankInfoDtoCardType").dropDownList("selected",$("#bankInfoDtoCardType_hidden").val()); 
				$("#bankInfoDtoFastFlag").dropDownList("selected",$("#bankInfoDtoFastFlag_hidden").val());
				$("#bankInfoDtoCorpInd").dropDownList("selected",$("#bankInfoDtoCorpInd_hidden").val());
				$("#bankInfoDtoSmsFlag").dropDownList("selected",$("#bankInfoDtoSmsFlag_hidden").val());
				$("#bankInfoDtoMailFlag").dropDownList("selected",$("#bankInfoDtoMailFlag_hidden").val());
				$("#bankInfoDtoCertType").dropDownList("selected",$("#bankInfoDtoCertType_hidden").val());
				
			
			
		}
	}
