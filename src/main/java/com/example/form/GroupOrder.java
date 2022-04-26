package com.example.form;

import javax.validation.GroupSequence;

 /*Set the order of validation*/
@GroupSequence({ValidGroup1.class, ValidGroup2.class})
public interface GroupOrder {
    
}
