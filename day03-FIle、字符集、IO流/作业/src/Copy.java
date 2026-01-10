
import java.io.*;
import java.util.Scanner;

public class Copy {
    public static void main(String[] args) {
        System.out.println("选择文件进行复制");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        System.out.println("选择目标路径");
        String targetPath = sc.nextLine();
        read(fileName, targetPath);
    }

    public static void read(String fileName, String targetPath) {
        try(
            FileInputStream fis = new FileInputStream(fileName);
            FileOutputStream fos = new FileOutputStream(targetPath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            //判断文件或者是文件夹
            File file = new File(fileName);
            if (file.isFile()) {
                System.out.println("复制文件");

            }
            else {
                System.out.println("复制文件夹");
            }
            long startTime = System.currentTimeMillis();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer))!= -1){
                bos.write(buffer, 0, len);
            }
            System.out.println("文件复制成功");
            System.out.println("用时" + (System.currentTimeMillis() - startTime) + "毫秒");
        }
        catch (IOException e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }
}