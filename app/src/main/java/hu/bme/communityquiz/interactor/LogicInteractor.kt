package hu.bme.communityquiz.interactor

import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import java.math.BigDecimal
import javax.inject.Inject

class LogicInteractor @Inject constructor(private val serverInteractor: ServerInteractor) {
    private val category = ""

    /*
    private val questions = listOf(
        Question(
            category = "history",
            question = "Mit ünneplünk március tizenötödikén?",
            rightAnswer = "1848-as szabadságharc",
            wrongAnswer1 = "1956-os forradalom",
            wrongAnswer2 = "aradi vértanúk emléknapja",
            wrongAnswer3 = "Szent István megkoronázása"
        ),
        Question(
            category = "history",
            question = "Mikor ért véget a második világháború?",
            rightAnswer = "1939",
            wrongAnswer1 = "1938",
            wrongAnswer2 = "1945",
            wrongAnswer3 = "1946"
        ),
        Question(
            category = "history",
            question = "Mikor kezdődött az első világháború?",
            rightAnswer = "1914",
            wrongAnswer1 = "1918",
            wrongAnswer2 = "1915",
            wrongAnswer3 = "1919"
        ),
        Question(
            category = "sports",
            question = "Hány aranyat nyert eddig Hosszú Katinka 2021-ig?",
            rightAnswer = "67",
            wrongAnswer1 = "56",
            wrongAnswer2 = "30",
            wrongAnswer3 = "113"
        ),
        Question(
            category = "sports",
            question = "Fejezze be a szöveget: Három-Egy a félidőben és a végén...",
            rightAnswer = "Hat-Három",
            wrongAnswer1 = "Hét-Három",
            wrongAnswer2 = "Hét-Négy",
            wrongAnswer3 = "Hat-Négy"
        ),
        Question(
            category = "sports",
            question = "Mikor került megrendezésre a 2020-as olimpia",
            rightAnswer = "2021 nyarán",
            wrongAnswer1 = "2020 nyarán",
            wrongAnswer2 = "2020 ősszel",
            wrongAnswer3 = "2019 nyarán"
        ),
        Question(
            category = "art",
            question = "Mi volt Picasso művészeti ága?",
            rightAnswer = "Festészet",
            wrongAnswer1 = "Szobrászat",
            wrongAnswer2 = "Irodalom",
            wrongAnswer3 = "Divat"
        ),
        Question(
            category = "art",
            question = "Mihez nem értett Da Vinci?",
            rightAnswer = "Atom fizika",
            wrongAnswer1 = "Gépészet",
            wrongAnswer2 = "Anatómia",
            wrongAnswer3 = "Festészet"
        ),
        Question(
            category = "art",
            question = "Melyik nem egy Eminem szám?",
            rightAnswer = "The reel slim shady",
            wrongAnswer1 = "Lose Yourself",
            wrongAnswer2 = "Without Me",
            wrongAnswer3 = "Fall"
        )
    )*/
    private var loadedQuestions = listOf<Question>()
    private var questionIndex = 0
    private var lives = 3
    private var correctAnswers = 0

    fun loadQuestions(category: String) {
        questionIndex = 0
        lives = 3
        correctAnswers = 0
        loadedQuestions = serverInteractor.getQuiz(category).filterNotNull()

        //loadedQuestions = questions.filter { it.category?.compareTo(category) == 0 }.shuffled()
    }

    fun getCurrentQuestion(): Question {
        return loadedQuestions[questionIndex]
    }

    fun getLives(): Int = lives

    fun getCorrectAnswerCount(): Int = correctAnswers

    fun isGameFinished(): Boolean = lives == 0 || questionIndex == loadedQuestions.size - 1

    fun chooseAnswer(text: String) {
        if (text.compareTo(loadedQuestions[questionIndex].rightAnswer ?: "") == 0)
            correctAnswers++
        else
            lives--
    }

    fun nextQuestion(): Question {
        questionIndex++
        return loadedQuestions[questionIndex]
    }

    fun endQuiz(): Score {
        return Score(
            category = category,
            point = BigDecimal(correctAnswers)
        )
    }
}