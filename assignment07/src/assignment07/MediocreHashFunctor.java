package assignment07; // 定义包名为assignment07 // Define package name as assignment07

public class MediocreHashFunctor implements HashFunctor { // 定义一个公开的MediocreHashFunctor类，实现HashFunctor接口 // Define a public class MediocreHashFunctor that implements the HashFunctor interface
    @Override // 表示覆写接口的方法 // Indicates overriding a method from the interface
    public int hash(String item) { // 定义哈希方法，接受一个字符串作为参数 // Define the hash method, taking a String as a parameter
        // 一个中等质量的哈希函数：求和字符的代码 // A mediocre hash function: sums the codes of the characters
        int hash = 0; // 初始化哈希值为0 // Initialize hash value to 0
        for (int i = 0; i < item.length(); i++) { // 遍历字符串中的每个字符 // Iterate through each character in the string
            hash += item.charAt(i); // 将字符的ASCII值加到哈希值上 // Add the ASCII value of the character to the hash
        }
        return hash; // 返回计算得到的哈希值 // Return the calculated hash value
    }
}
