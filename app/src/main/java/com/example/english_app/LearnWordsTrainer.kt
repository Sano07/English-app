package com.example.english_app

class LearnWordsTrainer(private val selectedLvl: String, private val selectedDictionaries: List<String>?) {

    val dictionaries = listOf(
        beginnerTravel, intermediateTravel, advancedTravel, beginnerFoodDrinks, intermediateFoodDrinks, advancedFoodDrinks, beginnerClothesAppearance, intermediateClothesAppearance, advancedClothesAppearance, beginnerJobsWork, intermediateJobsWork, advancedJobsWork, beginnerHomeDailyLife, intermediateHomeDailyLife, advancedHomeDailyLife, beginnerHealthBody, intermediateHealthBody, advancedHealthBody, beginnerSports, intermediateSports, advancedSports, beginnerWeatherNature, intermediateWeatherNature, advancedWeatherNature, beginnerFeelingsEmotions, intermediateFeelingsEmotions, advancedFeelingsEmotions, beginnerCityTransport, intermediateCityTransport, advancedCityTransport
    )

    private var currentQuestion: Question? = null

    fun getDictionaries(level : String, themes : List<String>): MutableList<List<Word>> {
        val result = mutableListOf<List<Word>>()

        for (theme in themes) {
           if (level == "Beginner") {
               when(theme) {
                   "Travel" -> result.add(beginnerTravel)
                   "Food & Drinks" -> result.add(beginnerFoodDrinks)
                   "Clothes & Appearance" -> result.add(beginnerClothesAppearance)
                   "Jobs & Work" -> result.add(beginnerJobsWork)
                   "Home & Daily Life" -> result.add(beginnerHomeDailyLife)
                   "Health & Body" -> result.add(beginnerHealthBody)
                   "Sports" -> result.add(beginnerSports)
                   "Weather & Nature" -> result.add(beginnerWeatherNature)
                   "Feelings & Emotions" -> result.add(beginnerFeelingsEmotions)
                   "City & Transport" -> result.add(beginnerCityTransport)
               }
           } else if (level == "Intermediate") {
               when(theme) {
                   "Travel" -> result.add(intermediateTravel)
                   "Food & Drinks" -> result.add(intermediateFoodDrinks)
                   "Clothes & Appearance" -> result.add(intermediateClothesAppearance)
                   "Jobs & Work" -> result.add(intermediateJobsWork)
                   "Home & Daily Life" -> result.add(intermediateHomeDailyLife)
                   "Health & Body" -> result.add(intermediateHealthBody)
                   "Sports" -> result.add(intermediateSports)
                   "Weather & Nature" -> result.add(intermediateWeatherNature)
                   "Feelings & Emotions" -> result.add(intermediateFeelingsEmotions)
                   "City & Transport" -> result.add(intermediateCityTransport)
               }
           } else {
               when(theme) {
                   "Travel" -> result.add(advancedTravel)
                   "Food & Drinks" -> result.add(advancedFoodDrinks)
                   "Clothes & Appearance" -> result.add(advancedClothesAppearance)
                   "Jobs & Work" -> result.add(advancedJobsWork)
                   "Home & Daily Life" -> result.add(advancedHomeDailyLife)
                   "Health & Body" -> result.add(advancedHealthBody)
                   "Sports" -> result.add(advancedSports)
                   "Weather & Nature" -> result.add(advancedWeatherNature)
                   "Feelings & Emotions" -> result.add(advancedFeelingsEmotions)
                   "City & Transport" -> result.add(advancedCityTransport)
               }
           }
        }

        return result
    }

    fun getNextQuestion(): Question? {

        val notLearnedList = getDictionaries(selectedLvl, selectedDictionaries ?: listOf()).flatten().filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = getDictionaries(selectedLvl, selectedDictionaries ?: listOf()).flatten().filter { it.learned }.shuffled()
            notLearnedList.shuffled()
                .take(NUMBER_OF_ANSWERS) + learnedList
                .take(NUMBER_OF_ANSWERS - notLearnedList.size)

            } else {
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()

        val correctAnswer: Word = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer,
        )
        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {
        return currentQuestion?.let { question ->
            val correctAnswerId = question.variants.indexOf(question.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                getDictionaries(selectedLvl, selectedDictionaries ?: listOf()).flatten().find { it.original == question.correctAnswer.original }?.let {
                    it.learned = true
                }
                true
            } else {
                false
            }
        } ?: false
    }

    fun getTotalWords(): Int {
        return getDictionaries(selectedLvl, selectedDictionaries ?: listOf()).flatten().size
    }

    fun getLearnedWordsCount(): Int {
        return getDictionaries(selectedLvl, selectedDictionaries ?: listOf()).flatten().count { it.learned }
    }


}

fun getDictionaries(level : String, themes : List<String>): MutableList<List<Word>> {
    val result = mutableListOf<List<Word>>()

    for (theme in themes) {
        if (level == "Beginner") {
            when(theme) {
                "Travel" -> result.add(beginnerTravel)
                "Food & Drinks" -> result.add(beginnerFoodDrinks)
                "Clothes & Appearance" -> result.add(beginnerClothesAppearance)
                "Jobs & Work" -> result.add(beginnerJobsWork)
                "Home & Daily Life" -> result.add(beginnerHomeDailyLife)
                "Health & Body" -> result.add(beginnerHealthBody)
                "Sports" -> result.add(beginnerSports)
                "Weather & Nature" -> result.add(beginnerWeatherNature)
                "Feelings & Emotions" -> result.add(beginnerFeelingsEmotions)
                "City & Transport" -> result.add(beginnerCityTransport)
            }
        } else if (level == "Intermediate") {
            when(theme) {
                "Travel" -> result.add(intermediateTravel)
                "Food & Drinks" -> result.add(intermediateFoodDrinks)
                "Clothes & Appearance" -> result.add(intermediateClothesAppearance)
                "Jobs & Work" -> result.add(intermediateJobsWork)
                "Home & Daily Life" -> result.add(intermediateHomeDailyLife)
                "Health & Body" -> result.add(intermediateHealthBody)
                "Sports" -> result.add(intermediateSports)
                "Weather & Nature" -> result.add(intermediateWeatherNature)
                "Feelings & Emotions" -> result.add(intermediateFeelingsEmotions)
                "City & Transport" -> result.add(intermediateCityTransport)
            }
        } else {
            when(theme) {
                "Travel" -> result.add(advancedTravel)
                "Food & Drinks" -> result.add(advancedFoodDrinks)
                "Clothes & Appearance" -> result.add(advancedClothesAppearance)
                "Jobs & Work" -> result.add(advancedJobsWork)
                "Home & Daily Life" -> result.add(advancedHomeDailyLife)
                "Health & Body" -> result.add(advancedHealthBody)
                "Sports" -> result.add(advancedSports)
                "Weather & Nature" -> result.add(advancedWeatherNature)
                "Feelings & Emotions" -> result.add(advancedFeelingsEmotions)
                "City & Transport" -> result.add(advancedCityTransport)
            }
        }
    }

    return result
}


const val NUMBER_OF_ANSWERS: Int = 4