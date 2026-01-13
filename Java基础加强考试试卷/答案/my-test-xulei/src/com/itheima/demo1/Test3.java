package com.itheima.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 - **业务一：**
 - 需要你解析上面的字符串，获取里面的用户数据，并封装到Sutdent对象中
 - 多个Student对象在添加到List<Student> 集合中
 - 注意：
 - 字符串中的规则如下，多个用户用 # 拼接，用户的信息之间用 , 拼接,多个景点是用-拼接的。
 - 其中用户的id和选择时间是需要进行类型转换的，其中id需要将String转成Long，选择时间需要将String转成LocalDateTime。
 - **业务二：**

 - 遍历上面获取的List<Student> 集合，统计里面每个景点选择的次数，并输出 景点名称和选择的次数。
 **业务三：
 * 请用程序计算出哪个景点想去的人数最多，以及哪些人没有选择这个最多人想去的景点。
 */
public class Test3 {
    public static void main(String[] args) {
        String info = "10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼#10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺" +
                "#10003,周芷若,女,2023-07-22 04:11:21,木兰文化区-东湖#10004,小昭,女,2023-07-22 08:11:21,东湖#10005,灭绝,女,2023-07-22 17:11:21,归元禅寺" ;
        // 把这个信息转换成List<Student>对象。
        List<Student> students = parseStudents(info);
        System.out.println(students);    // 解析成对象 10分

        // 历上面获取的List<Student> 集合，统计里面每个景点选择的次数，并输出 景点名称和选择的次数。 // 5分。
        Map<String,Integer> map =parseSelectAddresssCount(students);

        // 请用程序计算出哪个景点想去的人数最多，以及哪些人没有选择这个最多人想去的景点。
        String maxSelectAddress = getMaxSelectAddress(map);
        System.out.println("最多选择的景点名称：" + maxSelectAddress); // 找到最多选择的景点3分

        printNoSelectAddress(students,maxSelectAddress); // 打印哪些人没有选择这个景点  2分
    }

    private static void printNoSelectAddress(List<Student> students, String maxSelectAddress) {
        students.stream().filter(student -> !student.getSelectAddress().contains(maxSelectAddress))
                .forEach(student -> System.out.println(student.getName() + "没有选择" + maxSelectAddress));
    }

    private static String getMaxSelectAddress(Map<String, Integer> map) {
        // 请使用stream流找出最多选择的景点名称返回。
        return map.entrySet().stream().max((o1, o2) -> o1.getValue() - o2.getValue()).get().getKey();
    }

    public static   Map<String,Integer>  parseSelectAddresssCount(List<Student> students){
        Map<String, Integer> map = new HashMap<>();
        for (Student student : students) {
            String selectAddress = student.getSelectAddress(); // 东湖-黄鹤楼
            String[] selectAddressArr = selectAddress.split("-");
            // 遍历每个景点
            for (String address : selectAddressArr) {
                // 判断map中有没有这个景点
                if (map.containsKey(address)){
                    // 有这个景点，次数+1
                    map.put(address, map.get(address) + 1);
                }else {
                    // 没有这个景点，次数+1
                    map.put(address, 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("景点名称：" + entry.getKey() + " ，选择了几次： " + entry.getValue());
        }
        return map;
    }

    private static List<Student> parseStudents(String info) {
        String[] studentsInfo = info.split("#"); // 10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼  10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺
        List<Student> students = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (String studentInfo : studentsInfo) {
            String[] studentInfoArr = studentInfo.split(",");
            String time = studentInfoArr[3]; // 2023-07-22 11:11:12
            // 解析成LocalDateTime对象
            LocalDateTime localDateTime = LocalDateTime.parse(time, dtf);
            Student student = new Student(Long.parseLong(studentInfoArr[0]),
                    studentInfoArr[1], studentInfoArr[2], localDateTime, studentInfoArr[4]);
            // 添加到集合中
            students.add(student);
        }
        return students;
    }
}

// 定义学生类
@Data
@NoArgsConstructor
@AllArgsConstructor
class Student{
    private long id;
    private String name;
    private String sex;
    private LocalDateTime localDateTime;
    private String selectAddress;
}

