package com.qzj.facial.common.validation.validator;

import android.content.Context;

import com.qzj.facial.common.validation.ValidationExecutor;
import com.qzj.facial.common.validation.ValidationModel;


/**
 * 邮编验证器
 */
@SuppressWarnings("unused")
public class PostCodeValidator extends ValidationExecutor {

    private static final String POST_HINT = "格式不正确";

    @Override
    public boolean doValidate(Context context, ValidationModel model) {
        EmptyValidator emptyValidator = new EmptyValidator();
        boolean isEmpty = emptyValidator.doValidate(context,model);
        if(isEmpty){
            if(isPostCode(model.getEditText().getText().toString())){
                return true;
            }else{
                hintMsg = model.getModelName() + POST_HINT;
                if (model.getHintView() != null)
                    model.getHintView().setText(hintMsg);
            }
        }

        return false;
    }
}
