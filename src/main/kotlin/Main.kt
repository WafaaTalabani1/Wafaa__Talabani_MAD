import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val numberToGuess = createRandomNumber()
    var userGuessResult: String
    println("Enter your guess please.." +
            "Attention The number shall not  contain repeating digits")
    while (true) {
        val userGuess = scanner.nextLine()
        if (checkIfWin(numberToGuess, userGuess)) {
            println("You won!")
            break
        } else {
            try {
                userGuessResult = processTheUserGuess(numberToGuess, userGuess)
                println(userGuessResult)
            } catch (exception: IllegalArgumentException) {
                println(exception.message)
            }
        }
    }
}

fun createRandomNumber(): String {
    val digits = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    digits.shuffle()
    return digits.subList(0, 4).joinToString("")
}


fun checkIfWin(numberToGuess: String, userGuess: String): Boolean {
    return numberToGuess == userGuess
}

private fun processTheUserGuess(numberToGuess: String, userGuess: String): String {
    stringValidation(userGuess)
    var userGuessCopy = userGuess
    var rightDigitRightPlace = 0
    var rightDigitWrongPlace = 0
    for (i in userGuess.indices) {
        if (numberToGuess[i] == userGuessCopy[i]) {
            userGuessCopy = userGuessCopy.replaceFirst("${userGuessCopy[i]}", "-")
            rightDigitRightPlace++
        }

        /**
         * if the user's guess contains the i-th digit of the right number
         *      (i) get the index of this digit in the guess is retrieved and checked if
         *       userGuess[index] == numberToGuess[index] which means it is a right digit in the right place.
         *        if it is not the same, then the digit is right but at the wrong place
         */

        else if (userGuessCopy.contains(
                numberToGuess[i]
            )
        ) {
            val index = userGuessCopy.indexOf(numberToGuess[i])
            if (userGuessCopy[index] == numberToGuess[index]) {
                userGuessCopy = userGuessCopy.replaceFirst("${userGuessCopy[index]}", "-")
                rightDigitRightPlace++
            } else {
                userGuessCopy = userGuessCopy.replaceFirst("${userGuessCopy[index]}", "-")
                rightDigitWrongPlace++
            }
        }
    }
    return (rightDigitRightPlace + rightDigitWrongPlace).toString() + ":" + rightDigitRightPlace
}

private fun stringValidation(string: String) {
    when {
        string.isEmpty() -> throw IllegalArgumentException("The guess can not be empty!")
        string.length != 4 -> throw IllegalArgumentException("The guess must be 4 digits!")
        else -> {
            try {
                string.toInt()
            } catch (exception: NumberFormatException) {
                throw IllegalArgumentException("Only digits from 0 .. 9 are allowed!")
            }
        }
    }


}

