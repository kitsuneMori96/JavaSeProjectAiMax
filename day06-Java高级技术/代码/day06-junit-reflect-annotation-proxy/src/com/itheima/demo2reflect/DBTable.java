package com.itheima.demo2reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// 1. 定义表注解
@Target(ElementType.TYPE) // 只能用于类/接口
@Retention(RetentionPolicy.RUNTIME) // 运行时保留
public @interface DBTable {
    String name() default ""; // 表名，默认值为空字符串
}

// 2. 定义字段注解
@Target(ElementType.FIELD) // 只能用于字段
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString {
    String name() default ""; // 列名
    int value() default 0; // 字段长度
    Constraints constraints() default @Constraints; // 嵌套注解，定义约束
}

// 3. 定义约束注解（标记注解）
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}

// 4. 使用注解映射一个User类到数据库表
@DBTable(name = "user") // 指定表名为"user"
class User {
    @SQLString(name = "id", value = 32, constraints = @Constraints(primaryKey = true))
    private String id;

    @SQLString(name = "username", value = 50, constraints = @Constraints(allowNull = false))
    private String username;
}

class AnnotationProcessor {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.User"); // 获取User类的Class对象

        // 1. 解析类上的@DBTable注解
        if (clazz.isAnnotationPresent(DBTable.class)) {
            DBTable dbTable = clazz.getAnnotation(DBTable.class);
            String tableName = dbTable.name();
            if (tableName.isEmpty()) {
                tableName = clazz.getSimpleName().toLowerCase(); // 如果未指定表名，使用类名
            }
            StringBuilder createSql = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");

            // 2. 解析类中字段上的注解
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(SQLString.class)) {
                    SQLString sqlString = field.getAnnotation(SQLString.class);
                    String columnName = sqlString.name().isEmpty() ? field.getName() : sqlString.name();
                    createSql.append("\n  ").append(columnName).append(" VARCHAR(").append(sqlString.value()).append(")");

                    // 处理字段约束
                    Constraints con = sqlString.constraints();
                    if (con.primaryKey()) {
                        createSql.append(" PRIMARY KEY");
                    }
                    if (!con.allowNull()) {
                        createSql.append(" NOT NULL");
                    }
                    if (con.unique()) {
                        createSql.append(" UNIQUE");
                    }
                    createSql.append(",");
                }
            }
            // 移除最后一个逗号并完成SQL语句
            createSql.setLength(createSql.length() - 1);
            createSql.append("\n);");
            System.out.println(createSql.toString());
        }
    }
}