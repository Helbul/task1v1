import java.io.File

fun main() {
    val oldFile = File("test.txt").absoluteFile
    if (oldFile.exists()) {
        val newFile = correctingText(oldFile)
    }
}



