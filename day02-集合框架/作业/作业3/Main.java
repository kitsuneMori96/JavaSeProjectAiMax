import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.transform.Result;
import java.util.*;

@Data
@NoArgsConstructor

class Option {
   private static Map<String, Integer> optionMap = new HashMap<>();
   private static String result;

   static {
       optionMap.put("A", 0);
       optionMap.put("B", 0);
       optionMap.put("C", 0);
       optionMap.put("D", 0);
   }

   public static void work (List<Student> students) {
        for(Student student : students){
            for(String option : student.getOptions()) {
                int index = optionMap.get(option);
                optionMap.put(option, index + 1);
            }
        }
   }

   public static void printResult() {
       System.out.println("选项A：" + optionMap.get("A"));
       System.out.println("选项B：" + optionMap.get("B"));
       System.out.println("选项C：" + optionMap.get("C"));
       System.out.println("选项D：" + optionMap.get("D"));
       //找出投票最多的选项
       int max = 0;
       for(String option : optionMap.keySet()) {
           if(optionMap.get(option) > max) {
               max = optionMap.get(option);
               result = option;
           }
       }
       System.out.println("最终结果："+  result + " 票数：" + max);
   }

   public static void findLoner() {
       List<String> loners = new ArrayList<>();
//       for(Student student : Main.getStudents()){
//           boolean isLoner = true;
//           for(String option : student.getOptions()){
//               if (option.equals(result)) {
//                   isLoner = false;
//                   break;
//               }
//           }
//           if(isLoner) {
//               loners.add(student.getName());
//           }
//       }
//       System.out.println("loners：" + loners);
       // 使用stream流
       Main.getStudents()
               .stream()
               .filter(student -> student.getOptions()
                       .stream()
                       .noneMatch(option -> option
                               .equals(result)))
               .map(Student::getName)
               .forEach(loners::add);
       System.out.println("loners：" + loners);

   }
}

@Data
@NoArgsConstructor
@AllArgsConstructor

class Student {
    private String name;
    private List<String> options = new ArrayList<>();
}

@Data

public class Main {
    @Getter
    private static List<Student> students = new ArrayList<>();

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入学生姓名：(输入q退出)");
            String name = scanner.nextLine();
            if (name.equals("q")) {
                break;
            }
            String options[] = setOptions();
            Student student = new Student(name , Arrays.asList(options));
            students.add(student);
        }
    }

    public static String[] setOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生选择的选项：\n" +
                "a. 选项A\n" +
                "b. 选项B\n" +
                "c. 选项C\n" +
                "d. 选项D\n");
        String[] Setoptions = scanner.nextLine().split(" ");
        //转成大写字母
        for (int i = 0; i < Setoptions.length; i++) {
            Setoptions[i] = Setoptions[i].toUpperCase();
        }
        for (String option : Setoptions) {
            if(!option.equals("A") && !option.equals("B") && !option.equals("C") && !option.equals("D")) {
                System.out.println("输入的选项有误！请重新输入！");
                return setOptions();
            }
        }
        return Setoptions;
    }

    static void main(String[] args) {
        start();
        Option.work(students);
        Option.printResult();
        Option.findLoner();
    }
}