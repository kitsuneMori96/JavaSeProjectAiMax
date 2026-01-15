import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Organize {
    public static final String info = "10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼#10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺#10003,周芷若,女,2023-07-22 04:11:21,木兰文化区-东湖#10004，小昭,女,2023-07-22 08:11:21,东湖#10005，灭绝,女,2023-07-22 17:11:21,归元禅寺" ;
}

class Student {
    private long id;
    private String name;
    private String sex;
    private LocalDateTime selectTime;
    private List<String> locations = new ArrayList<>();

    public void analysis () {

    }

}
