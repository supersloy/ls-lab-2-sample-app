//> using lib "org.scalameta::munit::0.7.29"

package timeserver


class MainTest extends munit.FunSuite {
    test("basic test") {
        assert(1 == 1)
    }

    test("failing") {
        assert(1 == 2)
    }
}
