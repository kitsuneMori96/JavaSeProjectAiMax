

# day11IO流

## 题目1（综合扩展）

windows操作系统中可以复制文件夹,比如把D:\\from\\day11文件夹,复制到E:\\to\\day11文件夹下。但是java没有提供直接复制文件夹的方法.请编写程序从键盘接收两个文件夹路径,把其中一个文件夹中(包含内容)拷贝到另一个文件夹中。效果如下图：

![](img\05.png)

### 训练目标

能够使用递归调用定义方法,复制文件夹。

### 训练提示

1、该方法是否需要返回值?

2、如何复制单个文件，用什么流？

3、复制文件的功能实现后，文件夹怎么办？

### 参考方案

定义方法,复制文件夹,再调用方法传递两个文件夹File对象。

### 操作步骤  

1、定义复制文件到文件夹的方法copyFile2Dir

	1.1、创建文件字节输入流FileInputStream类的对象,绑定源文件
	    1.2、定义byte数组,保存每次读取到的字节的内容
	    1.3、定义int变量,保存每次读取到的字节数量
	   	1.4、根据目标文件夹和源文件,创建目标文件
	   	1.5、创建文件字节输出流FileOutputStream类的对象,绑定目标文件
	   	1.6、循环读(源文件)写(目标文件)
	   	1.7、关闭流释放资源
  2、定义复制文件夹到文件夹的方法copyDir2Dir

	2.1、在目标文件夹中创建源文件夹
	    2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
	    2.3、判断,如果File对象数组是null或者没有内容,结束方法
	    2.4、遍历File对象数组
	    2.5、判断,如果当前File对象是文件,调用copyFile2Dir方法,完成文件复制
	    2.6、判断,如果当前File对象是文件夹,递归调用copyDir2Dir方法,完成文件夹复制
  3、创建File对象srcDir,代表源文件夹
  4、创建File对象destDir,代表目标文件夹(把源文件夹拷贝到目标文件夹中)
  5、调用copyDir2Dir方法,传递源文件夹和目标文件夹,完成文件夹的复制

### 参考答案

```java
import java.io.File;
import java.util.Scanner;

public class Test05 {
    //1、定义复制文件到文件夹的方法copyFile2Dir
    public static void copyFile2Dir(File file,File dir) throws IOException {
		//1.1、创建文件字节输入流FileInputStream类的对象,绑定源文件
        InputStream is = new FileInputStream(file);
		//1.2、定义byte数组,保存每次读取到的字节的内容
        byte[] bs = new byte[1024*10];
		//1.3、定义int变量,保存每次读取到的字节数量
        int len = 0;
        //1.4、根据目标文件夹和源文件,创建目标文件
        File destFile = new File(dir,file.getName());
		//1.5、创建文件字节输出流FileOutputStream类的对象,绑定目标文件
        OutputStream os = new FileOutputStream(destFile);
		//1.6、循环读(源文件)写(目标文件)
        while((len = is.read(bs))!=-1) {
            os.write(bs,0,len);
        }
		//1.7、关闭流释放资源
        is.close();
        os.close();
    }
    //2、定义复制文件夹到文件夹的方法copyDir2Dir
    public static void copyDir2Dir(File srcDir,File destDir) throws IOException {
        //2.1、在目标文件夹中创建源文件夹
        File newDir = new File(destDir, srcDir.getName());
        if(!newDir.exists()) {
            newDir.mkdirs();
        }
        //2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
        File[] files = srcDir.listFiles();
        //2.3、判断,如果File对象数组是null或者没有内容,结束方法
        if(files==null || files.length==0) {
            return ;
        }
        //2.4、遍历File对象数组
        for (File file : files) {
            //2.5、判断,如果当前File对象是文件,调用copyFile2Dir方法,完成文件复制
            if(file.isFile()) {
                copyFile2Dir(file,newDir);               
            }else {
                //2.6、判断,如果当前File对象是文件夹,递归调用copyDir2Dir方法,完成文件夹复制
                copyDir2Dir(file,newDir);
            }
        }
    }
    public static void main(String[] args)  throws IOException {
        //3、创建File对象srcDir,代表源文件夹
        File srcDir = new File("...");
        //4、创建File对象destDir,代表目标文件夹(把源文件夹拷贝到目标文件夹中)
        File destDir = new File("...");
        //5、调用copyDir2Dir方法,传递源文件夹和目标文件夹,完成文件夹的复制
        copyDir2Dir(srcDir,destDir);
        System.out.println("文件夹复制完毕!!!");
    }

}

```





## 题目2（综合扩展）

windows操作系统中可以进行文件夹的剪切操作,比如把D:\\from\\day11文件夹,剪切到E:\\to文件夹下,但是java没有提供剪切文件夹的方法。
请编写程序从键盘接收两个文件夹路径,把其中一个文件夹(包含内容)剪切到另一个文件夹中。效果如下图：



![](img\06.png)

### 训练目标

能够使用递归调用定义方法,剪切文件夹。

### 训练提示

1、该方法是否需要返回值?

2、如何剪切单个文件，用什么流？

3、剪切文件的功能实现后，文件夹怎么办？

### 参考方案

定义方法,剪切文件夹,再调用方法传递两个文件夹File对象。

### 操作步骤  

1、定义复制文件到文件夹的方法copyFile2Dir(题目五中已经完成)
2、定义剪切文件夹的方法cutDir2Dir	
        2.1、在目标文件夹中创建源文件夹
        2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
        2.3、判断,如果File对象数组是null或者没有内容,结束方法
        2.4、遍历File对象数组
        2.5、判断,如果当前File对象是文件,调用copyFile2Dir方法,完成文件复制
	2.6、删除已经完成复制的文件
	2.7、判断,如果当前File对象是文件夹,递归调用cutDir2Dir方法,完成文件夹剪切
   	2.8、循环结束,删除文件夹
  3、创建File对象srcDir,代表源文件夹
  4、创建File对象destDir,代表目标文件夹(把源文件夹剪切到目标文件夹中)
  5、调用cutDir2Dir方法,传递源文件夹和目标文件夹,完成文件夹的剪切

