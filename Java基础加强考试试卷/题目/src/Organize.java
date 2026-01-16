import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Organize {
    public static String info = "10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼#10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺#10003,周芷若,女,2023-07-22 04:11:21,木兰文化区-东湖#10004，小昭,女,2023-07-22 08:11:21,东湖#10005，灭绝,女,2023-07-22 17:11:21,归元禅寺" ;
}

@Data
class Student {
    private long id;
    private String name;
    private String sex;
    private LocalDateTime selectTime;
    private List<String> locations = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", selectTime=" + selectTime +
                ", locations=" + locations +
                '}';
    }
}

@Data
class StudentManager {
    @Getter
    private static List<Student> students = new ArrayList<>();

    public static void analysisInfo () {
        //将info根据#切割开
        String[] infos = Organize.info.split("#");
        for(String info : infos){
            String[] userInfos = info.split("[,，]");
            Student student = new Student();
            student.setId(Long.parseLong(userInfos[0]));
            student.setName(userInfos[1]);
            student.setSex(userInfos[2]);
            //将userInfos[3]转成localdatatime格式
            student.setSelectTime(LocalDateTime.parse(userInfos[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            String[] locations = userInfos[4].split("-");
            student.setLocations(Arrays.asList(locations));
            students.add(student);
        }
    }
}

class Analysis {
    private static Map<String,Integer> locationCount = new HashMap<>();
    private static List<Map.Entry<String, Integer>> entries = new ArrayList<>();

    public static void analysis(List<Student> students){
        for(Student student : students){
            for(String location : student.getLocations()){
                if(locationCount.containsKey(location)){
                    locationCount.put(location,locationCount.get(location)+1);
                }
                else{
                    locationCount.put(location,1);
                }
            }
        }
    }

    public static void end () {
        //设置排序,使用int值排序
        entries = new ArrayList<>(locationCount.entrySet());
        entries.sort((o1, o2) -> o2.getValue() - o1.getValue());
        System.out.println("地点统计结果:");
        System.out.println(entries);
        System.out.println(entries.getFirst().getKey()+"最多,出现次数:"+entries.getFirst().getValue());
    }

    public static void Loneliness () {
        for(Student student : StudentManager.getStudents()) {
            boolean isLonely = true;
            for(String location : student.getLocations()){
                if(location.equals(entries.getFirst().getKey())) isLonely = false;
            }
            if(isLonely) System.out.println(student.getName()+"没有选择热门景点");
        }
    }

    static void main(String[] args) {
        StudentManager.analysisInfo();
        analysis(StudentManager.getStudents());
        end();
        Loneliness();
    }
}
