package enums;

import lombok.Getter;

@Getter
public enum ExpressStatus {
    CREATED(" 已揽收 "), TRANSIT(" 在途中 "), SUCCESS(" 签收 ");
    private final String info;

    ExpressStatus(String info) {
        this.info = info;
    }
//
//    public String getLabel() {
//        return label;
//    }
}