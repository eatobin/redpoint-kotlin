package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ApplyTest : StringSpec({
    "applyOrError should return one" {
        applyOrError(Pair(1, 12)) shouldBe "one"
    }

    "applyOrError should return two" {
        applyOrError(Pair(11, 12)) shouldBe "two"
    }

    // "getMaxBooks should return the Borrower maxBooks" {
    //     getMaxBooks(br1) shouldBe 1
    // }

    // "setMaxBooks should set the Borrower maxBooks" {
    //     setMaxBooks(11, br1) shouldBe Borrower("Borrower1", 11)
    // }

    // "the Borrower string should print" {
    //     borrowerToString(br1) shouldBe "Borrower1 (1 books)"
    // }
})
