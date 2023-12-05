package assignment07; // 定义包名为assignment07 // Define package name as assignment07

public class BadHashFunctor implements HashFunctor { // 定义一个公开的BadHashFunctor类，实现HashFunctor接口 // Define a public class BadHashFunctor that implements the HashFunctor interface
    @Override // 表示覆写接口的方法 // Indicates overriding a method from the interface
    public int hash(String item) { // 定义哈希方法，接受一个字符串作为参数 // Define the hash method, taking a String as a parameter
        // 一个不好的哈希函数示例：只考虑第一个字符 // A bad hash function example: only considers the first character
        return item.length() > 0 ? item.charAt(0) : 0; // 如果字符串长度大于0，则返回第一个字符的ASCII值，否则返回0 // If the string length is greater than 0, return the ASCII value of the first character, else return 0
    }
}
