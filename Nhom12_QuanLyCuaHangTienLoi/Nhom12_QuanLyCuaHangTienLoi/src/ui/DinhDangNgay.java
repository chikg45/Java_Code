package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField;


public class DinhDangNgay extends JFormattedTextField.AbstractFormatter {
    private String pattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            if (value instanceof Calendar) {
                return dateFormatter.format(((Calendar) value).getTime());
            } else if (value instanceof java.util.Date) {
                return dateFormatter.format((java.util.Date) value);
            }
        }
        return "";
    }
}
