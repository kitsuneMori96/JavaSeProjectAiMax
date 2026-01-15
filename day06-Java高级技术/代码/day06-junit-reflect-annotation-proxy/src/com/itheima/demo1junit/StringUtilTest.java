// 声明当前类所在的包路径，包用于组织和管理Java类[3](@ref)
package com.itheima.demo1junit;

// 导入JUnit 4的断言工具类，提供assertEquals等方法用于验证测试结果[6](@ref)
// 注意：此导入与JUnit 5的@Test注解混用可能导致兼容性问题，建议统一使用JUnit 5的Assertions类[7](@ref)
import org.junit.Assert;
// 导入JUnit 5的Test注解，标记方法为测试方法[7](@ref)
import org.junit.jupiter.api.Test;

/**
 * StringUtil工具类的单元测试类。
 * 单元测试目标：验证StringUtil中方法的正确性，确保其行为符合预期[1](@ref)。
 * 命名规范：测试类名通常以"Test"结尾，与方法名对应[2](@ref)。
 */
public class StringUtilTest {

    /**
     * 测试StringUtil.printNumber方法。
     * 当前测试仅调用方法，但未使用断言验证输出（如控制台打印内容），不符合单元测试最佳实践[2](@ref)。
     * 改进建议：捕获输出流或验证方法副作用（如使用Mockito模拟输出）[2](@ref)。
     */
    @Test // 标记该方法为测试用例，JUnit会自动执行[1,7](@ref)
    public void testPrintNumber(){
        // 测试正常输入：字符串"张三"
        StringUtil.printNumber("张三");
        // 测试边界条件：输入为null，需确保方法能安全处理[2](@ref)
        StringUtil.printNumber(null);
        // 测试边界条件：空字符串，验证空值处理逻辑[2](@ref)
        StringUtil.printNumber("");
        // 问题：当前测试无断言，无法自动验证结果。需添加逻辑验证打印内容或异常行为[2](@ref)
    }

    /**
     * 测试StringUtil.getMaxIndex方法。
     * 使用断言全面覆盖正常、边界和异常场景，符合单元测试标准[1,6](@ref)。
     */
    @Test
    public void testGetMaxIndex(){
        // 先执行方法调用（可选，但断言已包含验证）
        StringUtil.getMaxIndex("abcde");
        StringUtil.getMaxIndex("");
        StringUtil.getMaxIndex(null);

        // 断言1：验证正常输入"abcde"的返回值应为4（字符串长度-1或最大索引）[6](@ref)
        // 若实际结果与预期不符，测试失败并提示"方法内部有Bug!"[3](@ref)
        Assert.assertEquals("方法内部有Bug!", 4, StringUtil.getMaxIndex("abcde"));

        // 断言2：验证空字符串输入应返回-1（边界条件）[2](@ref)
        Assert.assertEquals(-1, StringUtil.getMaxIndex(""));

        // 断言3：验证null输入应返回-1（异常情况处理）[2](@ref)
        Assert.assertEquals(-1, StringUtil.getMaxIndex(null));
    }
}