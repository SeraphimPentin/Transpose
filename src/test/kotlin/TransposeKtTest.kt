import org.junit.Test
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import java.io.IOException
import java.lang.IllegalArgumentException

class TransposeKtTest {

    private fun assertFileContent(expectedFile: String, actualFile: String): Boolean {
        val expected = File(expectedFile).readLines()
        val actual = File(actualFile).readLines()
        return expected == actual
    }

    @Test
    fun transpose() {
//        main("transpose -a 5 -t -r input/example.txt".split(" ").toTypedArray())
        main("transpose -a 6 -t -r -o output/exampleOut.txt input/example.txt".split(" ").toTypedArray())
        assertTrue { assertFileContent("output/expectedOut", "output/exampleOut.txt") }
        assertFalse { assertFileContent("input/example_1", "input/example_2") }
//        assertThrows(IllegalArgumentException::class.java) {
//            main("eee".split(" ").toTypedArray())
//        }
//        main("transpose -r -o output/exampleOut_1 input/example_1".split(" ").toTypedArray())
//        assertTrue {
//            assertFileContent("output/exprctedOut_1", "output/exampleOut_1")
//        }
//        assertFalse {
//            assertFileContent("output/exampleOut.txt", "output/exprctedOut_1")
//        }
//        assertThrows(IOException::class.java) {
//            main("transpose -r -o output/exampleOut_1 ddfd".split(" ").toTypedArray())
//        }
//        assertThrows(IOException::class.java) {
//            main("transpose -r -o output/exampleOut_1".split(" ").toTypedArray())
//        }
//        assertThrows(IllegalArgumentException::class.java) {
//            main("-a 4 -t -o output/exampleOut_1 input/example_1".split(" ").toTypedArray())
//        }
    }
}