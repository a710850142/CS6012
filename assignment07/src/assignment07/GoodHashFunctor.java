package assignment07; // 定义包名为assignment07 // Define package name as assignment07

public class GoodHashFunctor implements HashFunctor { // 定义一个公开的GoodHashFunctor类，实现HashFunctor接口 // Define a public class GoodHashFunctor that implements the HashFunctor interface
    @Override // 表示覆写接口的方法 // Indicates overriding a method from the interface
    public int hash(String item) { // 定义哈希方法，接受一个字符串作为参数 // Define the hash method, taking a String as a parameter
        // 一个好的哈希函数：使用一个质数并结合每个字符 // A good hash function: uses a prime number and multiplies/adds each character
        int hash = 7; // 初始化哈希值为7 // Initialize hash value as 7
        for (int i = 0; i < item.length(); i++) { // 遍历字符串中的每个字符 // Iterate through each character in the string
            hash = hash * 31 + item.charAt(i); // 使用31（一个质数）乘以当前哈希值并加上字符的ASCII值 // Multiply the current hash value by 31 (a prime number) and add the ASCII value of the character
        }
        return hash; // 返回计算得到的哈希值 // Return the calculated hash value
    }
}
