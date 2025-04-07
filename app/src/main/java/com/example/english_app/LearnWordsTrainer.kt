package com.example.english_app

data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearnWordsTrainer {

    val dictionary = mutableListOf(
    Word("Vogon", "Вогон"),
    Word("Babel Fish", "Бабел-рыба"),
    Word("Apple", "Яблоко"),
    Word("Banana", "Банан"),
    Word("Car", "Машина"),
        Word("Dog", "Собака"),
        Word("Elephant", "Слон"),Word("Fish", "Рыба"),
        Word("Guitar", "Гитара"),Word("House", "Дом"),
        Word("Ice Cream", "Мороженое"),Word("Jacket", "Куртка"),
        Word("Keyboard", "Клавиатура"),Word("Lion", "Лев"),
        Word("Mountain", "Гора"),Word("Notebook", "Тетрадь"),
        Word("Orange", "Апельсин"),Word("Penguin", "Пингвин"),
        Word("Queen", "Королева"),Word("Rocket", "Ракета"),
        Word("Sun", "Солнце"),Word("Tree", "Дерево"),
        Word("Umbrella", "Зонтик"),Word("Violin", "Скрипка"),
        Word("Water", "Вода"),Word("Xylophone", "Ксилофон"),
        Word("Yacht", "Яхта"),Word("Zebra", "Зебра"),
        Word("Book", "Книга"),Word("Chair", "Стул"),
        Word("Desk", "Письменный стол"),Word("Egg", "Яйцо"),
        Word("Fire", "Огонь"),Word("Glass", "Стекло"),
        Word("Hat", "Шляпа"),Word("Island", "Остров"),
        Word("Jellyfish", "Медуза"),Word("Kangaroo", "Кенгуру"),
        Word("Lighthouse", "Маяк"),Word("Moon", "Луна"),
        Word("Nest", "Гнездо"),Word("Octopus", "Осьминог"),
        Word("Pencil", "Карандаш"),Word("Quokka", "Квокка"),
        Word("Rainbow", "Радуга"),Word("Star", "Звезда"),
        Word("Telescope", "Телескоп"),Word("Universe", "Вселенная"),
        Word("Volcano", "Вулкан"),Word("Window", "Окно"),
        Word("X-ray", "Рентген"),Word("Yogurt", "Йогурт"),
        Word("Zoo", "Зоопарк"),Word("Ant", "Муравей"),
        Word("Bread", "Хлеб"),Word("Cloud", "Облако"),
        Word("Dragon", "Дракон"),Word("Echo", "Эхо"),
        Word("Feather", "Перо"),Word("Goose", "Гусь"),
        Word("Hammer", "Молоток"),Word("Internet", "Интернет"),
        Word("Jungle", "Джунгли"),Word("King", "Король"),
        Word("Lamp", "Лампа"),Word("Mirror", "Зеркало"),
        Word("Noodle", "Лапша"),Word("Ocean", "Океан"),
        Word("Panda", "Панда"),Word("Quiver", "Колчан"),
        Word("Robot", "Робот"),Word("Snake", "Змея"),
        Word("Tornado", "Торнадо"),Word("Universe", "Вселенная"),
        Word("Violet", "Фиолетовый"),Word("Wolf", "Волк"),
        Word("Xenon", "Ксенон"),Word("Yawn", "Зевок"),
        Word("Zinc", "Цинк"),Word("Anchor", "Якорь"),
        Word("Basket", "Корзина"),Word("Candle", "Свеча"),
        Word("Dolphin", "Дельфин"),Word("Energy", "Энергия"),
        Word("Flute", "Флейта"),Word("Giraffe", "Жираф"),
        Word("Horizon", "Горизонт"),Word("Icicle", "Сосулька"),
        Word("Jigsaw", "Головоломка"),Word("Knight", "Рыцарь"),
        Word("Lantern", "Фонарь"),Word("Meteor", "Метеор"),
        Word("Nebula", "Туманность"),Word("Opera", "Опера"),
        Word("Pyramid", "Пирамида"),Word("Quilt", "Стёганое одеяло"),
        Word("Ripple", "Рябь"),Word("Satellite", "Спутник"),
        Word("Throne", "Трон"),Word("Unicorn", "Единорог"),
        Word("Vortex", "Вихрь"),Word("Wand", "Волшебная палочка"),
        Word("Xerox", "Ксерокс"),Word("Yo-yo", "Йо-йо"),
        Word("Zephyr", "Зефир")
    )

    private var currentQuestion: Question? = null

    fun getNextQuestion(): Question? {

        var notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = dictionary.filter { it.learned }.shuffled()
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
                dictionary.find { it.original == question.correctAnswer.original }?.let {
                    it.learned = true
                }
                true
            } else {
                false
            }
        } ?: false
    }

    fun getTotalWords(): Int {
        return dictionary.size
    }

    fun getLearnedWordsCount(): Int {
        return dictionary.count { it.learned }
    }
}

const val NUMBER_OF_ANSWERS: Int = 4