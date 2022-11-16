package com.example.dolphin.acomm.model;

/**
 * TV对
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
public class TextValue {
    /**
     * 中文
     */
    private String text;
    /**
     * 英文
     */
    private String value;

    protected TextValue() {
    }

    public TextValue(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static TextValue of(String text, String value) {
        return new TextValue(text, value);
    }

    //region gt&...

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TextValue{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    //endregion
}
