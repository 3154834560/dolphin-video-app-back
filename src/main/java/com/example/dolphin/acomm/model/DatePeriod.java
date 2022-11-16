package com.example.dolphin.acomm.model;

/**
 * 时间段(时间戳格式)
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
public class DatePeriod {
    /**
     * 开始时间
     */
    private Long form;
    /**
     * 结束时间
     */
    private Long to;


    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getForm() {
        return form;
    }

    public void setForm(Long form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return "DatePeriod{" +
                "form=" + form +
                ", to=" + to +
                '}';
    }

    //endregion
}