### 参考答案

```java
import java.io.File;
import java.util.Scanner;

public class Test06 {
    //1、定义复制文件到文件夹的方法copyFile2Dir(题目五中已经完成)
    public static void copyFile2Dir(File file,File dir) throws IOException { ... }
    //2、定义剪切文件夹的方法cutDir2Dir	
    public static void cutDir2Dir(File srcDir,File destDir) throws IOException {
        //2.1、在目标文件夹中创建源文件夹
        File newDir = new File(destDir, srcDir.getName());
        if(!newDir.exists()) {
            newDir.mkdirs();
        }
        //2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
        File[] files = srcDir.listFiles();
        //2.3、判断,如果File对象数组是null或者没有内容,结束方法
        if(files==null || files.length==0) {
            return ;
        }
        //2.4、遍历File对象数组
        for (File file : files) {
            //2.5、判断,如果当前File对象是文件,调用copyFile2Dir方法,完成文件复制
            if(file.isFile()) {
                copyFile2Dir(file,newDir); 
                //2.6、删除已经完成复制的文件
                file.delete();
            }else {
                //2.7、判断,如果当前File对象是文件夹,递归调用cutDir2Dir方法,完成文件夹剪切
                cutDir2Dir(file,newDir);
            }
        }
        //2.8、循环结束,删除文件夹
        srcDir.delete();
    }      
    public static void main(String[] args)  throws IOException {
        //3、创建File对象srcDir,代表源文件夹
        File srcDir = new File("...");
        //4、创建File对象destDir,代表目标文件夹(把源文件夹剪切到目标文件夹中)
        File destDir = new File("...");
        //5、调用cutDir2Dir方法,传递源文件夹和目标文件夹,完成文件夹的剪切
        cutDir2Dir(srcDir,destDir);
        System.out.println("文件夹剪切完毕!!!");
    }	  
}
	
    
```



## 题目3（综合扩展）

windows操作系统中,可以搜索指定类型的文件,然后打开文件,根据文件的内容确定是否是所需要的文件。
本案例模拟完成复制源文件夹中的所有指定类型的文件到目标文件夹中。效果如下图：



![](img\07.png)

### 训练目标

能够使用递归调用定义方法,搜索文件夹。

### 训练提示

1、该方法是否需要返回值?

2、如何判断该文件是否是需要的文件？

3、搜索后符合要求的文件如何写到目标文件夹中？

### 参考方案

定义方法,按照文件类型搜索文件夹,再调用方法传递文件夹File对象。

### 操作步骤  

1、定义复制文件到文件夹的方法copyFile2Dir(题目五中已经完成)
2、定义把源文件夹中的指定类型的所有文件,复制到目标文件夹中的方法copyTypeFiles
	2.1、判断,如果目标文件夹不存在,则创建
        2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
        2.3、判断,如果File对象数组是null或者没有内容,结束方法
        2.4、遍历File对象数组
        2.5、判断,如果当前File对象是type类型的文件,调用copyFile2Dir方法,完成文件复制
        2.6、判断,如果当前File对象是文件夹,递归调用copyTypeFiles方法
3、创建String变量type,指定文件类型
4、创建File对象srcDir,代表源文件夹(被搜索的文件夹)
5、创建File对象destDir,代表目标文件夹(存储搜索到的文件的)
6、调用copyTypeFiles方法,传递源文件夹和目标文件夹,文件扩展名,完成指定类型文件的复制到目标文件夹

### 参考答案

```java
import java.io.File;
import java.util.Scanner;

public class Test07 {
     //1、定义复制文件到文件夹的方法copyFile2Dir(题目五中已经完成)
    public static void copyFile2Dir(File file,File dir) throws IOException { ... }
    //2、定义把源文件夹中的指定类型的所有文件,复制到目标文件夹中的方法copyTypeFiles
    public static void copyTypeFiles(File srcDir, String type, File destDir) throws IOException {
        //2.1、判断,如果目标文件夹不存在,则创建
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        //2.2、获取源文件夹中的所有的文件和文件夹对应的File对象数组
        File[] files = srcDir.listFiles();
        //2.3、判断,如果File对象数组是null或者没有内容,结束方法
        if(files==null || files.length==0) {
            return ;
        }
        //2.4、遍历File对象数组
        for(File file : files) {
            if(file.isFile()) {
                //2.5、判断,如果当前File对象是type类型的文件,调用copyFile2Dir方法,完成文件复制     					if(file.getName().toLowerCase().endsWith(type.toLowerCase())) {              
                    copyFile2Dir(file,destDir);
                }
            } else {
            	//2.6、判断,如果当前File对象是文件夹,递归调用copyTypeFiles方法
                copyTypeFiles(file,type,destDir);
            }
        }
    }
    public static void main(String[] args)  throws IOException {  
        //3、创建String变量type,指定文件类型
        String type = "...";		
        //4、创建File对象srcDir,代表源文件夹(被搜索的文件夹)
        File srcDir = new File("...");
        //5、创建File对象destDir,代表目标文件夹(存储搜索到的文件的)
        File destDir = new File("...");
        //6、调用copyTypeFiles方法,传递源文件夹和目标文件夹,文件扩展名,完成指定类型文件的复制到目标文件夹
        copyTypeFiles(srcDir,type,destDir);
    }	
}


```

