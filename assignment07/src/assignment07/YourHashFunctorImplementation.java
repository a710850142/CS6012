package assignment07;

public class YourHashFunctorImplementation implements HashFunctor {
    @Override
    public int hash(String item) {
        // 一个简单的哈希函数实现
        // 注意：这只是一个示例，具体的实现应该根据你的需求来定
        return item.hashCode();
    }
}
