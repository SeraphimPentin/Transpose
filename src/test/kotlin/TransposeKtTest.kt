import org.junit.Test
import org.junit.Assert.*
import org.kohsuke.args4j.CmdLineException
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TransposeKtTest {

    private fun assertFileContent(expectedFile: String, actualFile: String): Boolean {
        val expected = File(expectedFile).readLines()
        val actual = File(actualFile).readLines()
        for (i in actual.indices){
            if(expected[i] != actual[i]) return false
        }
        return expected.size == actual.size
    }

    @Test
    fun transpose() {
        main("transpose -a 6 -t -r -o output/exampleOut.txt input/example.txt".split(" ").toTypedArray())
//        main("transpose -r -t input/example.txt".split(" ").toTypedArray())
//        assertTrue{ assertFileContent("input/example_1", "output/exampleOut")}
//        assertThrows(IllegalArgumentException::class.java) {
//            main("".split(" ").toTypedArray())
//        }
    }
}