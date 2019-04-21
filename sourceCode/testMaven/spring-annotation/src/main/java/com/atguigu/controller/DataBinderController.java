package com.atguigu.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.*;
import java.util.Date;
import java.util.Locale;

@Controller
public class DataBinderController {
    @InitBinder
    public void initEditorBinder(WebDataBinder binder) {
        MyDateEditor myDateEditor = new MyDateEditor();
        binder.registerCustomEditor(Date.class, new CustomDateEditor(myDateEditor, false));
    }
    @InitBinder
    protected void initFormatterBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

}

class MyDateEditor extends DateFormat {

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        toAppendTo.append(date.getTime());
        return toAppendTo;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return new Date(Long.parseLong(source));
    }
}
class MyDateFormat implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return new Date(Long.parseLong(text));    }

    @Override
    public String print(Date object, Locale locale) {
        return String.valueOf(object.getTime());
    }
}

