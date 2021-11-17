import java.io.File

//1
fun myToUppercase (str: String) = str.uppercase()

//2
fun myToLowercase (str: String) = str.lowercase()

//3
fun myEqualsIgnoreCase (str1: String, str2: String) = str1.equals(str2, true)

//4
fun  myEquals (str1: String, str2: String) = str1 == str2

//5
fun mySubstring (str: String, startIndex: Int) = str.substring(startIndex)

//6
fun myUppercaseCheck (str: String): Boolean = (str == str.uppercase())


//7
/**
 * To check a word or sentence for the first capital letter
 * @return true or false
 */
fun myCapitalizationCheck (str: String): Boolean {
    val cloneStr = str.lowercase().replaceFirstChar { c: Char -> c.uppercase() }
    return str == cloneStr
}

//8
fun myCapitalization (str: String) = str.replaceFirstChar { it.uppercase() }

//9
/**
 * Сhecking a string is a sentence ending in '.' or '?' or '!'
 * @return true or else
 */
fun myEndSentenceCheck (str: String) : Boolean {
    if (str.isEmpty()) return false
    val lastChar: Char
    if (str.length == 1) {
        lastChar = str[0]
    } else lastChar = str[str.length - 1]
    return (lastChar == '.' || lastChar == '?' || lastChar == '!' )
}

//10
/**
 * Сhecking a word for correct style. Example of correct style: "WORD" or "Word" or "word"
 * @return true or false
 */
fun myCorrectFormatCheck (str: String) : Boolean {
    if (str == myToLowercase(str)) return true
    if (str == myToUppercase(str)) return true
    if (str == myCapitalization(myToLowercase(str))) return true
    return false
}

//11
/**
 * All words from the beginning of the sentence are capitalized,
 * capitalized and uppercase words are retained,
 * and all other words are converted to lowercase.
 *
 * @param Filename with text to convert
 * @return Filename with correct ext
 */
fun correctingText (fileName: File): File {
    val reader = fileName.inputStream().bufferedReader()
    val fileNameOut = File("test2.txt").absoluteFile
    val writer = fileNameOut.outputStream().bufferedWriter()
    var line: String
    var lastWord = "."
    while (reader.ready()) {
        line = reader.readLine()

        //Replacing multiple spaces with one
        line = line.replace(" +".toRegex(), " ")

        //Get all the words of the text
        var arrayWords = line.split(" ").toMutableList()
        for (i in arrayWords.indices) {
            val word = arrayWords[i]
            if (myEndSentenceCheck(lastWord)) {
                if (!myUppercaseCheck(word) && !myCapitalizationCheck(word)) {
                    arrayWords[i] = myCapitalization(word)
                }
            } else if (!myCorrectFormatCheck(word)) {
                arrayWords[i] = myToLowercase(word)
            }
            lastWord = word
            writer.write("${arrayWords[i]} ")
            print("${arrayWords[i]} ")
        }
        writer.newLine()
        println()
    }
    writer.close()
    reader.close()
    return fileNameOut
}