package com.example.healthroutineapplication

import com.example.healthroutineapplication.models.ExerciseData

fun getChestExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 1,
            name = "푸시업",
            test =arrayOf("맨몸")
        ),
        Exercise(
            id = 2,
            name = "딥스",
            test =arrayOf("맨몸","머신")
        ),
        Exercise(
            id = 3,
            name = "플랫 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Exercise(
            id = 4,
            name = "인클라인 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Exercise(
            id = 5,
            name = "디클라인 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Exercise(
            id = 6,
            name = "플라이",
            test =arrayOf("덤벨","머신")
        ),
        Exercise(
            id = 7,
            name = "케이블 크로스 오버",
            test =arrayOf("케이블")
        )
    )
}

fun getLetExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 11,
            name = "풀업",
            test =arrayOf("맨몸","머신")
        ),
        Exercise(
            id = 12,
            name = "렛풀다운",
            test = arrayOf("머신","케이블")
        ),
        Exercise(
            id = 13,
            name = "로우",
            test = arrayOf("덤벨","바벨", "스미스 머신")
        ),
        Exercise(
            id = 14,
            name = "시티드 로우",
            test = arrayOf("머신","케이블")
        )
    )
}

fun getShoulderExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 21,
            name = "프론트 레이즈",
            test =arrayOf("덤벨","케이블")
        ),
        Exercise(
            id = 22,
            name = "숄더 프레스",
            test =arrayOf("덤벨", "머신", "바벨", "스미스 머신")
        ),
        Exercise(
            id = 23,
            name = "사이드 레터럴 레이즈",
            test =arrayOf("덤벨", "머신","케이블")
        ),
        Exercise(
            id = 24,
            name = "벤트 오버 레터럴 레이즈",
            test =arrayOf("덤벨", "머신","케이블")
        )
    )
}

fun getArmExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 31,
            name = "컬",
            test =arrayOf("덤벨", "바벨", "이지 바", "케이블")
        ),
        Exercise(
            id = 32,
            name = "라잉 트라이셉스 익스텐션",
            test =arrayOf("이지 바")
        ),
        Exercise(
            id = 33,
            name = "덤벨 킥백",
            test = arrayOf("덤벨")
        ),
        Exercise(
            id = 34,
            name = "케이블 푸시 다운",
            test =arrayOf("케이블")
        ),
        Exercise(
            id = 35,
            name = "오버 헤드 덤벨 프레스",
            test = arrayOf("덤벨")
        )
    )
}

fun getLegExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 41,
            name = "스쿼트",
            test =  arrayOf("머신", "바벨", "스미스 머신")
        ),
        Exercise(
            id = 42,
            name = "힙 쓰러스트",
            test =  arrayOf("머신", "바벨", "스미스 머신")
        ),
        Exercise(
            id = 43,
            name = "레그 컬",
            test = arrayOf("머신")
        ),
        Exercise(
            id = 44,
            name = "런지",
            test = arrayOf("덤벨", "맨몸","스미스 머신")
        ),
        Exercise(
            id = 45,
            name = "레그 프레스",
            test = arrayOf("머신")
        ),
        Exercise(
            id = 46,
            name = "레그 익스텐션",
            test =arrayOf("머신")
        )
    )
}

fun getAbsExerciseList(): List<Exercise> {
    return listOf(
        Exercise(
            id = 51,
            name = "크런치",
            test = arrayOf("맨몸")
        ),
        Exercise(
            id = 52,
            name = "레그 레이즈",
            test = arrayOf("맨몸")
        ),
        Exercise(
            id = 53,
            name = "행 인 레그 레이즈",
            test =arrayOf("맨몸")
        ),
        Exercise(
            id = 54,
            name = "V 업",
            test = arrayOf("맨몸")
        ),
        Exercise(
            id = 55,
            name = "플랭크",
            test =arrayOf("맨몸")
        )
    )
}

fun getProverbs():List<Proverb>{
    return listOf(
        Proverb("무슨 일이 있어도 2개 더해." ,"아놀드 슈워제네거"),
        Proverb("흔들리면 그건 지방이에요" ,"아놀드 슈워제네거"),
        Proverb("운동할 때 힘든 건 몸이 아니라 네 마음이다." ,"김종국"),
        Proverb("사람들은 앞모습이 아니라 뒷모습을 보고 감탄해" ,"김종국"),
        Proverb("자신의 몸에 만족하는 순간 더 이상의 발전은 없다"  ,"강경원"),
        Proverb("운동 해야 한다. 그렇지 않으면 어느 순간 당신을 고장 날 것이다."  ,"버락 오바마"),
        Proverb("세상에 있는 유일한 나쁜 운동은 안하는 운동 뿐이다."	 ,"작자미상"),
        Proverb("그만 두고 싶다는 생각이 들면, 왜 시작했는지 생각하여 보라." ,"작자미상"),
        Proverb("당신의 다리가 포기하는 것이 아니다. 당신의 머리가 포기하는 것이다. 계속 해라."  ,"작자미상"),
        Proverb("나는 운동을 한 후 후회한 적은 없다. 하지만 운동을 하지 않은 후 언제나 후회했다." ,"작자미상"),
        Proverb("너가 오늘 느끼는 고통은 내일 너에게 힘이 될 것이다."  ,"작자미상"),
    )
}

val exerciseList = mutableListOf<ExerciseData>()

var choiceRoutineList:List<ExerciseData>? = mutableListOf<ExerciseData>()

