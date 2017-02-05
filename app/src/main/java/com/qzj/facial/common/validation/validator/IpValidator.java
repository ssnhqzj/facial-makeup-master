package com.qzj.facial.common.validation.validator;

import android.content.Context;

import com.qzj.facial.common.validation.ValidationExecutor;
import com.qzj.facial.common.validation.ValidationModel;


/**
 * IP验证器
 */
@SuppressWarnings("unused")
public class IpValidator extends ValidationExecutor {

    private static final String IP_HINT = "还未填写";

    @Override
    public boolean doValidate(Context context, ValidationModel model) {
        EmptyValidator emptyValidator = new EmptyValidator();
        boolean isEmpty = emptyValidator.doValidate(context,model);
        if(isEmpty){
            if(isIpAddress(model.getEditText().getText().toString())){
                return true;
            }else{
                hintMsg = model.getModelName() + IP_HINT;
                if (model.getHintView() != null)
                    model.getHintView().setText(hintMsg);
            }
        }

        return false;
    }

}
