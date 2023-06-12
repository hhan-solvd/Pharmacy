package com.solvd.app.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String s) throws Exception {
        return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime());
    }

    @Override
    public String marshal(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
