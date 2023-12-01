package assignment06; // 包声明 - Package declaration

// 导入所需的类 - Importing necessary classes
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 定义拼写检查器类 - Defining the SpellChecker class
public class SpellChecker {
    private BinarySearchTree<String> dictionary; // 使用二叉搜索树存储字典 - Using a BinarySearchTree to store the dictionary

    // 构造器，初始化字典 - Constructor to initialize dictionary
    public SpellChecker() {
        dictionary = new BinarySearchTree<>();
    }

    // 基于给定单词列表的构造器 - Constructor with a given list of words
    public SpellChecker(List<String> words) {
        this(); // 调用默认构造器 - Calling the default constructor
        buildDictionary(words); // 构建字典 - Build the dictionary
    }

    // 基于文件的构造器 - Constructor with a dictionary file
    public SpellChecker(File dictionaryFile) {
        this(); // 调用默认构造器 - Calling the default constructor
        buildDictionary(readFromFile(dictionaryFile)); // 从文件中读取并构建字典 - Read from file and build the dictionary
    }

    // 向字典中添加单词的方法 - Method to add a word to the dictionary
    public void addToDictionary(String word) {
        dictionary.add(word.toLowerCase()); // 添加小写单词 - Add the word in lowercase
    }

    // 从字典中移除单词的方法 - Method to remove a word from the dictionary
    public void removeFromDictionary(String word) {
        dictionary.remove(word.toLowerCase()); // 移除小写单词 - Remove the word in lowercase
    }

    // 使用单词列表构建字典的私有方法 - Private method to build the dictionary using a list of words
    private void buildDictionary(List<String> words) {
        for (String word : words) {
            addToDictionary(word); // 添加每个单词到字典 - Add each word to the dictionary
        }
    }

    // 检查文件中单词拼写的方法 - Method to spell check words in a file
    public List<String> spellCheck(File documentFile) {
        List<String> wordsToCheck = readFromFile(documentFile); // 从文件中读取单词 - Read words from the file
        List<String> misspelledWords = new ArrayList<>(); // 用于存储拼写错误的单词 - For storing misspelled words

        for (String word : wordsToCheck) {
            if (!dictionary.contains(word)) { // 如果字典中不包含该单词 - If the dictionary does not contain the word
                misspelledWords.add(word); // 添加到拼写错误列表 - Add to the list of misspelled words
            }
        }

        return misspelledWords; // 返回拼写错误的单词列表 - Return the list of misspelled words
    }

    // 从文件中读取单词的私有方法 - Private method to read words from a file
    private List<String> readFromFile(File file) {
        ArrayList<String> words = new ArrayList<>();

        try (Scanner fileInput = new Scanner(file)) {
            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*"); // 设置分隔符，忽略非字母字符 - Set delimiter to ignore non-letter characters

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals("")) { // 如果字符串不为空 - If the string is not empty
                    words.add(s.toLowerCase()); // 添加小写单词 - Add the word in lowercase
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found."); // 如果文件未找到，打印错误 - Print an error if the file is not found
        }

        return words; // 返回读取到的单词列表 - Return the list of read words
    }
}
